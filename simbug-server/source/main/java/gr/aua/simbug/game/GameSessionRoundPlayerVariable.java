package gr.aua.simbug.game;

import gr.aua.simbug.definition.VariableType;

public class GameSessionRoundPlayerVariable extends GameSessionRoundVariable
{

	private String playerUuid;
	
	public GameSessionRoundPlayerVariable(int category, VariableType param, String uuidOfGameSession, int roundNum, String uuid) 
	{
		// TODO Auto-generated constructor stub
		super(category, param, uuidOfGameSession, roundNum);
		this.setPlayerUuid(uuid);
	}

	public String getPlayerUuid() {
		return playerUuid;
	}

	public void setPlayerUuid(String playerUuid) {
		this.playerUuid = playerUuid;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		super.save();
	}

}
