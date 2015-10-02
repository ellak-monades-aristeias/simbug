package gr.aua.simbug.beans;

import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionPlayer;

import java.util.ArrayList;
import java.util.List;


public class Info
{
	private Integer numOfPlayers; 
	private Long currentTurn;
	private List<String> uuidsOfPlayers = new ArrayList<String>();
	
	public Info() 
	{
	}	
	
	public Info(GameSession gameSession, List<GameSessionPlayer> players) 
	{
		this.numOfPlayers = players.size();
		this.currentTurn = gameSession.getCurrentRound();
		for (GameSessionPlayer player : players) 
		{
			uuidsOfPlayers.add(player.getUuid());
		}
	}
	
	public Integer getNumOfPlayers() {
		return numOfPlayers;
	}
	public void setNumOfPlayers(Integer numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	public Long getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(Long currentTurn) {
		this.currentTurn = currentTurn;
	}
	public List<String> getUuidsOfPlayers() {
		return uuidsOfPlayers;
	}
	public void setUuidsOfPlayers(List<String> uuidsOfPlayers) {
		this.uuidsOfPlayers = uuidsOfPlayers;
	}
	
}
