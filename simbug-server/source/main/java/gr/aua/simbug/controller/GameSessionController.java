package gr.aua.simbug.controller;

import gr.aua.simbug.beans.Info;
import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.PlayerStateVariableDataType;
import gr.aua.simbug.definition.VariableType;
import gr.aua.simbug.game.GameConstants;
import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionPlayer;
import gr.aua.simbug.game.GameSessionRound;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;
import gr.aua.simbug.game.GameSessionVariable;
import gr.aua.simbug.service.GameSessionRoundService;
import gr.aua.simbug.service.GameSessionService;
import gr.aua.simbug.utils.SimbugUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSessionController implements GameConstants
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
	
	@Autowired
	private GameSessionRound gameSessionRound;

	
	/**
	 * 
	 * @param sessionUuid
	 * @param definitionString
	 * @param jsonListOfPlayers
	 * @return
	 */
	@RequestMapping("/initGameSession/{sessionUuid}")
	public String initGameSession(@PathVariable String sessionUuid, 
			@RequestParam(value = "definitionString", required = false) String definitionString, 
			@RequestParam(value = "listOfPlayers", required = false) String jsonListOfPlayers)
	{
		if (gameSessionService.fetchGameSessionByUuid(sessionUuid) != null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' already exists\"}";
		}
		
		// TODO
		// TO BE DELETED START
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String definitionFile = "G:/Java/Eclipse/simbug/simbug/simbug-server/source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
		//definitionFile = "/home/michael/applications/eclipse/simbug/simbug/simbug-server/source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
		definitionString = SimbugUtils.fileToString(definitionFile);
		jsonListOfPlayers = "[{\"uuid\":\"1\"}, {\"uuid\":\"2\"}, {\"uuid\":\"3\"}]";
		// jsonListOfPlayers = "[{\"uuid\":\"56030487-0cf0-4f60-b10c-4d8a8fe9baf7\"},{\"uuid\":\"561dec18-8d0c-41f9-833b-1b848fe9baf7\"},{\"uuid\":\"7ed8b252-611a-11e5-9d70-feff819cdc9f\"}]";
		// TO BE DELETED END

		if ((definitionString == null) || (definitionString.isEmpty()))
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"'definitionString' should not be empty.\"}";	
		}
		if ((jsonListOfPlayers == null) || (jsonListOfPlayers.isEmpty()))
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"'listOfPlayers' should not be empty.\"}";	
		}
		// Create game session. Update database
		gameSession.createGameSession(sessionUuid, definitionString, jsonListOfPlayers);
		
		String status = "{\"status\": \"ok\", \"errorMessage\": }";
		if ((gameSession.getJsonError() != null) && !gameSession.getJsonError().isEmpty())
			status = "{\"status\": \"error\", \"errorMessage\": \"" + gameSession.getJsonError() + "\"}";
		
		return status;
	}
	
	/**
	 * 
	 * @param sessionUuid
	 * @return
	 */
	@RequestMapping("/advanceTurn/{sessionUuid}")
	public String advanceTurn( @PathVariable String sessionUuid)
	{
		// Calculates new state. Runs algorithm. Updates database.
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\"}";
		}
		Definition definition = gameSession.createDefinitionFromXml();

		List<GameSessionPlayer> players = gameSessionService.fetchListOfGameSessionPlayersBySessionUuid(sessionUuid);
		gameSession.setGameSessionPlayers(players);
		Info info = new Info(gameSession, players);
		StringBuffer script = new StringBuffer("var INFO = new Object();");
		script.append("INFO['num_players'] = " + info.getNumOfPlayers() + ";");
		script.append("INFO['cur_turn'] = " + info.getCurrentTurn() + ";");
		script.append("INFO['players'] = new Object();");
		for (int i = 0; i < players.size(); i++)
		{
			script.append("INFO['players'][" + i + "] = " + players.get(i).getUuid() + ";" );
		}

		// Get configuration parameters
		List<GameSessionVariable> config = gameSessionService.fetchConfigurationBySessionUuid(sessionUuid);
		script.append("CONFIG = new Object();");
		if (config != null)
		{
			for (GameSessionVariable gsv : config)
			{
				script.append("CONFIG['" + gsv.getVariableName()  + "'] = " + gsv.getVariableValue() + ";" );
			}
		}
		
		// Get player choice variables
		script.append("PLAYER_CHOICE_VARIABLES = new Object();");		
		for (VariableType param : definition.getPlayerChoiceVariables().getPlayerChoiceVariable()) 
		{
			script.append("PLAYER_CHOICE_VARIABLES['" + param.getName() + "'] = new Object();");
			for (GameSessionPlayer player : players) 
			{
				script.append("PLAYER_CHOICE_VARIABLES['" + param.getName() + "']['" + player.getUuid() + "'] = new Object();");
				for (int i = 0; i <= gameSession.getCurrentRound(); i++)
				{
					GameSessionRoundPlayerVariable gsrpv = gameSessionRoundService.fetchPlayerChoiceVariableByNameByUuidByRoundByPlayer(gameSession, player.getUuid(), param.getName());
					System.out.println(param.getName() + " - " + player.getUuid() );
					script.append("PLAYER_CHOICE_VARIABLES['" + param.getName() + "']['" + player.getUuid() + "'][" +  i + "] = " + gsrpv.getVariableValue() + ";");
				}
			}
		}
		// Get player state variables
		script.append("PLAYER_STATE_VARIABLES = new Object();");		
		for (PlayerStateVariableDataType param : definition.getPlayerStateVariables().getPlayerStateVariable()) 
		{
			script.append("PLAYER_STATE_VARIABLES['" + param.getName() + "'] = new Object();");
			for (GameSessionPlayer player : players) 
			{
				script.append("PLAYER_STATE_VARIABLES['" + param.getName() + "']['" + player.getUuid() + "'] = new Object();");
				for (int i = 0; i <= gameSession.getCurrentRound(); i++)
				{
					GameSessionRoundPlayerVariable gsrpv = gameSessionRoundService.fetchPlayerStateVariableByNameByUuidByRoundByPlayer(gameSession, player.getUuid(), param.getName());
					script.append("PLAYER_STATE_VARIABLES['" + param.getName() + "']['" + player.getUuid() + "'][" +  i + "] = " + gsrpv.getVariableValue() + ";");
				}
			}
		}
		// Get world state variables
		script.append("WORLD_STATE_VARIABLES = new Object();");		
		for (VariableType param : definition.getWorldStateVariables().getWorldStateVariable()) 
		{
			script.append("WORLD_STATE_VARIABLES['" + param.getName() + "'] = new Object();");
			for (int i = 0; i <= gameSession.getCurrentRound(); i++)
			{
				GameSessionRoundVariable gsrv = gameSessionRoundService.fetchWorldStateVariableByNameByUuidByRound(gameSession, param.getName());
				script.append("WORLD_STATE_VARIABLES['" + param.getName() + "'][" +  i + "] = " + gsrv.getVariableValue() + ";");
			}
		}

		// TODO
		// TO BE DELETED START
