package gr.aua.simbug.game;

import gr.aua.simbug.definition.VariableType;
import gr.aua.simbug.model.DbGameSessionRoundVariable;
import gr.aua.simbug.service.GameSessionRoundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSessionRoundVariable extends GameSessionVariable 
{
	/**
	 * The GameSessionRound service.
	 */
	@Autowired
	private GameSessionRoundService gameSessionRoundService;
	
	private long roundNum;
	
	public GameSessionRoundVariable()
	{
		
	}
	
	/**
	 * 
	 * @param category
	 * @param param
	 * @param uuidOfGameSession
	 * @param roundNum
	 */
	public GameSessionRoundVariable(int category, VariableType param, String uuidOfGameSession, long roundNum) 
	{
		super();
		setVariableName(param.getName());
		setVariableValue(null);
		setVariableType(param.getType().value());
		setUuidOfGameSession(uuidOfGameSession);
		setVariableCategory(category);
		this.roundNum = roundNum;
	}

	/**
	 * 
	 * @param dbgsrv
	 */
	public GameSessionRoundVariable(DbGameSessionRoundVariable dbgsrv) 
	{
		setVariableName(dbgsrv.getVariableName());
		setVariableValue(dbgsrv.getVariableValue());
		setVariableType(dbgsrv.getVariableType());
		setUuidOfGameSession(dbgsrv.getSessionUuid());
		setVariableCategory(dbgsrv.getCategory());
		this.roundNum = dbgsrv.getRoundNum();
	}

	public GameSessionRoundVariable(int worldStateVariable, Object name, Object value, String uuidOfGameSession, long roundNum)
	{
		setVariableName((String)name);
		setVariableValue((String)value);
		setUuidOfGameSession(uuidOfGameSession);
		setVariableCategory(worldStateVariable);
		this.roundNum = roundNum;
	}

	/**
	 * 
	 * @param category
	 * @param param
	 * @param uuidOfGameSession
	 * @param roundNum
	 */
	public void createGameSessionRoundVariable(int category, VariableType param, String uuidOfGameSession, long roundNum) 
	{
		setVariableName(param.getName());
		setVariableValue(null);
		setVariableType(param.getType().value());
		setUuidOfGameSession(uuidOfGameSession);
		setVariableCategory(category);
		this.roundNum = roundNum;
	}
	
	/**
	 * 
	 */
	public void saveSessionRoundVariable() 
	{
		gameSessionRoundService.saveGameSessionRoundVariable(this);
	}

	public long getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(long roundNum) {
		this.roundNum = roundNum;
	}

	public GameSessionRoundService getGameSessionRoundService() {
		return gameSessionRoundService;
	}

	public void setGameSessionRoundService(
			GameSessionRoundService gameSessionRoundService) {
		this.gameSessionRoundService = gameSessionRoundService;
	}

}
