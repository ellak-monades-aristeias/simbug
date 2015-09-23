package gr.aua.simbug.game;

import gr.aua.simbug.beans.Player;
import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.ExternalDataType;
import gr.aua.simbug.definition.PlayerStateVariableDataType;
import gr.aua.simbug.definition.VariableType;

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

public class GameSession
{
	private Definition definition;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private int currentRound;
	
	public GameSession()
	{
		super();
	}

	public GameSession(String UuidOfGameSession, String definitionString, String jsonListOfPlayers)
	{
		super();
		createDefinition(definitionString);
		creatListOfPlayers(jsonListOfPlayers);
		initialize();
	}
	
	/**
	 * Gets as input the json string jsonListOfPlayers (array of uuids) and creates ArrayList<Player> players
	 */
	private void creatListOfPlayers(String jsonListOfPlayers)
	{
		System.out.println("\n\nListOfPlayers"); //ListOfPlayers
		System.out.println(jsonListOfPlayers);
		
	    ObjectMapper objectMapper = new ObjectMapper();
		try
		{
			setPlayers(objectMapper.readValue(jsonListOfPlayers, objectMapper.getTypeFactory().constructCollectionType(List.class, Player.class)));
		}
		catch (JsonParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Players
		System.out.println("\nPlayers: " + players.size());
		for (Player player : players)
		{
			System.out.println(player.getUuid()); 
		}
	}

	/**
	 * Game initialization. 
	 * Stores the game structure into the database
	 */
	private void initialize()
	{
		// Configuration parameters
		System.out.println(definition.getConfiguration().getClass());
		
		// RandomNumberGenerators
		System.out.println(definition.getRandomNumberGenerators());

		// ExternalParameters
		System.out.println("\nExternalParameters"); 
		for (ExternalDataType param : definition.getExternalParameters().getExternalVariable())
		{
			System.out.println(param.getName() + "-" + param.getValue() + "-" + param.getType());
		}
		
		// ChoicesToStateAlgorithm
		System.out.println("\nChoicesToStateAlgorithm"); 
		//System.out.println(definition.getChoicesToStateAlgorithm());

		// PlayerChoiceVariables
		// A value for each player
		for (VariableType param : definition.getPlayerChoiceVariables().getPlayerChoiceVariable())
		{
			System.out.println(param.getName() + "-" + param.getType());
		}
		
		// PlayerStateVariables
		// A value for each player
		System.out.println("\nPlayerStateVariables"); 
		for (PlayerStateVariableDataType param : definition.getPlayerStateVariables().getPlayerStateVariable())
		{
			System.out.println(param.getName() + "-" + param.getType());
		}

		// WorldStateVariables
		System.out.println("\nWorldStateVariables"); 
		for (VariableType param : definition.getWorldStateVariables().getWorldStateVariable())
		{
			System.out.println(param.getName() + "-" + param.getType());
		}
		
	}
	
	/**
	 * Creates the Definition definition object from the definitionString (xml structure)
	 */
	private void createDefinition(String definitionString)
	{
		JAXBContext jc;
		Unmarshaller unmarshaller;
		try
		{
			jc = JAXBContext.newInstance(Definition.class);
			unmarshaller = jc.createUnmarshaller();

			InputStream xmlStream = new ByteArrayInputStream(definitionString.getBytes(StandardCharsets.UTF_8));
			definition = (Definition) unmarshaller.unmarshal(xmlStream);
		}
		catch (JAXBException e1)
		{
			// TODO Auto-generated catch block
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
	 * @param definition the definition to set
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
	 * @param currentRound the currentRound to set
	 */
	public void setCurrentRound(int currentRound)
	{
		this.currentRound = currentRound;
	}

	public ArrayList<Player> getPlayers()
	{
		return players;
	}

	public void setPlayers(ArrayList<Player> players)
	{
		this.players = players;
	}
	

}
