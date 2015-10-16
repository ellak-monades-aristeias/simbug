package gr.aua.simbug.game;

import gr.aua.simbug.definition.PlayerStateVariableDataType;
import gr.aua.simbug.definition.VariableType;
import gr.aua.simbug.service.GameSessionRoundService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameSessionRound implements GameConstants
{
	/**
	 * The GameSessionRound service.
	 */
	@Autowired
	private GameSessionRoundService gameSessionRoundService;
	
	@Autowired
	private GameSessionRoundVariable gameSessionRoundVariable;
	
	@Autowired
	GameSessionRoundPlayerVariable gameSessionRoundPlayerVariable;
	
	@Autowired
	GameSessionRoundPlayer gameSessionRoundPlayer;

	private GameSession gameSession;
	private long roundNum;
	private List<GameSessionRoundVariable> worldStateVariables = new ArrayList<GameSessionRoundVariable>();
	private List<GameSessionRoundPlayer> sessionRoundPlayer = new ArrayList<GameSessionRoundPlayer>();
	
	/**
	 * 
	 */
	public GameSessionRound()
	{
		super();
		System.out.println("creating bean GameSessionRound: " + this);
	}

	/**
	 * Save a new round and the players
	 * @param gameSession
	 * @param round
	 */
	public void createRound(GameSession gameSession, long round) 
	{
		this.gameSession = gameSession;
		this.roundNum = round;
		gameSessionRoundService.save(this);		
		// Save round players
		for (GameSessionPlayer player : gameSession.getGameSessionPlayers()) 
		{
			gameSessionRoundPlayer.createSessionRoundPlayer(player, this);
			gameSessionRoundPlayer.saveSessionRoundPlayer();
		}		
	}
	
	/**
	 * 
	 * @param worldStateVariables
	 */
	public void saveWorldStateVariables(List<VariableType> worldStateVariables) 
	{
		System.out.println("\nWorldStateVariables");
		for (VariableType param : worldStateVariables) 
		{
			gameSessionRoundVariable.createGameSessionRoundVariable(WORLD_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum);
			gameSessionRoundVariable.saveSessionRoundVariable();
			System.out.println(param.getName() + "-" + param.getType() + '-' + roundNum);
		}
	}

	/**
	 * 
	 * @param players
	 * @param variables
	 */
	public void savePlayerChoiceVariables(List<GameSessionPlayer> players, List<VariableType> variables) 
	{
		// Save a value for each player
		System.out.println("\nPlayerChoiceVariables");
		for (GameSessionPlayer player : players) 
		{			
			for (VariableType param : variables) 
			{
				gameSessionRoundPlayerVariable.createSessionRoundPlayerVariable(PLAYER_CHOICE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum, player.getUuid());
				gameSessionRoundPlayerVariable.saveSessionRoundPlayerVariable();
				System.out.println(param.getName() + "-" + param.getType() + '-' + roundNum);
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
		// Save a value for each player
		System.out.println("\nPlayerStateVariables");
		for (GameSessionPlayer player : players) 
		{			
			for (PlayerStateVariableDataType param : variables) 
			{
				gameSessionRoundPlayerVariable.createSessionRoundPlayerVariable(PLAYER_STATE_VARIABLE, param, gameSession.getUuidOfGameSession(), roundNum, player.getUuid());
				gameSessionRoundPlayerVariable.saveSessionRoundPlayerVariable();
				System.out.println(param.getName() + "-" + param.getType() + '-' + roundNum);
			}
		}
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public long getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(long roundNum) {
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

	public GameSessionRoundService getGameSessionRoundService() {
		return gameSessionRoundService;
	}

	public void setGameSessionRoundService(
			GameSessionRoundService gameSessionRoundService) {
		this.gameSessionRoundService = gameSessionRoundService;
	}

	public GameSessionRoundVariable getGameSessionRoundVariable() {
		return gameSessionRoundVariable;
	}

	public void setGameSessionRoundVariable(
			GameSessionRoundVariable gameSessionRoundVariable) {
		this.gameSessionRoundVariable = gameSessionRoundVariable;
	}

	public GameSessionRoundPlayerVariable getGameSessionRoundPlayerVariable() {
		return gameSessionRoundPlayerVariable;
	}

	public void setGameSessionRoundPlayerVariable(
			GameSessionRoundPlayerVariable gameSessionRoundPlayerVariable) {
		this.gameSessionRoundPlayerVariable = gameSessionRoundPlayerVariable;
	}

	public GameSessionRoundPlayer getGameSessionRoundPlayer() {
		return gameSessionRoundPlayer;
	}

	public void setGameSessionRoundPlayer(
			GameSessionRoundPlayer gameSessionRoundPlayer) {
		this.gameSessionRoundPlayer = gameSessionRoundPlayer;
	}

}
