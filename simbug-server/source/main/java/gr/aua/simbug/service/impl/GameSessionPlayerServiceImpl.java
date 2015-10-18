package gr.aua.simbug.service.impl;

import gr.aua.simbug.dao.GameSessionDAO;
import gr.aua.simbug.dao.GameSessionPlayerDAO;
import gr.aua.simbug.dao.GameSessionRoundPlayerDAO;
import gr.aua.simbug.dao.GameSessionRoundPlayerVariableDAO;
import gr.aua.simbug.game.GameSessionPlayer;
import gr.aua.simbug.game.GameSessionRoundPlayer;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSession;
import gr.aua.simbug.model.DbGameSessionPlayer;
import gr.aua.simbug.model.DbGameSessionRoundPlayer;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;
import gr.aua.simbug.service.GameSessionPlayerService;

import org.springframework.beans.factory.annotation.Autowired;

public class GameSessionPlayerServiceImpl implements GameSessionPlayerService
{
	/**
	 * The GameSession DAO.
	 */
	@Autowired
	private GameSessionDAO gameSessionDAO;
	
	/**
	 * The GameSessionPlayer DAO.
	 */
	@Autowired
	private GameSessionPlayerDAO gameSessionPlayerDAO;

	/**
	 * The GameSessionRoundPlayer DAO.
	 */
	@Autowired
	private GameSessionRoundPlayerDAO gameSessionRoundPlayerDAO;
	
	/**
	 * The GameSessionRoundPlayerVariable DAO.
	 */
	@Autowired
	private GameSessionRoundPlayerVariableDAO gameSessionRoundPlayerVariableDAO;

	/**
	 * 
	 */
	@Override
	public void save(GameSessionPlayer gameSessionPlayer) 
	{
		DbGameSessionPlayer dbGameSessionPlayer = new DbGameSessionPlayer(gameSessionPlayer);
		DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gameSessionPlayer.getGameSession().getUuidOfGameSession());
		dbGameSessionPlayer.setGameSession(gs);
		gameSessionPlayerDAO.save(dbGameSessionPlayer);
	}

	/**
	 * 
	 */
	@Override
	public void saveRoundPlayer(GameSessionRoundPlayer gameSessionRoundPlayer) 
	{
		DbGameSessionRoundPlayer dbGameSessionRoundPlayer = new DbGameSessionRoundPlayer(gameSessionRoundPlayer);
		DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gameSessionRoundPlayer.getGameSession().getUuidOfGameSession());
		dbGameSessionRoundPlayer.setGameSession(gs);
		gameSessionRoundPlayerDAO.save(dbGameSessionRoundPlayer);
	}


	@Override
	public void updateRoundPlayerVariable(GameSessionRoundPlayerVariable gsrpv) 
	{
		DbGameSessionRoundPlayerVariable dbgsrpv = gameSessionRoundPlayerVariableDAO.findByUuidByRoundByPlayerByName(gsrpv);
		dbgsrpv.setVariableValue(gsrpv.getVariableValue());
		gameSessionRoundPlayerVariableDAO.save(dbgsrpv);
	}

	@Override
	public void delete(String uuid) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameSessionPlayer fetchGameSessionPlayerByPlayerUuid(String uuid) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public GameSessionDAO getGameSessionDAO() {
		return gameSessionDAO;
	}

	public void setGameSessionDAO(GameSessionDAO gameSessionDAO) {
		this.gameSessionDAO = gameSessionDAO;
	}

	public GameSessionPlayerDAO getGameSessionPlayerDAO() {
		return gameSessionPlayerDAO;
	}

	public void setGameSessionPlayerDAO(GameSessionPlayerDAO gameSessionPlayerDAO) {
		this.gameSessionPlayerDAO = gameSessionPlayerDAO;
	}

	public GameSessionRoundPlayerDAO getGameSessionRoundPlayerDAO() {
		return gameSessionRoundPlayerDAO;
	}

	public void setGameSessionRoundPlayerDAO(
			GameSessionRoundPlayerDAO gameSessionRoundPlayerDAO) {
		this.gameSessionRoundPlayerDAO = gameSessionRoundPlayerDAO;
	}

	public GameSessionRoundPlayerVariableDAO getGameSessionRoundPlayerVariableDAO() {
		return gameSessionRoundPlayerVariableDAO;
	}

	public void setGameSessionRoundPlayerVariableDAO(
			GameSessionRoundPlayerVariableDAO gameSessionRoundPlayerVariableDAO) {
		this.gameSessionRoundPlayerVariableDAO = gameSessionRoundPlayerVariableDAO;
	}

}
