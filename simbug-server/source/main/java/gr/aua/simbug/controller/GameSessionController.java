package gr.aua.simbug.controller;

import gr.aua.simbug.beans.Info;
import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.PlayerStateVariableDataType;
import gr.aua.simbug.definition.VariableType;
import gr.aua.simbug.game.BaseSession;
import gr.aua.simbug.game.GameConstants;
import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionPlayer;
import gr.aua.simbug.game.GameSessionRound;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;
import gr.aua.simbug.game.GameSessionVariable;
import gr.aua.simbug.utils.SimbugUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@RestController
public class GameSessionController extends BaseSession implements GameConstants, ServletContextAware 
{	
	private static ServletContext servletContext;

	public void setServletContext(ServletContext context) {
	     servletContext = context;
	}
	//@Autowired
	private GameSession gameSession = new GameSession();
	
	//@Autowired
	private GameSessionRound gameSessionRound = new GameSessionRound();

	List<GameSessionRoundVariable> worldStateVariables = new ArrayList<GameSessionRoundVariable>();
	List<GameSessionRoundPlayerVariable> playerStateVariables = new ArrayList<GameSessionRoundPlayerVariable>();

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
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' already exists\", \"result\":\"\"}";
		}
		
		// TODO
		// TO BE DELETED START
