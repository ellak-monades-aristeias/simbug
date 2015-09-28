package gr.aua.simbug.service;

import gr.aua.simbug.game.GameSession;
import gr.aua.simbug.game.GameSessionVariable;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface GameSessionService
{

	void saveGameSession(GameSession gameSession);

	void deleteGameSession(String uuidOfGameSession);

	GameSession fetchGameSessionByUuid(String uuidOfGameSession);

	void saveGameSessionVariable(GameSessionVariable gameSessionVariable);
}
