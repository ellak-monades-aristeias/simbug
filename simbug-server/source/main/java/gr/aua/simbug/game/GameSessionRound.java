package gr.aua.simbug.game;

import gr.aua.simbug.definition.PlayerStateVariableDataType;
import gr.aua.simbug.definition.VariableType;

import java.util.ArrayList;
import java.util.List;


public class GameSessionRound implements GameConstants
{
	private GameSession gameSession;
	private int roundNum;
	private List<GameSessionRoundVariable> worldStateVariables = new ArrayList<GameSessionRoundVariable>();
	private List<GameSessionRoundPlayer> sessionRoundPlayer = new ArrayList<GameSessionRoundPlayer>();
	
	public GameSessionRound()
	{
		super();
	}

	public GameSessionRound(GameSession gameSession, int round) 
	{
		this.gameSession = gameSession;
		this.roundNum = round;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param worldStateVariables
	 */
	public void saveWorldStateVariables(List<VariableType> worldStateVariables) 
	{
		// TODO Auto-generated method stub
		System.out.println("\nWorldStateVariables");
		for (VariableType param : worldStateVariables) 
		{
			GameSessionRoundVariable var = new GameSessionRoundVariable(WORLD_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum);
			var.save();
			System.out.println(param.getName() + "-" + param.getType());
		}
	}

	/**
	 * 
	 * @param players
	 * @param variables
	 */
	public void savePlayerChoiceVariables(List<GameSessionPlayer> players, List<VariableType> variables) 
	{
		// TODO Auto-generated method stub
		// Save a value for each player
		for (GameSessionPlayer player : players) 
		{			
			for (VariableType param : variables) 
			{
				GameSessionRoundPlayerVariable var = new GameSessionRoundPlayerVariable(PLAYER_CHOICE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum, player.getUuid());
				var.save();
				System.out.println(param.getName() + "-" + param.getType());
			}
		}
	}

	/**
	 * 
	 * @param players
	 * @param variables
	 */
	public void savePlayerStateVariables(List<GameSessionPlayer> players, List<PlayerStateVariableDataType> variables) 
	{
		// TODO Auto-generated method stub
		// Save a value for each player
		System.out.println("\nPlayerStateVariables");
		for (GameSessionPlayer player : players) 
		{			
			for (PlayerStateVariableDataType param : variables) 
			{
				GameSessionRoundPlayerVariable var = new GameSessionRoundPlayerVariable(PLAYER_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum, player.getUuid());
				var.save();
				System.out.println(param.getName() + "-" + param.getType());
			}
		}
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public int getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}

	public List<GameSessionRoundVariable> getWorldStateVariables() {
		return worldStateVariables;
	}

	public void setWorldStateVariables(
			List<GameSessionRoundVariable> worldStateVariables) {
		this.worldStateVariables = worldStateVariables;
	}

	public List<GameSessionRoundPlayer> getSessionRoundPlayer() {
		return sessionRoundPlayer;
	}

	public void setSessionRoundPlayer(
			List<GameSessionRoundPlayer> sessionRoundPlayer) {
		this.sessionRoundPlayer = sessionRoundPlayer;
	}

}
