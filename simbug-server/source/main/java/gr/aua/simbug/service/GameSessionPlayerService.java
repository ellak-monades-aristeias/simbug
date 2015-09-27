package gr.aua.simbug.service;

import gr.aua.simbug.game.GameSessionPlayer;
import gr.aua.simbug.game.GameSessionRoundPlayer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface GameSessionPlayerService
{

	void save(GameSessionPlayer gameSessionPlayer);
	
	void delete(String uuid);
	
	GameSessionPlayer fetchGameSessionPlayerByPlayerUuid(String uuid);
	
	List<GameSessionPlayer> fetchListOfGameSessionPlayersBySessionUuid(String uuid);

	void saveRoundPlayer(GameSessionRoundPlayer gameSessionRoundPlayer);
	
}
