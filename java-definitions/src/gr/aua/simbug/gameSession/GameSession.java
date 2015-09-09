package gr.aua.simbug.gameSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.mozilla.javascript.Context;

import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.ParameterType;
import gr.aua.simbug.definition.VariableType;

public class GameSession {
	
	private Definition definition;
	private ArrayList players;
	
	private int currentRound;
	
	private Context rhinoContext = Context.enter();
	

	public GameSession(Definition definition, ArrayList players, int currentRound) {
		super();
		this.definition = definition;
		this.players = players;
		this.currentRound = currentRound;
	}
	
	
	public HashMap<VariableType,?> advanceTurn(HashMap<ParameterType,?> confParameters) {
		
		//construct INFO array
		
		//CONSTRUCT CONFIG array
		
		//CONSTRUCT PLAYER_STATE_VARABLES array
		
		//CONSTRUCT WORLD_STATE_VARIABLES array
		
		
		return null;		
	}
	
	
	

}
	

