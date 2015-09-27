package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundDAO;
import gr.aua.simbug.model.DbGameSessionRound;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GameSessionRoundDAOImpl extends HibernateDaoSupport implements GameSessionRoundDAO
{

	@Override
	public void save(DbGameSessionRound dbGameSessionRound) 
	{
		getHibernateTemplate().save(dbGameSessionRound);	
	}

}
