package gr.aua.simbug.game;

import java.util.ArrayList;
import java.util.List;


public class GameSessionRoundPlayer extends GameSessionPlayer
{
	private List<GameSessionRoundPlayerVariable> playerChoiceVariables = new ArrayList<GameSessionRoundPlayerVariable>();
	private List<GameSessionRoundPlayerVariable> playerStateVariables = new ArrayList<GameSessionRoundPlayerVariable>();

	public GameSessionRoundPlayer()
	{
		super();
	}

	public List<GameSessionRoundPlayerVariable> getPlayerChoiceVariables() {
		return playerChoiceVariables;
	}

	public void setPlayerChoiceVariables(
			List<GameSessionRoundPlayerVariable> playerChoiceVariables) {
		this.playerChoiceVariables = playerChoiceVariables;
	}

	public List<GameSessionRoundPlayerVariable> getPlayerStateVariables() {
		return playerStateVariables;
	}

	public void setPlayerStateVariables(
			List<GameSessionRoundPlayerVariable> playerStateVariables) {
		this.playerStateVariables = playerStateVariables;
	}


}
