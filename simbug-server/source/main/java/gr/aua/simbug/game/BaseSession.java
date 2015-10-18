package gr.aua.simbug.game;

import gr.aua.simbug.service.GameSessionPlayerService;
import gr.aua.simbug.service.GameSessionRoundService;
import gr.aua.simbug.service.GameSessionService;

import org.springframework.beans.factory.annotation.Autowired;


public class BaseSession
{
	/**
	 * The GameSessionRound service.
	 */
	@Autowired
	protected GameSessionRoundService gameSessionRoundService;
	
	/**
	 * The GameSession service.
	 */
	@Autowired
	protected GameSessionService gameSessionService;

	/**
	 * The GameSessionPlayer service.
	 */
	@Autowired
	protected GameSessionPlayerService gameSessionPlayerService;
	
	public BaseSession()
	{
		super();
	}

	
	public GameSessionService getGameSessionService() {
		return gameSessionService;
	}

	public void setGameSessionService(GameSessionService gameSessionService) {
		this.gameSessionService = gameSessionService;
	}

	public GameSessionPlayerService getGameSessionPlayerService() {
		return gameSessionPlayerService;
	}

	public void setGameSessionPlayerService(
			GameSessionPlayerService gameSessionPlayerService) {
		this.gameSessionPlayerService = gameSessionPlayerService;
	}

	public GameSessionRoundService getGameSessionRoundService() {
		return gameSessionRoundService;
	}

	public void setGameSessionRoundService(
			GameSessionRoundService gameSessionRoundService) {
		this.gameSessionRoundService = gameSessionRoundService;
	}

}
