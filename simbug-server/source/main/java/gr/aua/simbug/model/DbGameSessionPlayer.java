package gr.aua.simbug.model;

import gr.aua.simbug.game.GameSessionPlayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The {@code GameSessionPlayer} class represents game session players.
 * 
 * @author michael
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "game_session_player")
public class DbGameSessionPlayer implements Serializable
{
	/**
	 * The ID of this game session player.
	 */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * The player_uuid of this game session player.
	 */
	@Column(name = "player_uuid", nullable = false, length = 100)
	private String playerUuid;

	/**
	 * The game session of this player.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "game_session_id", nullable = false)
	private DbGameSession gameSession;

	transient private String sessionUuid;
	
	/**
	 * 
	 * @param gsp
	 */
	public DbGameSessionPlayer(GameSessionPlayer gsp) 
	{
		this.playerUuid = gsp.getUuid();
		this.sessionUuid = gsp.getGameSession().getUuidOfGameSession();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerUuid() {
		return playerUuid;
	}

	public void setPlayerUuid(String playerUuid) {
		this.playerUuid = playerUuid;
	}

	public DbGameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(DbGameSession gameSession) {
		this.gameSession = gameSession;
	}

	public String getSessionUuid() {
		return sessionUuid;
	}

	public void setSessionUuid(String sessionUuid) {
		this.sessionUuid = sessionUuid;
	}

}