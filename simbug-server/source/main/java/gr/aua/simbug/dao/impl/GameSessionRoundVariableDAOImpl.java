package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundVariableDAO;
import gr.aua.simbug.game.GameConstants;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundVariable;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GameSessionRoundVariableDAOImpl extends HibernateDaoSupport implements GameSessionRoundVariableDAO, GameConstants
{

	/**
	 * 
	 */
	@Override
	public void save(DbGameSessionRoundVariable dbGameSessionRoundVariable) 
	{
		getHibernateTemplate().save(dbGameSessionRoundVariable);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbGameSessionRoundVariable> findGameSessionRoundVariablesByUuidByRound(String uuidOfGameSession, long currentRound) 
	{
		String sql = "from DbGameSessionRoundVariable gs where gameSession.gameSessionUuid=? AND roundNum=? ";
		final List<DbGameSessionRoundVariable> temp = (List<DbGameSessionRoundVariable>)getHibernateTemplate()
				.find(sql, new Object[] { uuidOfGameSession, currentRound });
		return temp;	
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbGameSessionRoundPlayerVariable> findGameSessionRoundPlayerVariablesByUuidByRoundByPlayer(
			String uuidOfGameSession, long currentRound, String uuidOfPlayer) 
	{
		String sql = "from DbGameSessionRoundPlayerVariable gs where gameSession.gameSessionUuid=? AND roundNum=? ";
		sql += " AND playerUuid=? AND category=?";
		final List<DbGameSessionRoundPlayerVariable> temp = (List<DbGameSessionRoundPlayerVariable>)getHibernateTemplate()
				.find(sql, new Object[] { uuidOfGameSession, currentRound, uuidOfPlayer, PLAYER_STATE_VARIABLE });
		return temp;	
	}

}
