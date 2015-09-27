package gr.aua.simbug.service;

import gr.aua.simbug.game.GameSessionRound;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface GameSessionRoundService
{

	void save(GameSessionRound gameSessionRound);

	void saveGameSessionRoundVariable(GameSessionRoundVariable gsrv);

	void saveGameSessionRoundPlayerVariable(GameSessionRoundPlayerVariable gsrpv);

}
