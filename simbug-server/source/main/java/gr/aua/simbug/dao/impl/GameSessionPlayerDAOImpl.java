package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionPlayerDAO;
import gr.aua.simbug.model.DbGameSessionPlayer;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GameSessionPlayerDAOImpl extends HibernateDaoSupport implements GameSessionPlayerDAO
{

	@Override
	public void save(DbGameSessionPlayer gameSessionPlayer) 
	{
		getHibernateTemplate().save(gameSessionPlayer);	
	}

}
