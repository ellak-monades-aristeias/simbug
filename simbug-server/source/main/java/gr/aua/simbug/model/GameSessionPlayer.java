package gr.aua.simbug.model;

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
public class GameSessionPlayer implements Serializable
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_session_id", nullable = false)
	private GameSession gameSession;

}