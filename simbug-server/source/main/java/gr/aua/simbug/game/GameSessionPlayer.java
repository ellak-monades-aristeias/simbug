package gr.aua.simbug.game;

import gr.aua.simbug.beans.Player;
import gr.aua.simbug.model.DbGameSessionPlayer;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GameSessionPlayer extends Player
{
	
	private GameSession gameSession;

	public GameSessionPlayer()
	{
		super();
		System.out.println("creating bean GameSessionPlayer: " + this);
	}

	public GameSessionPlayer(DbGameSessionPlayer dbgsrv) 
	{
		this.setUuid(dbgsrv.getPlayerUuid());
	}

	public void createSessionPlayer(GameSessionPlayer gsp, GameSession gs) 
	{
		this.setUuid(gsp.getUuid());
		this.gameSession = gs;
	}

	/**
	 * 
	 * @param gs
	 */
	public void saveChoiceVariables(GameSession gs) 
	{
		// update Session
		for (Map.Entry<String, String> entry : getChoiceVariables().entrySet()) 
		{
			GameSessionRoundPlayerVariable gsrpv = new GameSessionRoundPlayerVariable();
			gsrpv.setUuidOfGameSession(gs.getUuidOfGameSession());
			gsrpv.setRoundNum(gs.getCurrentRound());
			gsrpv.setPlayerUuid(this.getUuid());
			gsrpv.setVariableName(entry.getKey());
			gsrpv.setVariableValue(entry.getValue());
			
			gameSessionPlayerService.updateRoundPlayerVariable(gsrpv);
		}		
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

}