/*		script.append("RESULTS = new Object();");
		script.append("RESULTS['WorldStateVariables'] = new Object();");
		script.append("RESULTS['PlayerStateVariables'] = new Object();");
		script.append("RESULTS['PlayerStateVariables']['roundDistanceFromAverage'] = new Object();");
		script.append("RESULTS['PlayerStateVariables']['roundRank'] = new Object();");
		script.append("RESULTS['PlayerStateVariables']['overallScore'] = new Object();");
*/		// TO BE DELETED END
		
		System.out.println("Script: " + script.toString());

		// Results
		String algorithm = definition.getChoicesToStateAlgorithm();
		algorithm += "RESULTS;";
		
		Object obj = runScript(script.toString() + algorithm);
		handleResults((NativeObject)obj, gameSession.getCurrentRound(), players);
		
		// Create next round
		gameSession.setCurrentRound(gameSession.getCurrentRound()+1);
		gameSessionService.saveGameSession(gameSession);

		System.out.println("Next round: " + gameSession.getCurrentRound());
		gameSessionRound.createRound(gameSession, gameSession.getCurrentRound());

		// Save WorldStateVariables
		gameSessionRound.saveWorldStateVariables(definition.getWorldStateVariables().getWorldStateVariable());

		// Save PlayerChoiceVariables
		gameSessionRound.savePlayerChoiceVariables(players, definition.getPlayerChoiceVariables().getPlayerChoiceVariable());

		// Save playerStateVariables
		gameSessionRound.savePlayerStateVariables(players, definition.getPlayerStateVariables().getPlayerStateVariable());		
		
		// "{\"status\": 'ok', \"errorMessage\": }"
		return script.toString();
	}

	/**
	 * Gets variables and set player's choices. Updates database.
	 * 
	 * @param sessionUuid
	 * @param jsonString POST variable. JSON string {"uuid":"1", "choiceVariables":{"numberChoice":"22"}}
	 * @return status
	 */
	@RequestMapping("/submitChoices/{sessionUuid}")
	public String submitChoices( @PathVariable String sessionUuid, 
			@RequestParam(value = "jsonString", required = false) String jsonString)
	{
		// TODO
		// TO BE DELETED
		//jsonString = "{\"uuid\":\"1\", \"choiceVariables\":{\"numberChoice\":\"22\", \"numberChoice\":\"23\"}}";
		//jsonString = "{\"uuid\": \"56030487-0cf0-4f60-b10c-4d8a8fe9baf7\", \"choiceVariables\": {\"numberChoice\": \"10\"}}";
		
		if ((jsonString == null) || (jsonString.isEmpty()))
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"'jsonString' should not be empty.\"}";
		}
		if (gameSessionService.fetchGameSessionByUuid(sessionUuid) == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\"}";
		}
		gameSession.savePlayerChoiceVariables(sessionUuid, jsonString);
		return "{\"status\": \"ok\", \"errorMessage\": }";
	}

	/**
	 * 
	 * @param sessionUuid
	 * @return world state variables as JSON string {world_state_variable_name: value]}
	 */
	@RequestMapping("/getWorldState/{sessionUuid}")
	public String getWorldState( @PathVariable String sessionUuid)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\"}";
		}		
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

	/**
	 * 
	 * @param sessionUuid
	 * @param playerUuid
	 * @return player state variables as JSON string {player_state_variable_name: value]}
	 */
	@RequestMapping("/getPlayerState/{sessionUuid}/{playerUuid}")
	public String getPlayerState( @PathVariable String sessionUuid, @PathVariable String playerUuid)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\"}";
		}		
		List<GameSessionRoundPlayerVariable> playerStateVariables = gameSessionRoundService.fetchPlayerStateVariablesByUuidByRoundByPlayer(gameSession, playerUuid);
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

	/**
	 * 
	 * @param sessionUuid
	 * @param playerUuid
	 * @return player choice variables as JSON string {player_state_variable_name: value]}
	 */
	@RequestMapping("/getPlayerChoices/{sessionUuid}/{playerUuid}")
	public String getPlayerChoices( @PathVariable String sessionUuid, @PathVariable String playerUuid)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\"}";
		}		
		
		List<GameSessionRoundPlayerVariable> playerChoiceVariables = gameSessionRoundService.fetchPlayerChoiceVariablesByUuidByRoundByPlayer(gameSession, playerUuid);
		String json = "{";
		int counter = 1;
		for (GameSessionRoundPlayerVariable gsrv : playerChoiceVariables) 
		{
			json += "\"" + gsrv.getVariableName() + "\":\"" + gsrv.getVariableValue();
			if (counter < playerChoiceVariables.size())
			{
				json += ",";
			}
			counter++;
			System.out.println(gsrv.getVariableName());
		}
		json += "}";
		return json;
	}
	
	/**
	 * 
	 */
	/**
	 * 
	 * @param script
	 * @return
	 */
	private Object runScript(String script) 
	{
		Context cx = Context.enter();
		try
		{
			Scriptable scope = cx.initStandardObjects();
			Object obj = cx .evaluateString(scope, script, "TestScript", 1, null);
			
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Context.exit();
		}
		return null;
	}

	/**
	 * 
	 * @param res
	 * @param roundNum 
	 * @param players 
	 */
	private void handleResults(NativeObject res, long roundNum, List<GameSessionPlayer> players) 
	{
		List<GameSessionRoundVariable> worldStateVariables = new ArrayList<GameSessionRoundVariable>();
		GameSessionRoundVariable gameSessionRoundVariable;
		
		List<GameSessionRoundPlayerVariable> playerStateVariables = new ArrayList<GameSessionRoundPlayerVariable>();
		GameSessionRoundPlayerVariable gameSessionRoundPlayerVariable;
		
		for (Entry<Object, Object> p : res.entrySet()) 
		{
			NativeObject r1 = (NativeObject)p.getValue();
            for (Entry<Object, Object> p1 : r1.entrySet()) 
            {
				if (p.getKey().equals("WorldStateVariables"))
				{
					System.out.println(p.getKey() + ": " + p1.getKey() + ": " + p1.getValue());
					gameSessionRoundVariable = new GameSessionRoundVariable(WORLD_STATE_VARIABLE, p1.getKey(), p1.getValue().toString(), gameSession.getUuidOfGameSession(), roundNum);
					worldStateVariables.add(gameSessionRoundVariable);
				}
				if (p.getKey().equals("PlayerStateVariables"))
				{
					NativeObject r2 = (NativeObject)p1.getValue();
		            for (Entry<Object, Object> p2 : r2.entrySet()) 
		            {
						System.out.println(p.getKey() + ": " + p1.getKey() + ": " + p2.getKey() + ": " + p2.getValue());	
						gameSessionRoundPlayerVariable = new GameSessionRoundPlayerVariable(PLAYER_STATE_VARIABLE, p1.getKey(), p1.getValue().toString(), 
								gameSession.getUuidOfGameSession(), roundNum, p1.getKey().toString());
						playerStateVariables.add(gameSessionRoundPlayerVariable);
		            }	            	
	            }
			}

		}
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

	/**
	 * @return the gameSessionRoundService
	 */
	public GameSessionRoundService getGameSessionRoundService()
	{
		return gameSessionRoundService;
	}

	/**
	 * @param gameSessionRoundService the gameSessionRoundService to set
	 */
	public void setGameSessionRoundService(
			GameSessionRoundService gameSessionRoundService)
	{
		this.gameSessionRoundService = gameSessionRoundService;
	}

	/**
	 * @return the gameSessionRound
	 */
	public GameSessionRound getGameSessionRound()
	{
		return gameSessionRound;
	}

	/**
	 * @param gameSessionRound the gameSessionRound to set
	 */
	public void setGameSessionRound(GameSessionRound gameSessionRound)
	{
		this.gameSessionRound = gameSessionRound;
	}

}
