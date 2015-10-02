package gr.aua.simbug.controller;

import gr.aua.simbug.beans.Info;
import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.VariableType;
import gr.aua.simbug.game.GameConstants;
import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionPlayer;
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
	
	
	@RequestMapping("/initGameSession/{sessionUuid}")
	public String initGameSession(@PathVariable String sessionUuid, 
			@RequestParam(value = "definitionString", required = false) String definitionString, 
			@RequestParam(value = "listOfPlayers", required = false) String jsonListOfPlayers)
	{
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession != null)
		{
			return "Error: SessionId already exists";
		}
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String definitionFile = "G:/Java/Eclipse/simbug/simbug/simbug-server/source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
		definitionString = SimbugUtils.fileToString(definitionFile);
		jsonListOfPlayers = "[{\"uuid\":\"1\"}, {\"uuid\":\"2\"}, {\"uuid\":\"3\"}]";
		
		// Create game session. Update database
		gameSession.createGameSession(sessionUuid, definitionString, jsonListOfPlayers);
		
		return sessionUuid;
	}
	
	@RequestMapping("/advanceTurn/{sessionUuid}")
	public String advanceTurn( @PathVariable String sessionUuid)
	{
		// Calculates new state. Runs algorithm. Updates database.
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		List<GameSessionPlayer> players = gameSessionService.fetchListOfGameSessionPlayersBySessionUuid(sessionUuid);
		Info info = new Info(gameSession, players);
		StringBuffer script = new StringBuffer("var INFO = new Object();");
		script.append("INFO.num_players = " + info.getNumOfPlayers() + ";");
		script.append("INFO.cur_turn = " + info.getCurrentTurn() + ";");
		script.append("INFO.players = new Object();");
		for (int i = 0; i < players.size(); i++)
		{
			script.append("INFO.players[" + i + "] = " + players.get(i).getUuid() + ";" );
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
		for (int i = 1; i <= gameSession.getCurrentRound(); i++)
		{
			script.append("PLAYER_CHOICE_VARIABLES[" + i + "] = new Object();");
			for (GameSessionPlayer player : players) 
			{
				script.append("PLAYER_CHOICE_VARIABLES[" + i + "]['" + player.getUuid() + "'] = new Object();");
				List<GameSessionRoundPlayerVariable> gsrpvs = gameSessionRoundService.fetchPlayerChoiceVariablesByUuidByRoundByPlayer(gameSession, player.getUuid());
				for (GameSessionRoundPlayerVariable gsrpv : gsrpvs) 
				{
					script.append("PLAYER_CHOICE_VARIABLES[" + i + "]['" + player.getUuid() + "']['" +  gsrpv.getVariableName() + "'] = " + gsrpv.getVariableValue() + ";");
				}
			}
		}
		
		// Get player state variables
		script.append("PLAYER_STATE_VARIABLES = new Object();");
		for (int i = 1; i <= gameSession.getCurrentRound(); i++)
		{
			script.append("PLAYER_STATE_VARIABLES[" + i + "] = new Object();");
			for (GameSessionPlayer player : players) 
			{
				script.append("PLAYER_STATE_VARIABLES[" + i + "]['" + player.getUuid() + "'] = new Object();");
				List<GameSessionRoundPlayerVariable> gsrpvs = gameSessionRoundService.fetchPlayerStateVariablesByUuidByRoundByPlayer(gameSession, player.getUuid());
				for (GameSessionRoundPlayerVariable gsrpv : gsrpvs) 
				{
					script.append("PLAYER_STATE_VARIABLES[" + i + "]['" + player.getUuid() + "']['" +  gsrpv.getVariableName() + "'] = " + gsrpv.getVariableValue() + ";");
				}
			}
		}
	
		// Get world state variables
		script.append("WORLD_STATE_VARIABLES = new Object();");
		for (int i = 1; i <= gameSession.getCurrentRound(); i++)
		{
			script.append("WORLD_STATE_VARIABLES[" + i + "] = new Object();");
			List<GameSessionRoundVariable> gsrpvs = gameSessionRoundService.fetchWorldStateVariablesByUuidByRound(gameSession);
			for (GameSessionRoundVariable gsrpv : gsrpvs) 
			{
				script.append("WORLD_STATE_VARIABLES[" + i + "]['" +  gsrpv.getVariableName() + "'] = " + gsrpv.getVariableValue() + ";");
			}
		}

		// Results
		Definition definition = gameSession.createDefinitionFromXml();
		String algorithm = definition.getChoicesToStateAlgorithm();
		
		Object obj = runScript(script.toString() + algorithm);
		System.out.println("Object: " + obj);
		handleResults((NativeObject)obj);

		return script.toString();
	}

	@RequestMapping("/submitChoices/{sessionUuid}")
	public String submitChoices( @PathVariable String sessionUuid, 
			@RequestParam(value = "jsonString", required = false) String jsonString)
	{
		// Gets variables and set player's choices. Updates database.
		
		// POST variables:
		// JSON string {"uuid":"1", "choiceVariables":{"numberChoice":"22"}} 

		gameSession.savePlayerChoiceVariables(sessionUuid, jsonString);
		// Returns: "ok"
		return "Session: " + sessionUuid + " - Choice variables set OK.";
	}

	@RequestMapping("/getWorldState/{sessionUuid}")
	public String getWorldState( @PathVariable String sessionUuid)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		
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

	@RequestMapping("/getPlayerState/{sessionUuid}/{playerUuid}")
	public String getPlayerState( @PathVariable String sessionUuid, @PathVariable String playerUuid)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		
		// Returns player state variables.	
		// Returns: JSON string of world variables {user_state_variable_name: value]}
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
	 */
	private void handleResults(NativeObject res) 
	{
		List<GameSessionRoundVariable> worldStateVariables = new ArrayList<GameSessionRoundVariable>();
		GameSessionRoundVariable gameSessionRoundVariable;
//		for (VariableType param : worldStateVariables) 
//		{
//			gameSessionRoundVariable.createGameSessionRoundVariable(WORLD_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum);
//			gameSessionRoundVariable.saveSessionRoundVariable();
//			System.out.println(param.getName() + "-" + param.getType());
//		}
		
		List<GameSessionRoundPlayerVariable> playerStateVariables = new ArrayList<GameSessionRoundPlayerVariable>();
		GameSessionRoundPlayerVariable gameSessionRoundPlayerVariable;
//		for (PlayerStateVariableDataType param : variables) 
//		{
//			gameSessionRoundPlayerVariable.createSessionRoundPlayerVariable(PLAYER_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum, player.getUuid());
//			gameSessionRoundPlayerVariable.saveSessionRoundPlayerVariable();
//			System.out.println(param.getName() + "-" + param.getType());
//		}
		
		for (Entry<Object, Object> p : res.entrySet()) 
		{
			NativeObject r1 = (NativeObject)p.getValue();
            for (Entry<Object, Object> p1 : r1.entrySet()) 
            {
				if (p.getKey().equals("world"))
				{
					System.out.println(p.getKey() + ": " + p1.getKey() + ": " + p1.getValue());
					//gameSessionRoundVariable = new GameSessionRoundVariable(WORLD_STATE_VARIABLE, p1.getKey(), p1.getValue(), gameSession.getUuidOfGameSession(), roundNum);
					//gameSessionRoundVariable.createGameSessionRoundVariable(WORLD_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum);
				}
				if (p.getKey().equals("player"))
				{
					NativeObject r2 = (NativeObject)p1.getValue();
		            for (Entry<Object, Object> p2 : r2.entrySet()) 
		            {
						System.out.println(p.getKey() + ": " + p1.getKey() + ": " + p2.getKey() + ": " + p2.getValue());			            	
						//gameSessionRoundPlayerVariable.createSessionRoundPlayerVariable(PLAYER_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum, player.getUuid());
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

}