/*		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String definitionFile = "G:/Java/Eclipse/simbug/simbug/simbug-server/source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
		//definitionFile = "/home/michael/applications/eclipse/simbug/simbug/simbug-server/source/test/java/gr/aua/simbug/tests/hello_world.def2.xml";
		definitionString = SimbugUtils.fileToString(definitionFile);
		jsonListOfPlayers = "[{\"uuid\":\"56030487-0cf0-4f60-b10c-4d8a8fe9baf7\"}, {\"uuid\":\"561dec18-8d0c-41f9-833b-1b848fe9baf7\"}]";
		// jsonListOfPlayers = "[{\"uuid\":\"56030487-0cf0-4f60-b10c-4d8a8fe9baf7\"},{\"uuid\":\"561dec18-8d0c-41f9-833b-1b848fe9baf7\"},{\"uuid\":\"7ed8b252-611a-11e5-9d70-feff819cdc9f\"}]";
		jsonListOfPlayers = "[{\"uuid\":\"1\"}, {\"uuid\":\"2\"}]";
		// TO BE DELETED END
*/
		if ((definitionString == null) || (definitionString.isEmpty()))
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"'definitionString' should not be empty.\", \"result\":\"\"}";	
		}
		if ((jsonListOfPlayers == null) || (jsonListOfPlayers.isEmpty()))
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"'listOfPlayers' should not be empty.\", \"result\":\"\"}";	
		}
		// Create game session. Update database
		gameSession = new GameSession();
		gameSession.setGameSessionPlayerService(gameSessionPlayerService);
		gameSession.setGameSessionService(gameSessionService);
		gameSession.setGameSessionRoundService(gameSessionRoundService);
		gameSession.createGameSession(sessionUuid, definitionString, jsonListOfPlayers);
		
		String status = "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":\"\" }";
		if ((gameSession.getJsonError() != null) && !gameSession.getJsonError().isEmpty())
			status = "{\"status\": \"error\", \"errorMessage\": \"" + gameSession.getJsonError() + "\", \"result\":\"\"}";
		
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
		String status = "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":\"\"}";
		// Calculates new state. Runs algorithm. Updates database.
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\", \"result\":\"\"}";
		}
		List<GameSessionPlayer> players = gameSessionService.fetchListOfGameSessionPlayersBySessionUuid(sessionUuid);
		gameSession.setGameSessionPlayers(players);
		
		String script = createAlgorithmScript(gameSession); 		
		Object obj = runScript(script);			
		if (obj == null)
		{
			status = "{\"status\": \"Error\", \"errorMessage\":\"Error in javascript.\", \"result\":\"" + script + "\"}";
			return status;
		}
		handleResults((NativeObject)obj, gameSession.getCurrentRound(), players);
		
		// Create next round
		gameSession.setCurrentRound(gameSession.getCurrentRound()+1);
		gameSessionService.saveGameSession(gameSession);

		System.out.println("Next round: " + gameSession.getCurrentRound());
		gameSessionRound.setGameSessionPlayerService(gameSessionPlayerService);
		gameSessionRound.setGameSessionService(gameSessionService);
		gameSessionRound.setGameSessionRoundService(gameSessionRoundService);
		gameSessionRound.createRound(gameSession, gameSession.getCurrentRound());

		// Save WorldStateVariables
		gameSessionRound.saveSessionWorldStateVariables(worldStateVariables);

		// Save PlayerChoiceVariables
		gameSessionRound.saveSessionPlayerChoiceVariables(players);

		// Save playerStateVariables
		gameSessionRound.saveSessionPlayerStateVariables(playerStateVariables);		
		
		return status;
	}

	/**
	 * 
	 * @param sessionUuid
	 * @return
	 */
	@RequestMapping("/getScript/{sessionUuid}")
	public String getScript( @PathVariable String sessionUuid)
	{
		String status = "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":\"\"}";
		// Calculates new state. Runs algorithm. Updates database.
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\", \"result\":\"\"}";
		}
		List<GameSessionPlayer> players = gameSessionService.fetchListOfGameSessionPlayersBySessionUuid(sessionUuid);
		gameSession.setGameSessionPlayers(players);
		
		String script = createAlgorithmScript(gameSession); 		

		status = "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":\"" + script + "\"}";
		return status;
	}
	
	/**
	 * 
	 * @param gameSession
	 * @param players
	 * @return
	 */
	private String createAlgorithmScript(GameSession gameSession)
	{
		Definition definition = gameSession.createDefinitionFromXml();
		String sessionUuid = gameSession.getUuidOfGameSession();
		List<GameSessionPlayer> players = gameSession.getGameSessionPlayers();
		
		Info info = new Info(gameSession, players);
		StringBuffer script = new StringBuffer("var INFO = new Object();");
		script.append("INFO['num_players'] = " + info.getNumOfPlayers() + ";");
		script.append("INFO['cur_turn'] = " + info.getCurrentTurn() + ";");
		script.append("INFO['players'] = new Object();");
		for (int i = 0; i < players.size(); i++)
		{
			script.append("INFO['players'][" + i + "] = '" + players.get(i).getUuid() + "';" );
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
		
		//System.out.println("Script: " + script.toString());

		// Results
		String algorithm = definition.getChoicesToStateAlgorithm();
		algorithm += "RESULTS;";
		
		System.out.println("Script: " + script.toString() + algorithm);
		return script.toString() + algorithm;
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
/*		jsonString = "{\"uuid\":\"1\", \"choiceVariables\":{\"numberChoice\":\"22\"}}";
		jsonString = "{\"uuid\":\"2\", \"choiceVariables\":{\"numberChoice\":\"44\"}}";
*/		//jsonString = "{\"uuid\": \"56030487-0cf0-4f60-b10c-4d8a8fe9baf7\", \"choiceVariables\": {\"numberChoice\": \"10\"}}";
		
		if ((jsonString == null) || (jsonString.isEmpty()))
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"'jsonString' should not be empty.\", \"result\":\"\"}";
		}
		if (gameSessionService.fetchGameSessionByUuid(sessionUuid) == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\", \"result\":\"\"}";
		}
		gameSession.setGameSessionPlayerService(gameSessionPlayerService);
		gameSession.setGameSessionService(gameSessionService);
		gameSession.setGameSessionRoundService(gameSessionRoundService);
		gameSession.savePlayerChoiceVariables(sessionUuid, jsonString);
		return "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":\"\"}";
	}

	/**
	 * 
	 * @param sessionUuid
	 * @return world state variables as JSON string {world_state_variable_name: value]}
	 */
	@RequestMapping("/getWorldState/{sessionUuid}/{round}")
	public String getWorldState( @PathVariable String sessionUuid, @PathVariable String round)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\", \"result\":\"\"}";
		}		
		if ((round != null) && (!round.isEmpty()))
		{
			gameSession.setCurrentRound(Long.valueOf(round));
		}
		List<GameSessionRoundVariable> worldStateVariables = gameSessionRoundService.fetchWorldStateVariablesByUuidByRound(gameSession);
		String json = "{";
		int counter = 1;
		for (GameSessionRoundVariable gsrv : worldStateVariables) 
		{
			json += "\"" + gsrv.getVariableName() + "\":\"" + gsrv.getVariableValue() + "\"";
			if (counter < worldStateVariables.size())
			{
				json += ",";
			}
			counter++;
			System.out.println(gsrv.getVariableName());
		}
		json += "}";
		String status = "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":" + json + "}";
		return status;
	}

	/**
	 * 
	 * @param sessionUuid
	 * @param playerUuid
	 * @return player state variables as JSON string {player_state_variable_name: value]}
	 */
	@RequestMapping("/getPlayerState/{sessionUuid}/{playerUuid}/{round}")
	public String getPlayerState( @PathVariable String sessionUuid, @PathVariable String playerUuid, @PathVariable String round)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\"}";
		}		
		if ((round != null) && (!round.isEmpty()))
		{
			gameSession.setCurrentRound(Long.valueOf(round));
		}
		List<GameSessionRoundPlayerVariable> playerStateVariables = gameSessionRoundService.fetchPlayerStateVariablesByUuidByRoundByPlayer(gameSession, playerUuid);
		String json = "{";
		int counter = 1;
		for (GameSessionRoundPlayerVariable gsrv : playerStateVariables) 
		{
			json += "\"" + gsrv.getVariableName() + "\":\"" + gsrv.getVariableValue() + "\"";
			if (counter < playerStateVariables.size())
			{
				json += ",";
			}
			counter++;
			System.out.println(gsrv.getVariableName());
		}
		json += "}";
		String status = "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":" + json + "}";
		return status;
	}

	/**
	 * 
	 * @param sessionUuid
	 * @param playerUuid
	 * @return player choice variables as JSON string {player_state_variable_name: value]}
	 */
	@RequestMapping("/getPlayerChoices/{sessionUuid}/{playerUuid}/{round}")
	public String getPlayerChoices( @PathVariable String sessionUuid, @PathVariable String playerUuid, @PathVariable String round)
	{
		// Gets session
		gameSession = gameSessionService.fetchGameSessionByUuid(sessionUuid);
		if (gameSession == null)
		{
			return "{\"status\": \"Error\", \"errorMessage\": \"sessionUuid '" +  sessionUuid + "' not exists\"}";
		}		
		if ((round != null) && (!round.isEmpty()))
		{
			gameSession.setCurrentRound(Long.valueOf(round));
		}
		List<GameSessionRoundPlayerVariable> playerChoiceVariables = gameSessionRoundService.fetchPlayerChoiceVariablesByUuidByRoundByPlayer(gameSession, playerUuid);
		String json = "{";
		int counter = 1;
		for (GameSessionRoundPlayerVariable gsrv : playerChoiceVariables) 
		{
			json += "\"" + gsrv.getVariableName() + "\":\"" + gsrv.getVariableValue() + "\"";
			if (counter < playerChoiceVariables.size())
			{
				json += ",";
			}
			counter++;
			System.out.println(gsrv.getVariableName());
		}
		json += "}";
		String status = "{\"status\": \"ok\", \"errorMessage\":\"\", \"result\":" + json + "}";
		return status;
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
		GameSessionRoundVariable gameSessionRoundVariable;
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
						String catName = String.valueOf(p.getKey());
						String varName = String.valueOf(p1.getKey());
						String playerId = String.valueOf(p2.getKey());
						String varValue = String.valueOf(p2.getValue());
						System.out.println(catName + ": " + varName + ": " + playerId + ": " + varValue);	
						gameSessionRoundPlayerVariable = new GameSessionRoundPlayerVariable(PLAYER_STATE_VARIABLE, varName, varValue, 
								gameSession.getUuidOfGameSession(), roundNum, playerId);
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

	public static Object getService(String serviceName)
	{
		if (servletContext != null)
		{
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			if (serviceName.equals("gameSessionRoundService"))
			{
				return wac.getBean("gameSessionRoundService");
			}
			else if (serviceName.equals("gameSessionService"))
			{
				return wac.getBean("gameSessionService");
			}
			else if (serviceName.equals("gameSessionService"))
			{
				return wac.getBean("gameSessionService");
			}
		}
		return null;
	}
}
