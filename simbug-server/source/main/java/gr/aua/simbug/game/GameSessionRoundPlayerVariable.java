package gr.aua.simbug.game;

import gr.aua.simbug.definition.VariableType;

import org.springframework.stereotype.Service;

@Service
public class GameSessionRoundPlayerVariable extends GameSessionRoundVariable
{

	private String playerUuid;
	
	public GameSessionRoundPlayerVariable() 
	{
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

	/**
	 * 
	 */
	public void saveSessionRoundPlayerVariable() 
	{
		getGameSessionRoundService().saveGameSessionRoundPlayerVariable(this);
	}

	public String getPlayerUuid() {
		return playerUuid;
	}

	public void setPlayerUuid(String playerUuid) {
		this.playerUuid = playerUuid;
	}


}
