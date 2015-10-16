package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionDAO;
import gr.aua.simbug.model.DbGameSession;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class GameSessionDAOImpl extends DAOHibernate implements GameSessionDAO
{

	@Override
	public void save(DbGameSession dbGameSession) 
	{
		this.getSession().saveOrUpdate(dbGameSession);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DbGameSession findGameSessionByUuid(String uuid) 
	{
		String sql = "from DbGameSession gs where gameSessionUuid=:uuid";
		List<DbGameSession> list = (List<DbGameSession>) getSession().createQuery(sql)
				.setParameter("uuid", uuid)
				.list();
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

}
