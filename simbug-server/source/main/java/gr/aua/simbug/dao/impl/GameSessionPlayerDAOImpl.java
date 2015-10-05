package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionPlayerDAO;
import gr.aua.simbug.model.DbGameSessionPlayer;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class GameSessionPlayerDAOImpl extends HibernateDaoSupport implements GameSessionPlayerDAO
{

	@Override
	public void save(DbGameSessionPlayer gameSessionPlayer) 
	{
		getHibernateTemplate().saveOrUpdate(gameSessionPlayer);	
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbGameSessionPlayer> findGameSessionPlayersBySessionUuid(String uuid) 
	{
		String sql = "from DbGameSessionPlayer gs where gameSession.gameSessionUuid=?";
		final List<DbGameSessionPlayer> temp = (List<DbGameSessionPlayer>)getHibernateTemplate()
				.find(sql, new Object[] { uuid });
		return temp;	
	}

}
