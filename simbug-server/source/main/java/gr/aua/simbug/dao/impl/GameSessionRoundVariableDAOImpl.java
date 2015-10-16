package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundVariableDAO;
import gr.aua.simbug.game.GameConstants;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundVariable;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class GameSessionRoundVariableDAOImpl extends DAOHibernate implements GameSessionRoundVariableDAO, GameConstants
{

	/**
	 * 
	 */
	@Override
	public void save(DbGameSessionRoundVariable dbGameSessionRoundVariable) 
	{
		this.getSession().saveOrUpdate(dbGameSessionRoundVariable);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbGameSessionRoundVariable> findGameSessionRoundVariablesByUuidByRound(String uuidOfGameSession, long currentRound) 
	{
		String sql = "from DbGameSessionRoundVariable gs where gameSession.gameSessionUuid=:gameSessionUuid AND roundNum=:roundNum ";
		List<DbGameSessionRoundVariable> list = (List<DbGameSessionRoundVariable>) getSession().createQuery(sql)
				.setParameter("gameSessionUuid", uuidOfGameSession)
				.setParameter("roundNum", currentRound)
				.list();
		return list;	
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbGameSessionRoundPlayerVariable> findGameSessionRoundPlayerStateVariablesByUuidByRoundByPlayer(
			String uuidOfGameSession, long currentRound, String uuidOfPlayer) 
	{
		String sql = "from DbGameSessionRoundPlayerVariable gs where gameSession.gameSessionUuid=:gameSessionUuid AND roundNum=:roundNum ";
		sql += " AND playerUuid=:playerUuid AND category=:category";
		List<DbGameSessionRoundPlayerVariable> list = (List<DbGameSessionRoundPlayerVariable>) getSession().createQuery(sql)
				.setParameter("gameSessionUuid", uuidOfGameSession)
				.setParameter("roundNum", currentRound)
				.setParameter("playerUuid", uuidOfPlayer)
				.setParameter("category", PLAYER_STATE_VARIABLE)
				.list();
		if (CollectionUtils.isEmpty(list))
			return null;
		return list;	
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbGameSessionRoundPlayerVariable> findGameSessionRoundPlayerChoiceVariablesByUuidByRoundByPlayer(
			String uuidOfGameSession, long currentRound, String playerUuid) 
	{
		String sql = "from DbGameSessionRoundPlayerVariable gs where gameSession.gameSessionUuid=:gameSessionUuid AND roundNum=:roundNum ";
		sql += " AND playerUuid=:playerUuid AND category=:category";
		List<DbGameSessionRoundPlayerVariable> list = (List<DbGameSessionRoundPlayerVariable>) getSession().createQuery(sql)
				.setParameter("gameSessionUuid", uuidOfGameSession)
				.setParameter("roundNum", currentRound)
				.setParameter("playerUuid", playerUuid)
				.setParameter("category", PLAYER_CHOICE_VARIABLE)
				.list();
		return list;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public DbGameSessionRoundPlayerVariable findGameSessionRoundPlayerChoiceVariableByUuidByRoundByPlayer(
			String uuidOfGameSession, long currentRound, String playerUuid, String name)
	{
		String sql = "from DbGameSessionRoundPlayerVariable gs where gameSession.gameSessionUuid=:gameSessionUuid AND roundNum=:roundNum ";
		sql += " AND playerUuid=:playerUuid AND category=:category";
		List<DbGameSessionRoundPlayerVariable> list = (List<DbGameSessionRoundPlayerVariable>) getSession().createQuery(sql)
				.setParameter("gameSessionUuid", uuidOfGameSession)
				.setParameter("roundNum", currentRound)
				.setParameter("playerUuid", playerUuid)
				.setParameter("category", PLAYER_CHOICE_VARIABLE)
				.list();
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DbGameSessionRoundPlayerVariable findGameSessionRoundPlayerStateVariableByUuidByRoundByPlayer(
			String uuidOfGameSession, long currentRound, String playerUuid, String name)
	{
		String sql = "from DbGameSessionRoundPlayerVariable gs where gameSession.gameSessionUuid=:gameSessionUuid AND roundNum=:roundNum ";
		sql += " AND playerUuid=:playerUuid AND category=:category AND variableName=:variableName";
		List<DbGameSessionRoundPlayerVariable> list = (List<DbGameSessionRoundPlayerVariable>) getSession().createQuery(sql)
				.setParameter("gameSessionUuid", uuidOfGameSession)
				.setParameter("roundNum", currentRound)
				.setParameter("playerUuid", playerUuid)
				.setParameter("category", PLAYER_STATE_VARIABLE)
				.setParameter("variableName", name)
				.list();
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DbGameSessionRoundVariable findGameSessionRoundVariablesByUuidByRound( String uuidOfGameSession, long currentRound, String name)
	{
		String sql = "from DbGameSessionRoundVariable gs where gameSession.gameSessionUuid=:gameSessionUuid AND roundNum=:roundNum ";
		sql += " AND variableName=:variableName";
		List<DbGameSessionRoundVariable> list = (List<DbGameSessionRoundVariable>) getSession().createQuery(sql)
				.setParameter("gameSessionUuid", uuidOfGameSession)
				.setParameter("roundNum", currentRound)
				.setParameter("variableName", name)
				.list();
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

}
