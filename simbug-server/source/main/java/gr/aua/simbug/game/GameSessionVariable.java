package gr.aua.simbug.game;

import gr.aua.simbug.definition.ParameterType;


public class GameSessionVariable 
{
	private String variableName;
	private String variableValue;	
	private String variableType;
	private int variableCategory;
	private String uuidOfGameSession;
	
	public GameSessionVariable()
	{
		
	}
	
	public GameSessionVariable(int category, ParameterType param, String uuidOfGameSession) 
	{
		// TODO Auto-generated constructor stub
		this.variableName = param.getName();
		this.variableValue = param.getValue();
		this.variableType = param.getType().value();
		this.uuidOfGameSession = uuidOfGameSession;
		this.variableCategory = category;
	}
	
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public String getVariableValue() {
		return variableValue;
	}
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}
	public String getVariableType() {
		return variableType;
	}
	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public void save() 
	{
		// TODO Auto-generated method stub
		
	}

	public int getVariableCategory() {
		return variableCategory;
	}

	public void setVariableCategory(int variableCategory) {
		this.variableCategory = variableCategory;
	}

	public String getUuidOfGameSession() {
		return uuidOfGameSession;
	}

	public void setUuidOfGameSession(String uuidOfGameSession) {
		this.uuidOfGameSession = uuidOfGameSession;
	}
	
	

}
