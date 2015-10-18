package gr.aua.simbug.game;

import gr.aua.simbug.definition.VariableType;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;

import org.springframework.stereotype.Component;

@Component
public class GameSessionRoundPlayerVariable extends GameSessionRoundVariable
{

	private String playerUuid;
	
	public GameSessionRoundPlayerVariable() 
	{
		super();
		System.out.println("creating bean GameSessionRoundPlayerVariable: " + this);
	}

	/**
	 * 
	 * @param dbgsrpv
	 */
	public GameSessionRoundPlayerVariable(DbGameSessionRoundPlayerVariable dbgsrpv) 
	{
		setVariableName(dbgsrpv.getVariableName());
		setVariableValue(dbgsrpv.getVariableValue());
		setVariableType(dbgsrpv.getVariableType());
		setUuidOfGameSession(dbgsrpv.getSessionUuid());
		setVariableCategory(dbgsrpv.getCategory());
		setRoundNum(dbgsrpv.getRoundNum());
		this.playerUuid = dbgsrpv.getPlayerUuid();
	}

	/**
	 * 
	 * @param category
	 * @param param
	 * @param uuidOfGameSession
	 * @param roundNum
	 * @param uuid
	 */
	public GameSessionRoundPlayerVariable(int category, VariableType param, String uuidOfGameSession, long roundNum, String uuid) 
	{
		super(category, param, uuidOfGameSession, roundNum);
		this.setPlayerUuid(uuid);
	}

	/**
	 * 
	 * @param category
	 * @param name
	 * @param value
	 * @param uuidOfGameSession
	 * @param roundNum
	 * @param playerUuid
	 */
	public GameSessionRoundPlayerVariable(int category, Object name, Object value, String uuidOfGameSession, long roundNum, Object playerUuid)
	{
		setVariableName(String.valueOf(name));
		setVariableValue(String.valueOf(value));
		setUuidOfGameSession(uuidOfGameSession);
		setVariableCategory(category);
		setRoundNum(roundNum);
		this.playerUuid = String.valueOf(playerUuid);
	}

	/**
	 * 
	 * @param category
	 * @param param
	 * @param uuidOfGameSession
	 * @param roundNum
	 * @param uuid
	 */
	public void createSessionRoundPlayerVariable(int category, VariableType param, String uuidOfGameSession, long roundNum, String uuid) 
	{
		super.createGameSessionRoundVariable(category, param, uuidOfGameSession, roundNum);
		this.setPlayerUuid(uuid);
		System.out.println(uuid);
	}

	public String getPlayerUuid() {
		return playerUuid;
	}

	public void setPlayerUuid(String playerUuid) {
		this.playerUuid = playerUuid;
	}


}
