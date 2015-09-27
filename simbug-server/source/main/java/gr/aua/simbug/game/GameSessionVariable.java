package gr.aua.simbug.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.aua.simbug.definition.ExternalDataType;
import gr.aua.simbug.definition.ParameterType;
import gr.aua.simbug.service.GameSessionService;

@Service
public class GameSessionVariable 
{
	/**
	 * The GameSession service.
	 */
	@Autowired
	private GameSessionService gameSessionService;
	
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
	
	public void createGameSessionVariable(int category, ExternalDataType param, String uuidOfGameSession) 
	{
		this.variableName = param.getName();
		this.variableValue = param.getValue();
		this.variableType = param.getType().value();
		this.uuidOfGameSession = uuidOfGameSession;
		this.variableCategory = category;
	}
	
	public void saveSessionVariable() 
	{
		gameSessionService.saveGameSessionVariable(this);
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

	public GameSessionService getGameSessionService() {
		return gameSessionService;
	}

	public void setGameSessionService(GameSessionService gameSessionService) {
		this.gameSessionService = gameSessionService;
	}

}
