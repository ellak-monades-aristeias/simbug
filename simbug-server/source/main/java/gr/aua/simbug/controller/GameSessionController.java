package gr.aua.simbug.controller;

import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;
import gr.aua.simbug.service.GameSessionRoundService;
import gr.aua.simbug.service.GameSessionService;
import gr.aua.simbug.utils.SimbugUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSessionController
{
	/**
	 * The GameSession service.
	 */
	@Autowired
	private GameSessionService gameSessionService;

	/**
	 * The GameSessionRound service.
	 */
	@Autowired
	private GameSessionRoundService gameSessionRoundService;

	@Autowired
	private GameSession gameSession;
	
	
	@RequestMapping("/initGameSession/{uuidOfGameSession}")
	public String initGameSession(@PathVariable String uuidOfGameSession, 
			@RequestParam(value = "definitionString", required = false) String definitionString, 
			@RequestParam(value = "listOfPlayers", required = false) String jsonListOfPlayers)
	{
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String definitionFile = "G:/Java/Eclipse/simbug/simbug/simbug-server/source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
		definitionString = SimbugUtils.fileToString(definitionFile);
		jsonListOfPlayers = "[{\"uuid\":\"1\"}, {\"uuid\":\"2\"}, {\"uuid\":\"3\"}]";
		
		// Create game session. Update database
		gameSession.createGameSession(uuidOfGameSession, definitionString, jsonListOfPlayers);
		
		return uuidOfGameSession;
	}
	
	@RequestMapping("/advanceTurn/{uuidOfGameSession}")
	public String advanceTurn( @PathVariable String uuidOfGameSession)
	{
		// Calculates new state. Runs algorithm. Updates database.
		return uuidOfGameSession;
	}

	@RequestMapping("/submitChoices/{uuidOfGameSession}")
	public String submitChoices( @PathVariable String uuidOfGameSession, 
			@RequestParam(value = "jsonString", required = false) String jsonString)
	{
		// Gets variables and set player's choices. Updates database.
		
		// POST variables:
		// JSON string {"uuid":"1", "choiceVariables":{"numberChoice":"22"}} 

		gameSession.savePlayerChoiceVariables(uuidOfGameSession, jsonString);
		// Returns: "ok"
		return "Session: " + uuidOfGameSession + " - Choice variables set OK.";
	}

	@RequestMapping("/getWorldState/{uuidOfGameSession}")
	public String getWorldState( @PathVariable String uuidOfGameSession)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(uuidOfGameSession);
		
		// Returns world state variables.
		// Returns: JSON string of world variables {world_state_variable_name: value]}
		List<GameSessionRoundVariable> worldStateVariables = gameSessionRoundService.fetchWorldStateVariablesByUuidByRound(gameSession);
		String json = "{";
		int counter = 1;
		for (GameSessionRoundVariable gsrv : worldStateVariables) 
		{
			json += "\"" + gsrv.getVariableName() + "\":\"" + gsrv.getVariableValue();
			if (counter < worldStateVariables.size())
			{
				json += ",";
			}
			counter++;
			System.out.println(gsrv.getVariableName());
		}
		json += "}";
		return json;
	}

	@RequestMapping("/getPlayerState/{uuidOfGameSession}/{uuidOfPlayer}")
	public String getPlayerState( @PathVariable String uuidOfGameSession, @PathVariable String uuidOfPlayer)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(uuidOfGameSession);
		
		// Returns player state variables.	
		// Returns: JSON string of world variables {user_state_variable_name: value]}
		List<GameSessionRoundPlayerVariable> playerStateVariables = gameSessionRoundService.fetchPlayerStateVariablesByUuidByRoundByPlayer(gameSession, uuidOfPlayer);
		String json = "{";
		int counter = 1;
		for (GameSessionRoundPlayerVariable gsrv : playerStateVariables) 
		{
			json += "\"" + gsrv.getVariableName() + "\":\"" + gsrv.getVariableValue();
			if (counter < playerStateVariables.size())
			{
				json += ",";
			}
			counter++;
			System.out.println(gsrv.getVariableName());
		}
		json += "}";
		return json;
	}

	public GameSession getGameSession() 
	{
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) 
	{
		this.gameSession = gameSession;
	}

	public GameSessionService getGameSessionService() {
		return gameSessionService;
	}

	public void setGameSessionService(GameSessionService gameSessionService) {
		this.gameSessionService = gameSessionService;
	}

}
