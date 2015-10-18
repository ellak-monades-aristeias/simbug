package gr.aua.simbug.game;

import gr.aua.simbug.definition.PlayerStateVariableDataType;
import gr.aua.simbug.definition.VariableType;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GameSessionRound extends BaseSession implements GameConstants
{
	//@Autowired
	private GameSessionRoundVariable gameSessionRoundVariable = new GameSessionRoundVariable();
	
	//@Autowired
	private GameSessionRoundPlayerVariable gameSessionRoundPlayerVariable = new GameSessionRoundPlayerVariable();
	
	//@Autowired
	private GameSessionRoundPlayer gameSessionRoundPlayer = new GameSessionRoundPlayer();

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
			gameSessionPlayerService.saveRoundPlayer(gameSessionRoundPlayer);	
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
			gameSessionRoundService.saveGameSessionRoundVariable(gameSessionRoundVariable);
			System.out.println(param.getName() + "-" + param.getType() + '-' + roundNum);
		}
	}

	/**
	 * 
	 * @param worldStateVariables
	 */
	public void saveSessionWorldStateVariables(List<GameSessionRoundVariable> worldStateVariables) 
	{
		System.out.println("\nWorldStateVariables");
		gameSession.setCurrentRound(gameSession.getCurrentRound()-1);
		for (GameSessionRoundVariable gsrv : worldStateVariables) 
		{
			GameSessionRoundVariable prevGsrv = gameSessionRoundService.fetchWorldStateVariableByNameByUuidByRound(gameSession, gsrv.getVariableName());
			prevGsrv.setUuidOfGameSession(gameSession.getUuidOfGameSession());
			prevGsrv.setRoundNum(roundNum);
			prevGsrv.setVariableValue(gsrv.getVariableValue());
			gameSessionRoundService.saveGameSessionRoundVariable(prevGsrv);
			System.out.println(gsrv.getVariableName() + "-" + gsrv.getVariableValue() + '-' + roundNum);
		}
		gameSession.setCurrentRound(gameSession.getCurrentRound()+1);
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
				getGameSessionRoundService().saveGameSessionRoundPlayerVariable(gameSessionRoundPlayerVariable);
				System.out.println(param.getName() + "-" + param.getType() + '-' + roundNum);
			}
		}
	}

	/**
	 * 
	 * @param players
	 * @param variables
	 */
	public void saveSessionPlayerChoiceVariables(List<GameSessionPlayer> players) 
	{
		// Save a value for each player
		System.out.println("\nPlayerChoiceVariables");
		gameSession.setCurrentRound(gameSession.getCurrentRound()-1);
		for (GameSessionPlayer player : players) 
		{			
			List<GameSessionRoundPlayerVariable> gsrpvList = gameSessionRoundService.fetchPlayerChoiceVariablesByUuidByRoundByPlayer(gameSession, player.getUuid()); 			
			for (GameSessionRoundPlayerVariable gsrpv : gsrpvList) 
			{
				gsrpv.setUuidOfGameSession(gameSession.getUuidOfGameSession());
				gsrpv.setRoundNum(roundNum);				
				gameSessionRoundService.saveGameSessionRoundPlayerVariable(gsrpv);
				System.out.println(gsrpv.getVariableName() + "-" + gsrpv.getVariableValue() + '-' + roundNum);
			}
		}
		gameSession.setCurrentRound(gameSession.getCurrentRound()+1);
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
				getGameSessionRoundService().saveGameSessionRoundPlayerVariable(gameSessionRoundPlayerVariable);
				System.out.println(param.getName() + "-" + param.getType() + '-' + roundNum);
			}
		}
	}

	/**
	 * 
	 * @param playerStateVariables
	 */
	public void saveSessionPlayerStateVariables(List<GameSessionRoundPlayerVariable> playerStateVariables) 
	{
		// Save a value for each player
		System.out.println("\nPlayerStateVariables");
		gameSession.setCurrentRound(gameSession.getCurrentRound()-1);
		for (GameSessionRoundPlayerVariable gsrpv : playerStateVariables) 
		{
			GameSessionRoundPlayerVariable prevGsrpv = gameSessionRoundService.fetchPlayerStateVariableByNameByUuidByRoundByPlayer(gameSession, gsrpv.getPlayerUuid(), gsrpv.getVariableName()); 			
			prevGsrpv.setUuidOfGameSession(gameSession.getUuidOfGameSession());
			prevGsrpv.setRoundNum(roundNum);				
			prevGsrpv.setVariableValue(gsrpv.getVariableValue());
			gameSessionRoundService.saveGameSessionRoundPlayerVariable(prevGsrpv);
			System.out.println(gsrpv.getVariableName() + "-" + gsrpv.getVariableValue() + '-' + roundNum);
		}
		gameSession.setCurrentRound(gameSession.getCurrentRound()-1);
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
