package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionPlayerDAO;
import gr.aua.simbug.model.DbGameSessionPlayer;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class GameSessionPlayerDAOImpl extends DAOHibernate implements GameSessionPlayerDAO
{

	@Override
	public void save(DbGameSessionPlayer gameSessionPlayer) 
	{
		this.getSession().saveOrUpdate(gameSessionPlayer);	
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DbGameSessionPlayer> findGameSessionPlayersBySessionUuid(String uuid) 
	{
		String sql = "from DbGameSessionPlayer gs where gameSession.gameSessionUuid=:uuid";
		List<DbGameSessionPlayer> list = (List<DbGameSessionPlayer>) getSession().createQuery(sql)
				.setParameter("uuid", uuid)
				.list();
		if (CollectionUtils.isEmpty(list))
			return null;
		return list;
	}

}
