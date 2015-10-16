package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundPlayerDAO;
import gr.aua.simbug.model.DbGameSessionRoundPlayer;

import org.springframework.stereotype.Repository;

@Repository
public class GameSessionRoundPlayerDAOImpl extends DAOHibernate implements GameSessionRoundPlayerDAO
{

	@Override
	public void save(DbGameSessionRoundPlayer dbGameSessionRoundPlayer) 
	{
		this.getSession().saveOrUpdate(dbGameSessionRoundPlayer);	
	}

}
