package gr.aua.simbug.service.impl;

import gr.aua.simbug.dao.GameSessionDAO;
import gr.aua.simbug.dao.GameSessionPlayerDAO;
import gr.aua.simbug.dao.GameSessionVariableDAO;
import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionPlayer;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;
import gr.aua.simbug.game.GameSessionVariable;
import gr.aua.simbug.model.DbGameSession;
import gr.aua.simbug.model.DbGameSessionPlayer;
import gr.aua.simbug.model.DbGameSessionVariable;
import gr.aua.simbug.service.GameSessionService;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * The GameSessionPlayer DAO.
	 */
	@Autowired
	private GameSessionPlayerDAO gameSessionPlayerDAO;
	
	@Override
	public void saveGameSession(GameSession gs) 
	{
		DbGameSession dbgs = gameSessionDAO.findGameSessionByUuid(gs.getUuidOfGameSession());		
		if(dbgs == null)
			dbgs = new DbGameSession();
		dbgs.setCurrentRound(gs.getCurrentRound());
		dbgs.setDefinitionData(gs.getDefinitionData());
		dbgs.setDefinitionFile(null);
		dbgs.setGameSessionUuid(gs.getUuidOfGameSession());
		//this.game;
		dbgs.setPlayers(gs.getJsonListOfPlayers());

		gameSessionDAO.save(dbgs);
	}


	@Override
	public void deleteGameSession(String uuidOfGameSession) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public GameSession fetchGameSessionByUuid(String uuidOfGameSession) 
	{
		DbGameSession dbgs = gameSessionDAO.findGameSessionByUuid(uuidOfGameSession);
		if (dbgs != null)
		{
			GameSession gs = new GameSession(dbgs);
			return gs;
		}
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

	@Override
	public List<GameSessionPlayer> fetchListOfGameSessionPlayersBySessionUuid(String uuid) 
	{
		List<DbGameSessionPlayer> dbgspList = gameSessionPlayerDAO.findGameSessionPlayersBySessionUuid(uuid);
		List<GameSessionPlayer> gspList = new ArrayList<GameSessionPlayer>();
		for (DbGameSessionPlayer dbgsrv : dbgspList) 
		{
			GameSessionPlayer gsp = new GameSessionPlayer(dbgsrv);
			gspList.add(gsp);
		}
		return gspList;
	}


	@Override
	public List<GameSessionVariable> fetchConfigurationBySessionUuid(String sessionUuid) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<GameSessionRoundPlayerVariable> fetchChoiceVariablesBySessionUuid(String sessionUuid) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<GameSessionRoundPlayerVariable> fetchStateVariablesBySessionUuid(String sessionUuid) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<GameSessionRoundVariable> fetchWorldStateVariablesByUuid(GameSession gameSession) 
	{
		// TODO Auto-generated method stub
		return null;
	}


}
