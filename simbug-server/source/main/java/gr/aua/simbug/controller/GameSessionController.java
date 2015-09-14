package gr.aua.simbug.controller;

import java.util.List;

import gr.aua.simbug.beans.Player;
import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.ExternalDataType;
import gr.aua.simbug.definition.PlayerStateVariableDataType;
import gr.aua.simbug.definition.VariableType;
import gr.aua.simbug.utils.SimbugUtils;

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
		Definition definition = SimbugUtils.xmlStringToDefinition(definitionString);
		System.out.println(definition.getConfiguration().getClass());
		System.out.println(definition.getRandomNumberGenerators());

		System.out.println("\nExternalParameters"); //ExternalParameters
		for (ExternalDataType param : definition.getExternalParameters().getExternalVariable())
		{
			System.out.println(param.getName() + "-" + param.getValue() + "-" + param.getType());
		}
		System.out.println("\nWorldStateVariables"); //WorldStateVariables
		for (VariableType param : definition.getWorldStateVariables().getWorldStateVariable())
		{
			System.out.println(param.getName() + "-" + param.getType());
		}
		System.out.println("\nChoicesToStateAlgorithm"); //ChoicesToStateAlgorithm
		//System.out.println(definition.getChoicesToStateAlgorithm());

		System.out.println("\n\nListOfPlayers"); //ListOfPlayers
		System.out.println(jsonListOfPlayers);
		List<Player> listOfPlayers = SimbugUtils.jsonToList(jsonListOfPlayers);
		System.out.println("\nPlayerChoiceVariables"); //PlayerChoiceVariables
		for (VariableType param : definition.getPlayerChoiceVariables().getPlayerChoiceVariable())
		{
			System.out.println(param.getName() + "-" + param.getType());
		}
		System.out.println("\nPlayerStateVariables"); //PlayerStateVariables
		for (PlayerStateVariableDataType param : definition.getPlayerStateVariables().getPlayerStateVariable())
		{
			System.out.println(param.getName() + "-" + param.getType());
		}
		
		return UuidOfGameSession;
	}
	
	@RequestMapping("/advanceTurn/{UuidOfGameSession}")
	public String advanceTurn( @PathVariable String UuidOfGameSession)
	{
		return UuidOfGameSession;
	}

	@RequestMapping("/submitChoices/{UuidOfGameSession}")
	public String submitChoices( @PathVariable String UuidOfGameSession)
	{
		return UuidOfGameSession;
	}

	@RequestMapping("/getWorldState/{UuidOfGameSession}")
	public String getWorldState( @PathVariable String UuidOfGameSession)
	{
		return UuidOfGameSession;
	}

	@RequestMapping("/getPlayerState/{UuidOfGameSession}/{UuidOfPlayer}")
	public String getPlayerState( @PathVariable String UuidOfGameSession, @PathVariable String UuidOfPlayer)
	{
		return UuidOfGameSession + "/" + UuidOfPlayer;
	}

}
