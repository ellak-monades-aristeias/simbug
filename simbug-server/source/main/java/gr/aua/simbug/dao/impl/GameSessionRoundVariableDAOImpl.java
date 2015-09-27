package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundVariableDAO;
import gr.aua.simbug.model.DbGameSessionRoundVariable;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GameSessionRoundVariableDAOImpl extends HibernateDaoSupport implements GameSessionRoundVariableDAO
{

	@Override
	public void save(DbGameSessionRoundVariable dbGameSessionRoundVariable) 
	{
		getHibernateTemplate().save(dbGameSessionRoundVariable);
	}

}
