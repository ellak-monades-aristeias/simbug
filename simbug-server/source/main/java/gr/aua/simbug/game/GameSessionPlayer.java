package gr.aua.simbug.game;

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
