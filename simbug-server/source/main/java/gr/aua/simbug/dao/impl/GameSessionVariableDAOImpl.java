package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionVariableDAO;
import gr.aua.simbug.model.DbGameSessionVariable;

import org.springframework.stereotype.Repository;

@Repository
public class GameSessionVariableDAOImpl extends DAOHibernate implements GameSessionVariableDAO
{

	@Override
	public void save(DbGameSessionVariable dbGameSessionVariable) 
	{
		this.getSession().saveOrUpdate(dbGameSessionVariable);
	}

}
