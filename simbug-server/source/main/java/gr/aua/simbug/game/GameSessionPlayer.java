package gr.aua.simbug.game;

import java.util.Map;

import gr.aua.simbug.beans.Player;
import gr.aua.simbug.service.GameSessionPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSessionPlayer extends Player
{
	
	/**
	 * The GameSessionPlayer service.
	 */
	@Autowired
	private GameSessionPlayerService gameSessionPlayerService;
	
	private GameSession gameSession;

	public GameSessionPlayer()
	{
		super();
	}

	public void createSessionPlayer(GameSessionPlayer gsp, GameSession gs) 
	{
		this.setUuid(gsp.getUuid());
		this.gameSession = gs;
	}

	/**
	 * Saves the player into the database
	 */
	public void save() 
	{
		gameSessionPlayerService.save(this);		
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

	public GameSessionPlayerService getGameSessionPlayerService() 
	{
		return gameSessionPlayerService;
	}

	public void setGameSessionPlayerService(GameSessionPlayerService gameSessionPlayerService) 
	{
		this.gameSessionPlayerService = gameSessionPlayerService;
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

}
