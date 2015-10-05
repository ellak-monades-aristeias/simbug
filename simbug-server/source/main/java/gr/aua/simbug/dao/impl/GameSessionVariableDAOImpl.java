package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionVariableDAO;
import gr.aua.simbug.model.DbGameSessionVariable;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GameSessionVariableDAOImpl extends HibernateDaoSupport implements GameSessionVariableDAO
{

	@Override
	public void save(DbGameSessionVariable dbGameSessionVariable) 
	{
		getHibernateTemplate().saveOrUpdate(dbGameSessionVariable);
	}

}
