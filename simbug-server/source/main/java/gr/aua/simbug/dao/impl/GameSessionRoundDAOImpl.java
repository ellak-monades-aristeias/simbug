package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionRoundDAO;
import gr.aua.simbug.model.DbGameSessionRound;

import org.springframework.stereotype.Repository;

@Repository
public class GameSessionRoundDAOImpl extends DAOHibernate implements GameSessionRoundDAO
{

	@Override
	public void save(DbGameSessionRound dbGameSessionRound) 
	{
		this.getSession().saveOrUpdate(dbGameSessionRound);	
	}

}
