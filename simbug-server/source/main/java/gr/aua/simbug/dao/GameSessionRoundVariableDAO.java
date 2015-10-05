package gr.aua.simbug.dao;

import java.util.List;

import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundVariable;


public interface GameSessionRoundVariableDAO
{

	void save(DbGameSessionRoundVariable dbGameSessionRoundVariable);

	List<DbGameSessionRoundVariable> findGameSessionRoundVariablesByUuidByRound(String uuidOfGameSession, long currentRound);

	List<DbGameSessionRoundPlayerVariable> findGameSessionRoundPlayerStateVariablesByUuidByRoundByPlayer(String uuidOfGameSession, long currentRound, String uuidOfPlayer);

	List<DbGameSessionRoundPlayerVariable> findGameSessionRoundPlayerChoiceVariablesByUuidByRoundByPlayer(String uuidOfGameSession, long currentRound, String uuid);

	DbGameSessionRoundPlayerVariable findGameSessionRoundPlayerChoiceVariableByUuidByRoundByPlayer( String uuidOfGameSession, long currentRound, String uuid, String name);

	DbGameSessionRoundPlayerVariable findGameSessionRoundPlayerStateVariableByUuidByRoundByPlayer(String uuidOfGameSession, long currentRound, String uuid, String name);

	DbGameSessionRoundVariable findGameSessionRoundVariablesByUuidByRound(String uuidOfGameSession, long currentRound, String name);

}
