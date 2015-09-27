package gr.aua.simbug.controller;

import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.utils.SimbugUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSessionController
{
	@Autowired
	private GameSession gameSession;
	
	@RequestMapping("/initGameSession/{UuidOfGameSession}")
	public String initGameSession(@PathVariable String UuidOfGameSession, 
			@RequestParam(value = "definitionString", required = false) String definitionString, 
			@RequestParam(value = "listOfPlayers", required = false) String jsonListOfPlayers)
	{
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String definitionFile = "G:/Java/Eclipse/simbug/simbug/simbug-server/source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
		definitionString = SimbugUtils.fileToString(definitionFile);
		jsonListOfPlayers = "[{\"uuid\":\"1\"}, {\"uuid\":\"2\"}, {\"uuid\":\"3\"}]";
		
		// Create game session. Update database
		gameSession.createGameSession(UuidOfGameSession, definitionString, jsonListOfPlayers);
		
		return UuidOfGameSession;
	}
	
	@RequestMapping("/advanceTurn/{UuidOfGameSession}")
	public String advanceTurn( @PathVariable String UuidOfGameSession)
	{
		// Calculates new state. Runs algorithm. Updates database.
		return UuidOfGameSession;
	}

	@RequestMapping("/submitChoices/{UuidOfGameSession}")
	public String submitChoices( @PathVariable String UuidOfGameSession, @RequestParam("jsonString") String jsonString)
	{
		// Gets variables and set player's choices. Updates database.
		
		// POST variables:
		// JSON string {player_uuid: array[choice_variable_name: value]} 
		// Returns: "ok"

		return UuidOfGameSession;
	}

	@RequestMapping("/getWorldState/{UuidOfGameSession}")
	public String getWorldState( @PathVariable String UuidOfGameSession)
	{
		// Returns world state variables.
		
		// Returns: JSON string of world variables {world_state_variable_name: value]}

		return UuidOfGameSession;
	}

	@RequestMapping("/getPlayerState/{UuidOfGameSession}/{UuidOfPlayer}")
	public String getPlayerState( @PathVariable String UuidOfGameSession, @PathVariable String UuidOfPlayer)
	{
		// Returns player state variables.
		
		// Returns: JSON string of world variables {user_state_variable_name: value]}

		return UuidOfGameSession + "/" + UuidOfPlayer;
	}

	public GameSession getGameSession() 
	{
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) 
	{
		this.gameSession = gameSession;
	}

}
