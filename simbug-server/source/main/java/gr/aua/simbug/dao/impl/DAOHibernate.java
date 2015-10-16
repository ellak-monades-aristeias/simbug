package gr.aua.simbug.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOHibernate //extends HibernateDaoSupport 
{
    @Resource
    protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
}
