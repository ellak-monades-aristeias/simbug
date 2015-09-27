package gr.aua.simbug.game;

import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.ExternalDataType;
import gr.aua.simbug.definition.ParameterType;
import gr.aua.simbug.service.GameSessionService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSession implements GameConstants
{
	/**
	 * The GameSession service.
	 */
	@Autowired
	private GameSessionService gameSessionService;

	private Definition definition;
	private List<GameSessionPlayer> gameSessionPlayers = new ArrayList<GameSessionPlayer>();
	private List<GameSessionRound> gameSessionRounds = new ArrayList<GameSessionRound>();
	private List<GameSessionVariable> configurationParameters = new ArrayList<GameSessionVariable>();	
	private List<GameSessionVariable> externalVariables = new ArrayList<GameSessionVariable>();
	private long currentRound;
	private String uuidOfGameSession;
	private String definitionData;
	private String jsonListOfPlayers;
	
	@Autowired
	private GameSessionPlayer gameSessionPlayer;

	@Autowired
	private GameSessionVariable gameSessionVariable;
	
	@Autowired
	private GameSessionRound gameSessionRound;
	
	/**
	 * 
	 */
	public GameSession() 
	{
		super();
	}

	/**
	 * 
	 * @param xmlDefinition
	 * @param jsonListOfPlayers
	 */
	public void createGameSession(String uuidOfGameSession, String xmlDefinition, String jsonListOfPlayers) 
	{
		this.uuidOfGameSession = uuidOfGameSession;
		this.definitionData = xmlDefinition;
		this.jsonListOfPlayers = jsonListOfPlayers;
		currentRound = 1;

		createDefinitionFromXml(xmlDefinition);
		createListOfPlayers(jsonListOfPlayers);
		
		// Save session data
		gameSessionService.saveGameSession(this);
		
		// Save the session players into the database
		savePlayers();
		
		// Save Configuration parameters
		saveConfigurationParameters(definition.getConfiguration().getParameter());

		// Save ExternalParameters
		saveExternalParameters(definition.getExternalParameters().getExternalVariable());

		// randomNumberGenerators. Are contained into the xmlDefinition and is
		// not saved separately into the database
		System.out.println("\nrandomNumberGenerators");
		System.out.println(definition.getRandomNumberGenerators().getRandomNumberGenerator());

		// choicesToStateAlgorithm. The choicesToStateAlgorithm is contained
		// into the xmlDefinition and is not saved separately into the database
		System.out.println("\nChoicesToStateAlgorithm");
		// System.out.println(definition.getChoicesToStateAlgorithm());
		
		createRound(currentRound);
	}

	/**
	 * 
	 * @param externalVariables
	 */
	private void saveExternalParameters(List<ExternalDataType> externalVariables) 
	{
		System.out.println("\nExternalParameters");
		for (ExternalDataType param : externalVariables) 
		{
			//GameSessionVariable var = new GameSessionVariable(EXTERNAL_PARAMETER, param, uuidOfGameSession);
			gameSessionVariable.createGameSessionVariable(EXTERNAL_PARAMETER, param, uuidOfGameSession);
			gameSessionVariable.saveSessionVariable();
			System.out.println(param.getName() + "-" + param.getValue() + "-" + param.getType());
		}
	}

	/**
	 * Save configuration parameters
	 * @param parameter
	 */
	private void saveConfigurationParameters(List<ParameterType> parameters) 
	{
		// TODO
		System.out.println("\nConfiguration");
		for (ParameterType param : parameters) 
		{
			GameSessionVariable var = new GameSessionVariable(CONFIGURATION_PARAMETER, param, uuidOfGameSession);
			var.saveSessionVariable();
			//this.configurationParameters.add(var);
			System.out.println(param.getName() + "-" + param.getValue() + "-" + param.getType());
		}
	}

	/**
	 * Save session players
	 */
	private void savePlayers() 
	{
		for (GameSessionPlayer player : gameSessionPlayers) 
		{
			gameSessionPlayer.createSessionPlayer(player, this);
			gameSessionPlayer.save();
		}		
		showPlayers();
	}

	/**
	 * List content of session players
	 */
	private void showPlayers() 
	{
		System.out.println("\nPlayers: " + gameSessionPlayers.size());
		for (GameSessionPlayer player : gameSessionPlayers) 
		{
			System.out.println(player.getUuid());
		}
	}

	/**
	 * 
	 * @param round
	 */
	private void createRound(long round) 
	{
		gameSessionRound.createRound(this, currentRound);

		// Save WorldStateVariables
		gameSessionRound.saveWorldStateVariables(definition.getWorldStateVariables().getWorldStateVariable());

		// Save PlayerChoiceVariables
		gameSessionRound.savePlayerChoiceVariables(gameSessionPlayers, definition.getPlayerChoiceVariables().getPlayerChoiceVariable());

		// Save playerStateVariables
		gameSessionRound.savePlayerStateVariables(gameSessionPlayers, definition.getPlayerStateVariables().getPlayerStateVariable());
	}

	/**
	 * Gets as input the json string jsonListOfPlayers (array of uuids) and
	 * creates ArrayList<gameSessionPlayers> players
	 */
	private void createListOfPlayers(String jsonListOfPlayers)
	{
		System.out.println("\n\nListOfPlayers"); // ListOfPlayers
		System.out.println(jsonListOfPlayers);

		// Extract the list of players from the json array
		ObjectMapper objectMapper = new ObjectMapper();
		try 
		{
			gameSessionPlayers = objectMapper.readValue(jsonListOfPlayers, 
					objectMapper.getTypeFactory().constructCollectionType(List.class, GameSessionPlayer.class));
			System.out.println("NumOfPlayers: " + gameSessionPlayers.size());
		} 
		catch (JsonParseException e) 
		{
			e.printStackTrace();
		} 
		catch (JsonMappingException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates the Definition definition object from the definitionString (xml
	 * structure)
	 */
	private void createDefinitionFromXml(String xmlDefinition) 
	{
		JAXBContext jc;
		Unmarshaller unmarshaller;
		try 
		{
			jc = JAXBContext.newInstance(Definition.class);
			unmarshaller = jc.createUnmarshaller();

			InputStream xmlStream = new ByteArrayInputStream(xmlDefinition.getBytes(StandardCharsets.UTF_8));
			definition = (Definition) unmarshaller.unmarshal(xmlStream);
		} 
		catch (JAXBException e1) 
		{
			e1.printStackTrace();
		}
	}

	/**
	 * @return the definition
	 */
	public Definition getDefinition() 
	{
		return definition;
	}

	/**
	 * @param definition
	 *            the definition to set
	 */
	public void setDefinition(Definition definition) 
	{
		this.definition = definition;
	}

	/**
	 * @return the currentRound
	 */
	public long getCurrentRound() 
	{
		return currentRound;
	}

	/**
	 * @param currentRound
	 *            the currentRound to set
	 */
	public void setCurrentRound(long currentRound) 
	{
		this.currentRound = currentRound;
	}

	public List<GameSessionPlayer> getGameSessionPlayers() {
		return gameSessionPlayers;
	}

	public void setGameSessionPlayers(List<GameSessionPlayer> gameSessionPlayers) {
		this.gameSessionPlayers = gameSessionPlayers;
	}

	public List<GameSessionRound> getGameSessionRounds() {
		return gameSessionRounds;
	}

	public void setGameSessionRounds(List<GameSessionRound> gameSessionRounds) {
		this.gameSessionRounds = gameSessionRounds;
	}

	public List<GameSessionVariable> getConfigurationParameters() {
		return configurationParameters;
	}

	public void setConfigurationParameters(
			List<GameSessionVariable> configurationParameters) {
		this.configurationParameters = configurationParameters;
	}

	public List<GameSessionVariable> getExternalVariables() {
		return externalVariables;
	}

	public void setExternalVariables(List<GameSessionVariable> externalVariables) {
		this.externalVariables = externalVariables;
	}

	public String getUuidOfGameSession() {
		return uuidOfGameSession;
	}

	public void setUuidOfGameSession(String uuidOfGameSession) {
		this.uuidOfGameSession = uuidOfGameSession;
	}

	public String getDefinitionData() {
		return definitionData;
	}

	public void setDefinitionData(String definitionData) {
		this.definitionData = definitionData;
	}

	public String getJsonListOfPlayers() {
		return jsonListOfPlayers;
	}

	public void setJsonListOfPlayers(String jsonListOfPlayers) {
		this.jsonListOfPlayers = jsonListOfPlayers;
	}

	public GameSessionService getGameSessionService() {
		return gameSessionService;
	}

	public void setGameSessionService(GameSessionService gameSessionService) {
		this.gameSessionService = gameSessionService;
	}

}