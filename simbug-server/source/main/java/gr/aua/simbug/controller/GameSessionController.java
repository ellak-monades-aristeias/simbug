package gr.aua.simbug.controller;

import gr.aua.simbug.game.GameSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSessionController
{
	@RequestMapping("/initGameSession/{UuidOfGameSession}")
	public String initGameSession(@PathVariable String UuidOfGameSession, 
			@RequestParam("definitionString") String definitionString, @RequestParam("listOfPlayers") String jsonListOfPlayers)
	{
		GameSession gameSession = new GameSession(UuidOfGameSession, definitionString, jsonListOfPlayers);
		
		return UuidOfGameSession;
	}
	
	@RequestMapping("/advanceTurn/{UuidOfGameSession}")
	public String advanceTurn( @PathVariable String UuidOfGameSession)
	{
		return UuidOfGameSession;
	}

	@RequestMapping("/submitChoices/{UuidOfGameSession}")
	public String submitChoices( @PathVariable String UuidOfGameSession, @RequestParam("jsonString") String jsonString)
	{
		// POST variables:
		// JSON string {player_uuid: array[choice_variable_name: value]} 
		// Returns: "ok"

		return UuidOfGameSession;
	}

	@RequestMapping("/getWorldState/{UuidOfGameSession}")
	public String getWorldState( @PathVariable String UuidOfGameSession)
	{
		// Returns: JSON string of world variables {world_state_variable_name: value]}

		return UuidOfGameSession;
	}

	@RequestMapping("/getPlayerState/{UuidOfGameSession}/{UuidOfPlayer}")
	public String getPlayerState( @PathVariable String UuidOfGameSession, @PathVariable String UuidOfPlayer)
	{
		// Returns: JSON string of world variables {user_state_variable_name: value]}

		return UuidOfGameSession + "/" + UuidOfPlayer;
	}

}
