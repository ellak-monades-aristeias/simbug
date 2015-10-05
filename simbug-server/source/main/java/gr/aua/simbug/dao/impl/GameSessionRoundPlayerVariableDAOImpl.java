package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundPlayerVariableDAO;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.CollectionUtils;

public class GameSessionRoundPlayerVariableDAOImpl extends HibernateDaoSupport implements GameSessionRoundPlayerVariableDAO
{

	/**
	 * 
	 * @param dbgsrpv
	 */
	@Override
	public void save(DbGameSessionRoundPlayerVariable dbgsrpv) 
	{
		getHibernateTemplate().saveOrUpdate(dbgsrpv);
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
		String sql = "from DbGameSessionRoundPlayerVariable gs where gameSession.gameSessionUuid=? ";
		sql += "AND roundNum=? AND playerUuid=? AND variableName=?";
		final List<DbGameSessionRoundPlayerVariable> temp = (List<DbGameSessionRoundPlayerVariable>)getHibernateTemplate()
				.find(sql, new Object[] { gsrpv.getUuidOfGameSession(), gsrpv.getRoundNum(), gsrpv.getPlayerUuid(), gsrpv.getVariableName() });
		if (CollectionUtils.isEmpty(temp))
			return null;
		return temp.get(0);	}

}
