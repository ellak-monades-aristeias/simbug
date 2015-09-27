package gr.aua.simbug.model;

import gr.aua.simbug.game.GameSessionRoundPlayer;

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
 * The {@code GameSession} class represents games.
 * 
 * @author michael
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "game_session_round_player")
public class DbGameSessionRoundPlayer implements Serializable
{
	/**
	 * The ID of this game session.
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
	 * The round of this game session round.
	 */
	@Column(name = "round_num", nullable = false)
	private Long roundNum;

	/**
	 * The game session of this round.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "game_session_id", nullable = false)
	private DbGameSession gameSession;

	transient private String sessionUuid;

	/**
	 * 
	 */
	public DbGameSessionRoundPlayer() 
	{
	}

	/**
	 * 
	 * @param gameSessionRoundPlayer
	 */
	public DbGameSessionRoundPlayer(GameSessionRoundPlayer gsrp) 
	{
		this.playerUuid = gsrp.getUuid();
		this.sessionUuid = gsrp.getGameSession().getUuidOfGameSession();
		this.roundNum = gsrp.getRoundNum();
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

	public Long getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(Long roundNum) {
		this.roundNum = roundNum;
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