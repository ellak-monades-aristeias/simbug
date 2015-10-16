package gr.aua.simbug.service.impl;

import gr.aua.simbug.dao.GameDAO;
import gr.aua.simbug.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;

public class GameServiceImpl implements GameService
{
	@Autowired
	private GameDAO gameDAO;

	public GameServiceImpl()
	{
		super();
		System.out.println("creating bean GameServiceImpl: " + this);
	}
	
	/**
	 * @return the gameDAO
	 */
	public GameDAO getGameDAO()
	{
		return gameDAO;
	}

	/**
	 * @param gameDAO the gameDAO to set
	 */
	public void setGameDAO(GameDAO gameDAO)
	{
		this.gameDAO = gameDAO;
	}

}
