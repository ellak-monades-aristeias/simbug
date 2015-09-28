package gr.aua.simbug.dao;

import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.model.DbGameSessionRoundPlayerVariable;


public interface GameSessionRoundPlayerVariableDAO
{

	void save(DbGameSessionRoundPlayerVariable dbGameSessionRoundPlayerVariable);

	DbGameSessionRoundPlayerVariable findByUuidByRoundByPlayerByName(GameSessionRoundPlayerVariable gsrpv);


}
