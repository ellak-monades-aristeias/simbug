package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundPlayerDAO;
import gr.aua.simbug.model.DbGameSessionRoundPlayer;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GameSessionRoundPlayerDAOImpl extends HibernateDaoSupport implements GameSessionRoundPlayerDAO
{

	@Override
	public void save(DbGameSessionRoundPlayer dbGameSessionRoundPlayer) 
	{
		getHibernateTemplate().saveOrUpdate(dbGameSessionRoundPlayer);	
	}

}
