package gr.aua.simbug.game;

import gr.aua.simbug.beans.Player;
import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.ExternalDataType;
import gr.aua.simbug.definition.ParameterType;

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

public class GameSession implements GameConstants
{
	private Definition definition;
	private List<GameSessionPlayer> gameSessionPlayers = new ArrayList<GameSessionPlayer>();
	private List<GameSessionRound> gameSessionRounds = new ArrayList<GameSessionRound>();
	private List<GameSessionVariable> configurationParameters = new ArrayList<GameSessionVariable>();	
	private List<GameSessionVariable> externalVariables = new ArrayList<GameSessionVariable>();
	private int currentRound;
	private String uuidOfGameSession;

	/**
	 * 
	 */
	public GameSession() 
	{
		super();
	}

	/**
	 * Define the initial state of the game
	 * 
	 * @param uuidOfGameSession
	 * @param definitionString
	 * @param jsonListOfPlayers
	 */
	public GameSession(String uuidOfGameSession, String definitionString, String jsonListOfPlayers) 
	{
		super();
		this.uuidOfGameSession = uuidOfGameSession;
		currentRound = 1;
		createSession(definitionString, jsonListOfPlayers);
		createRound(currentRound);
	}

	/**
	 * 
	 * @param xmlDefinition
	 * @param jsonListOfPlayers
	 */
	private void createSession(String xmlDefinition, String jsonListOfPlayers) 
	{
		createDefinitionFromXml(xmlDefinition);
		creatListOfPlayers(jsonListOfPlayers);
		
		// TODO 
		// Save session data
		
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
			GameSessionVariable var = new GameSessionVariable(EXTERNAL_PARAMETER, param, uuidOfGameSession);
			var.save();
			System.out.println(param.getName() + "-" + param.getValue() + "-" + param.getType());
		}
	}

	/**
	 * Save configuration parameters
	 * @param parameter
	 */
	private void saveConfigurationParameters(List<ParameterType> parameters) 
	{
		System.out.println("\nConfiguration");
		for (ParameterType param : parameters) 
		{
			GameSessionVariable var = new GameSessionVariable(CONFIGURATION_PARAMETER, param, uuidOfGameSession);
			var.save();
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
			player.save();
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
	private void createRound(int round) 
	{
		GameSessionRound gameSessionRound = new GameSessionRound(this, currentRound);

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
	private void creatListOfPlayers(String jsonListOfPlayers)
	{
		System.out.println("\n\nListOfPlayers"); // ListOfPlayers
		System.out.println(jsonListOfPlayers);

		// Extract the list of players from the json array
		ObjectMapper objectMapper = new ObjectMapper();
		try 
		{
			gameSessionPlayers = objectMapper.readValue(jsonListOfPlayers, 
					objectMapper.getTypeFactory().constructCollectionType(List.class, Player.class));
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
	public int getCurrentRound() 
	{
		return currentRound;
	}

	/**
	 * @param currentRound
	 *            the currentRound to set
	 */
	public void setCurrentRound(int currentRound) 
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

}
