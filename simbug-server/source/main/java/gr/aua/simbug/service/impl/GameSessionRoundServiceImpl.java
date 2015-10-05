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
		DbGameSessionRoundVariable dbgsrv = gameSessionRoundVariableDAO.
				findGameSessionRoundVariablesByUuidByRound(gsrv.getUuidOfGameSession(), gsrv.getRoundNum(), gsrv.getVariableName());
		if (dbgsrv == null)
			dbgsrv = new DbGameSessionRoundVariable();
		DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gsrv.getUuidOfGameSession());	
		
		dbgsrv.setGameSession(gs);
		dbgsrv.setCategory(gsrv.getVariableCategory());
		dbgsrv.setVariableName(gsrv.getVariableName());
		dbgsrv.setVariableValue(gsrv.getVariableValue());
		dbgsrv.setVariableType(gsrv.getVariableType());
		dbgsrv.setSessionUuid(gsrv.getUuidOfGameSession());
		dbgsrv.setRoundNum(gsrv.getRoundNum());
		
		gameSessionRoundVariableDAO.save(dbgsrv);		
	}

	/**
	 * 
	 */
	@Override
	public void saveGameSessionRoundPlayerVariable(GameSessionRoundPlayerVariable gsrpv) 
	{
		DbGameSessionRoundPlayerVariable dbgsrpv = gameSessionRoundVariableDAO
				.findGameSessionRoundPlayerStateVariableByUuidByRoundByPlayer(gsrpv.getUuidOfGameSession(), gsrpv.getRoundNum(), gsrpv.getPlayerUuid(), gsrpv.getVariableName());
		if (dbgsrpv == null)
			dbgsrpv = new DbGameSessionRoundPlayerVariable();
		DbGameSession gs = gameSessionDAO.findGameSessionByUuid(gsrpv.getUuidOfGameSession());	
		
		dbgsrpv.setGameSession(gs);
		dbgsrpv.setCategory(gsrpv.getVariableCategory());
		dbgsrpv.setVariableName(gsrpv.getVariableName());
		dbgsrpv.setVariableValue(gsrpv.getVariableValue());
		dbgsrpv.setVariableType(gsrpv.getVariableType());
		dbgsrpv.setSessionUuid(gsrpv.getUuidOfGameSession());
		dbgsrpv.setRoundNum(gsrpv.getRoundNum());
		dbgsrpv.setPlayerUuid(gsrpv.getPlayerUuid());
		
		gameSessionRoundPlayerVariableDAO.save(dbgsrpv);
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

	@Override
	public GameSessionRoundPlayerVariable fetchPlayerChoiceVariableByNameByUuidByRoundByPlayer(GameSession gs, String uuid, String name)
	{
		DbGameSessionRoundPlayerVariable dbgsrpv = gameSessionRoundVariableDAO
				.findGameSessionRoundPlayerChoiceVariableByUuidByRoundByPlayer(gs.getUuidOfGameSession(), gs.getCurrentRound(), uuid, name);
		if (dbgsrpv != null)
		{
			GameSessionRoundPlayerVariable gsrv = new GameSessionRoundPlayerVariable(dbgsrpv);
			return gsrv;
		}
		return null;
	}

	@Override
	public GameSessionRoundPlayerVariable fetchPlayerStateVariableByNameByUuidByRoundByPlayer(
			GameSession gs, String uuid, String name)
	{
		DbGameSessionRoundPlayerVariable dbgsrpv = gameSessionRoundVariableDAO
				.findGameSessionRoundPlayerStateVariableByUuidByRoundByPlayer(gs.getUuidOfGameSession(), gs.getCurrentRound(), uuid, name);
		if (dbgsrpv != null)
		{
			GameSessionRoundPlayerVariable gsrv = new GameSessionRoundPlayerVariable(dbgsrpv);
			return gsrv;
		}
		return null;
	}

	@Override
	public GameSessionRoundVariable fetchWorldStateVariableByNameByUuidByRound(GameSession gs, String name)
	{
		DbGameSessionRoundVariable dbgsrv = gameSessionRoundVariableDAO.findGameSessionRoundVariablesByUuidByRound(gs.getUuidOfGameSession(), gs.getCurrentRound(), name);
		if (dbgsrv != null)
		{
			GameSessionRoundVariable gsrv = new GameSessionRoundVariable(dbgsrv);
			return gsrv;
		}
		return null;
	}

}
