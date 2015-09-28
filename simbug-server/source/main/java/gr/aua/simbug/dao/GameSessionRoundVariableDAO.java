package gr.aua.simbug.dao;

import java.util.List;

import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundVariable;


public interface GameSessionRoundVariableDAO
{

	void save(DbGameSessionRoundVariable dbGameSessionRoundVariable);

	List<DbGameSessionRoundVariable> findGameSessionRoundVariablesByUuidByRound(String uuidOfGameSession, long currentRound);

	List<DbGameSessionRoundPlayerVariable> findGameSessionRoundPlayerVariablesByUuidByRoundByPlayer(
			String uuidOfGameSession, long currentRound, String uuidOfPlayer);

}
