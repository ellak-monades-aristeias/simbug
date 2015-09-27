package gr.aua.simbug.service.impl;

import gr.aua.simbug.dao.GameSessionDAO;
import gr.aua.simbug.dao.GameSessionVariableDAO;
import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionVariable;
import gr.aua.simbug.model.DbGameSession;
import gr.aua.simbug.model.DbGameSessionVariable;
import gr.aua.simbug.service.GameSessionService;

import org.springframework.beans.factory.annotation.Autowired;

public class GameSessionServiceImpl implements GameSessionService
{
	/**
	 * The GameSession DAO.
	 */
	@Autowired
	private GameSessionDAO gameSessionDAO;


	/**
	 * The GameSessionVariable DAO.
	 */
	@Autowired
	private GameSessionVariableDAO gameSessionVariableDAO;

	@Override
	public void saveGameSession(GameSession gameSession) 
	{
		DbGameSession dbGameSession = new DbGameSession(gameSession);
		gameSessionDAO.save(dbGameSession);
	}


	@Override
	public void deleteGameSession(String uuidOfGameSession) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public GameSession fetchGameSessionByUuid() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	public GameSessionDAO getGameSessionDAO() 
	{
		return gameSessionDAO;
	}


	public void setGameSessionDAO(GameSessionDAO gameSessionDAO) 
	{
		this.gameSessionDAO = gameSessionDAO;
	}


	@Override
	public void saveGameSessionVariable(GameSessionVariable gsv) 
	{
		DbGameSessionVariable dbGameSessionVariable = new DbGameSessionVariable(gsv);
		//DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gsv.getUuidOfGameSession());
		gameSessionVariableDAO.save(dbGameSessionVariable);

	}

}
