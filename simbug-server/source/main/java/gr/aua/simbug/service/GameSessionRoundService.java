package gr.aua.simbug.service;

import java.util.List;

import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionRound;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public interface GameSessionRoundService
{

	void save(GameSessionRound gameSessionRound);

	void saveGameSessionRoundVariable(GameSessionRoundVariable gsrv);

	void saveGameSessionRoundPlayerVariable(GameSessionRoundPlayerVariable gsrpv);

	List<GameSessionRoundVariable> fetchWorldStateVariablesByUuidByRound( GameSession gameSession);

	List<GameSessionRoundPlayerVariable> fetchPlayerStateVariablesByUuidByRoundByPlayer(GameSession gameSession, String uuidOfPlayer);

	List<GameSessionRoundPlayerVariable> fetchPlayerChoiceVariablesByUuidByRoundByPlayer(GameSession gameSession, String uuid);

	GameSessionRoundPlayerVariable fetchPlayerChoiceVariableByNameByUuidByRoundByPlayer(GameSession gameSession, String uuid, String name);

	GameSessionRoundPlayerVariable fetchPlayerStateVariableByNameByUuidByRoundByPlayer(GameSession gameSession, String uuid, String name);

	GameSessionRoundVariable fetchWorldStateVariableByNameByUuidByRound(GameSession gameSession, String name);

}
