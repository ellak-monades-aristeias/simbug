package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundDAO;
import gr.aua.simbug.model.DbGameSessionRound;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GameSessionRoundDAOImpl extends HibernateDaoSupport implements GameSessionRoundDAO
{

	@Override
	public void save(DbGameSessionRound dbGameSessionRound) 
	{
		getHibernateTemplate().saveOrUpdate(dbGameSessionRound);	
	}

}
