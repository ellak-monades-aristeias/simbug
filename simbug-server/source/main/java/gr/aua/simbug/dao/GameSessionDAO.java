package gr.aua.simbug.dao;

import gr.aua.simbug.model.DbGameSession;

public interface GameSessionDAO
{

	void save(DbGameSession dbGameSession);

	DbGameSession findGameSessionByUuid(String uuid);

}
