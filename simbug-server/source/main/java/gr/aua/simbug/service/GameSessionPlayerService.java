package gr.aua.simbug.service;

import gr.aua.simbug.game.GameSessionPlayer;
import gr.aua.simbug.game.GameSessionRoundPlayer;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface GameSessionPlayerService
{

	void save(GameSessionPlayer gameSessionPlayer);
	
	void delete(String uuid);
	
	GameSessionPlayer fetchGameSessionPlayerByPlayerUuid(String uuid);
	
	void saveRoundPlayer(GameSessionRoundPlayer gameSessionRoundPlayer);

	void updateRoundPlayerVariable(GameSessionRoundPlayerVariable gsrpv);
	
}
