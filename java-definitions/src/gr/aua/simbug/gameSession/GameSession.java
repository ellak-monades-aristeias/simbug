package gr.aua.simbug.gameSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.mozilla.javascript.Context;

import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.definition.ParameterType;
import gr.aua.simbug.definition.VariableType;

public class GameSession {
	
	private Definition definition;
	private ArrayList player;
	
	private int currentRound;
	
	private Context rhinoContext = Context.enter();
	

	public GameSession(Definition definition, ArrayList player, int currentRound) {
		super();
		this.definition = definition;
		this.player = player;
		this.currentRound = currentRound;
	}
	
	
	public HashMap<VariableType,?> advanceTurn(MultiKeyMap<K, V> Playerchoices) {
		
		//construct INFO array
		
		//CONSTRUCT CONFIG array
		
		//CONSTRUCT PLAYER_STATE_VARABLES array
		
		//CONSTRUCT WORLD_STATE_VARIABLES array
		
		
		return null;		
	}
	
	
	

}
	

