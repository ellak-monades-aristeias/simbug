package gr.aua.simbug.model;

import gr.aua.simbug.game.GameSession;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The {@code GameSession} class represents games.
 * 
 * @author michael
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "game_session")
public class DbGameSession implements Serializable
{
	/**
	 * The ID of this game session.
	 */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * The uuid of this game session.
	 */
	@Column(name = "game_session_uuid", nullable = false, length = 100)
	private String gameSessionUuid;

	/**
	 * The definition_data of this game.
	 */
	@Column(name = "definition_data", nullable = true)
	private String definitionData;

	/**
	 * The definition_file of this game.
	 */
	@Column(name = "definition_file", nullable = true, length = 100)
	private String definitionFile;
	
	/**
	 * The players of this game.
	 */
	@Column(name = "players", nullable = true, length = 500)
	private String players;

	/**
	 * The current_round of this game session.
	 */
	@Column(name = "current_round", nullable = false)
	private Long currentRound;

	/**
	 * The game of this session.
	 */
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "game_id", nullable = false)
//	private DbGame game;

	public DbGameSession() 
	{
	}

	public DbGameSession(GameSession gs) 
	{
		this.currentRound = gs.getCurrentRound();
		this.definitionData = gs.getDefinitionData();
		this.definitionFile = null;
		this.gameSessionUuid = gs.getUuidOfGameSession();
		//this.game;
		this.players = gs.getJsonListOfPlayers();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGameSessionUuid() {
		return gameSessionUuid;
	}

	public void setGameSessionUuid(String gameSessionUuid) {
		this.gameSessionUuid = gameSessionUuid;
	}

	public String getDefinitionData() {
		return definitionData;
	}

	public void setDefinitionData(String definitionData) {
		this.definitionData = definitionData;
	}

	public String getDefinitionFile() {
		return definitionFile;
	}

	public void setDefinitionFile(String definitionFile) {
		this.definitionFile = definitionFile;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public Long getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(Long currentRound) {
		this.currentRound = currentRound;
	}

}