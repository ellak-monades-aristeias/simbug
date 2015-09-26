package gr.aua.simbug.game;

import gr.aua.simbug.definition.VariableType;

public class GameSessionRoundVariable extends GameSessionVariable 
{
	private int roundNum;
	
	public GameSessionRoundVariable()
	{
		
	}
	
	public GameSessionRoundVariable(int category, VariableType param, String uuidOfGameSession, int roundNum) 
	{
		super();
		// TODO Auto-generated constructor stub
		setVariableName(param.getName());
		setVariableValue(null);
		setVariableType(param.getType().value());
		setUuidOfGameSession(uuidOfGameSession);
		setVariableCategory(category);
		this.roundNum = roundNum;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		super.save();
	}

	public int getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}

}
