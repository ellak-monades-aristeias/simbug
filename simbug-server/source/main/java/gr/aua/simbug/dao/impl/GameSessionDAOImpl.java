package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameSessionDAO;
import gr.aua.simbug.model.DbGameSession;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class GameSessionDAOImpl extends HibernateDaoSupport implements GameSessionDAO
{

	@Override
	public void save(DbGameSession dbGameSession) 
	{
		getHibernateTemplate().saveOrUpdate(dbGameSession);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DbGameSession findGameSessionByUuid(String uuid) 
	{
		String sql = "from DbGameSession gs where gameSessionUuid=?";
		final List<DbGameSession> temp = (List<DbGameSession>)getHibernateTemplate().find(sql, new Object[] { uuid });
		if (CollectionUtils.isEmpty(temp))
			return null;
		return temp.get(0);
	}

}
