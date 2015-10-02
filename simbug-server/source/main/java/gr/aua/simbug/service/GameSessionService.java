package gr.aua.simbug.service;

import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionPlayer;
import gr.aua.simbug.game.GameSessionRoundPlayerVariable;
import gr.aua.simbug.game.GameSessionRoundVariable;
import gr.aua.simbug.game.GameSessionVariable;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface GameSessionService
{

	void saveGameSession(GameSession gameSession);

	void deleteGameSession(String uuidOfGameSession);

	GameSession fetchGameSessionByUuid(String uuidOfGameSession);

	void saveGameSessionVariable(GameSessionVariable gameSessionVariable);
	
	List<GameSessionPlayer> fetchListOfGameSessionPlayersBySessionUuid(String uuid);

	List<GameSessionVariable> fetchConfigurationBySessionUuid(String sessionUuid);

	List<GameSessionRoundPlayerVariable> fetchChoiceVariablesBySessionUuid(String sessionUuid);

	List<GameSessionRoundPlayerVariable> fetchStateVariablesBySessionUuid(String sessionUuid);

	List<GameSessionRoundVariable> fetchWorldStateVariablesByUuid(GameSession gameSession);

}
