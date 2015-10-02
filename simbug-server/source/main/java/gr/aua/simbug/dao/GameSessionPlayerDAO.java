package gr.aua.simbug.dao;

import java.util.List;

import gr.aua.simbug.model.DbGameSessionPlayer;

public interface GameSessionPlayerDAO
{

	void save(DbGameSessionPlayer dbGameSessionPlayer);

	List<DbGameSessionPlayer> findGameSessionPlayersBySessionUuid(String uuid);

}
