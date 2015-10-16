package gr.aua.simbug.dao.impl;

import gr.aua.simbug.dao.GameDAO;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GameDAOImpl extends HibernateDaoSupport implements GameDAO
{

}
