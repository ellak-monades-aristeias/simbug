package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundPlayerVariableDAO;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GameSessionRoundPlayerVariableDAOImpl extends HibernateDaoSupport implements GameSessionRoundPlayerVariableDAO
{

	@Override
	public void save(DbGameSessionRoundPlayerVariable dbGameSessionRoundPlayerVariable) 
	{
		getHibernateTemplate().save(dbGameSessionRoundPlayerVariable);
	}

}
