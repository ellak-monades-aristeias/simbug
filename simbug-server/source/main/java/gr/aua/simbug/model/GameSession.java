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
 * The {@code GameSession} class represents games.
 * 
 * @author michael
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "game_session")
public class GameSession implements Serializable
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

}