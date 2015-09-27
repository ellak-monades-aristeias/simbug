package gr.aua.simbug.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GameSessionRoundPlayer extends GameSessionPlayer
{
	private List<GameSessionRoundPlayerVariable> playerChoiceVariables = new ArrayList<GameSessionRoundPlayerVariable>();
	private List<GameSessionRoundPlayerVariable> playerStateVariables = new ArrayList<GameSessionRoundPlayerVariable>();

	private long roundNum;
	
	public GameSessionRoundPlayer()
	{
		super();
	}

	public void createSessionRoundPlayer(GameSessionPlayer player, GameSessionRound gameSessionRound) 
	{
		this.setUuid(player.getUuid());
		this.setGameSession(gameSessionRound.getGameSession());
		this.roundNum = gameSessionRound.getRoundNum();
	}

	/**
	 * 
	 */
	public void saveSessionRoundPlayer() 
	{
		getGameSessionPlayerService().saveRoundPlayer(this);		
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

	public long getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(long roundNum) {
		this.roundNum = roundNum;
	}

}
