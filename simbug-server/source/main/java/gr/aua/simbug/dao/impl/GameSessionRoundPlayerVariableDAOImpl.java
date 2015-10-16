package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundPlayerVariableDAO;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class GameSessionRoundPlayerVariableDAOImpl extends DAOHibernate implements GameSessionRoundPlayerVariableDAO
{

	/**
	 * 
	 * @param dbgsrpv
	 */
	@Override
	public void save(DbGameSessionRoundPlayerVariable dbgsrpv) 
	{
		this.getSession().saveOrUpdate(dbgsrpv);
	}

	/**
	 * 
	 * @param gsrpv
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DbGameSessionRoundPlayerVariable findByUuidByRoundByPlayerByName(GameSessionRoundPlayerVariable gsrpv) 
	{
		String sql = "from DbGameSessionRoundPlayerVariable gs where gameSession.gameSessionUuid=:gameSessionUuid ";
		sql += "AND roundNum=:roundNum AND playerUuid=:playerUuid AND variableName=:variableName";
		List<DbGameSessionRoundPlayerVariable> list = (List<DbGameSessionRoundPlayerVariable>) getSession().createQuery(sql)
				.setParameter("gameSessionUuid", gsrpv.getUuidOfGameSession())
				.setParameter("roundNum", gsrpv.getRoundNum())
				.setParameter("playerUuid", gsrpv.getPlayerUuid())
				.setParameter("variableName", gsrpv.getVariableName())
				.list();
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}
}
