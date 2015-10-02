package gr.aua.simbug.service.impl;

import gr.aua.simbug.dao.GameSessionDAO;
import gr.aua.simbug.dao.GameSessionRoundDAO;
import gr.aua.simbug.dao.GameSessionRoundPlayerVariableDAO;
import gr.aua.simbug.dao.GameSessionRoundVariableDAO;
import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionRound;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;
import gr.aua.simbug.model.DbGameSession;
import gr.aua.simbug.model.DbGameSessionRound;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundVariable;
import gr.aua.simbug.service.GameSessionRoundService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class GameSessionRoundServiceImpl implements GameSessionRoundService
{
	/**
	 * The GameSession DAO.
	 */
	@Autowired
	private GameSessionDAO gameSessionDAO;
	
	/**
	 * The GameSessionRound DAO.
	 */
	@Autowired
	private GameSessionRoundDAO gameSessionRoundDAO;

	/**
	 * The GameSessionRoundVariable DAO.
	 */
	@Autowired
	private GameSessionRoundVariableDAO gameSessionRoundVariableDAO;

	/**
	 * The GameSessionRoundPlayerVariable DAO.
	 */
	@Autowired
	private GameSessionRoundPlayerVariableDAO gameSessionRoundPlayerVariableDAO;

	/**
	 * 
	 */
	@Override
	public List<GameSessionRoundVariable> fetchWorldStateVariablesByUuidByRound(GameSession gs) 
	{
		List<DbGameSessionRoundVariable> dbgsrvList = gameSessionRoundVariableDAO.findGameSessionRoundVariablesByUuidByRound(gs.getUuidOfGameSession(), gs.getCurrentRound());
		List<GameSessionRoundVariable> gsrvList = new ArrayList<GameSessionRoundVariable>();
		for (DbGameSessionRoundVariable dbgsrv : dbgsrvList) 
		{
			GameSessionRoundVariable gsrv = new GameSessionRoundVariable(dbgsrv);
			gsrvList.add(gsrv);
		}
		return gsrvList;
	}

	/**
	 * @Override
	 */
	public List<GameSessionRoundPlayerVariable> fetchPlayerStateVariablesByUuidByRoundByPlayer(GameSession gs, String uuidOfPlayer) 
	{
		List<DbGameSessionRoundPlayerVariable> dbgsrpvList = gameSessionRoundVariableDAO
				.findGameSessionRoundPlayerStateVariablesByUuidByRoundByPlayer(gs.getUuidOfGameSession(), gs.getCurrentRound(), uuidOfPlayer);
		List<GameSessionRoundPlayerVariable> gsrpvList = new ArrayList<GameSessionRoundPlayerVariable>();
		for (DbGameSessionRoundPlayerVariable dbgsrpv : dbgsrpvList) 
		{
			GameSessionRoundPlayerVariable gsrv = new GameSessionRoundPlayerVariable(dbgsrpv);
			gsrpvList.add(gsrv);
		}
		return gsrpvList;
	}
	
	/**
	 * 
	 */
	@Override
	public void save(GameSessionRound gameSessionRound) 
	{
		DbGameSessionRound dbGameSessionRound = new DbGameSessionRound(gameSessionRound);
		DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gameSessionRound.getGameSession().getUuidOfGameSession());		
		dbGameSessionRound.setGameSession(gs);
		gameSessionRoundDAO.save(dbGameSessionRound);
	}

	/**
	 * 
	 */
	@Override
	public void saveGameSessionRoundVariable(GameSessionRoundVariable gsrv) 
	{
		DbGameSessionRoundVariable dbGameSessionRoundVariable = new DbGameSessionRoundVariable(gsrv);
		DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gsrv.getUuidOfGameSession());		
		dbGameSessionRoundVariable.setGameSession(gs);
		gameSessionRoundVariableDAO.save(dbGameSessionRoundVariable);
	}

	/**
	 * 
	 */
	@Override
	public void saveGameSessionRoundPlayerVariable(GameSessionRoundPlayerVariable gsrpv) 
	{
		DbGameSessionRoundPlayerVariable dbGameSessionRoundPlayerVariable = new DbGameSessionRoundPlayerVariable(gsrpv);
		DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gsrpv.getUuidOfGameSession());		
		dbGameSessionRoundPlayerVariable.setGameSession(gs);
		gameSessionRoundPlayerVariableDAO.save(dbGameSessionRoundPlayerVariable);
	}

	public GameSessionDAO getGameSessionDAO() {
		return gameSessionDAO;
	}

	public void setGameSessionDAO(GameSessionDAO gameSessionDAO) {
		this.gameSessionDAO = gameSessionDAO;
	}

	public GameSessionRoundDAO getGameSessionRoundDAO() {
		return gameSessionRoundDAO;
	}

	public void setGameSessionRoundDAO(GameSessionRoundDAO gameSessionRoundDAO) {
		this.gameSessionRoundDAO = gameSessionRoundDAO;
	}

	public GameSessionRoundVariableDAO getGameSessionRoundVariableDAO() {
		return gameSessionRoundVariableDAO;
	}

	public void setGameSessionRoundVariableDAO(
			GameSessionRoundVariableDAO gameSessionRoundVariableDAO) {
		this.gameSessionRoundVariableDAO = gameSessionRoundVariableDAO;
	}

	public GameSessionRoundPlayerVariableDAO getGameSessionRoundPlayerVariableDAO() {
		return gameSessionRoundPlayerVariableDAO;
	}

	public void setGameSessionRoundPlayerVariableDAO(
			GameSessionRoundPlayerVariableDAO gameSessionRoundPlayerVariableDAO) {
		this.gameSessionRoundPlayerVariableDAO = gameSessionRoundPlayerVariableDAO;
	}

	/**
	 * 
	 */
	@Override
	public List<GameSessionRoundPlayerVariable> fetchPlayerChoiceVariablesByUuidByRoundByPlayer(GameSession gs, String uuid) 
	{
		List<DbGameSessionRoundPlayerVariable> dbgsrpvList = gameSessionRoundVariableDAO
				.findGameSessionRoundPlayerChoiceVariablesByUuidByRoundByPlayer(gs.getUuidOfGameSession(), gs.getCurrentRound(), uuid);
		List<GameSessionRoundPlayerVariable> gsrpvList = new ArrayList<GameSessionRoundPlayerVariable>();
		for (DbGameSessionRoundPlayerVariable dbgsrpv : dbgsrpvList) 
		{
			GameSessionRoundPlayerVariable gsrv = new GameSessionRoundPlayerVariable(dbgsrpv);
			gsrpvList.add(gsrv);
		}
		return gsrpvList;
	}

}
