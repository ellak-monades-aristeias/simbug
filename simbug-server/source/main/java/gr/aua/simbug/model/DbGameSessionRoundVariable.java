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
 * The {@code GameSessionRound} class represents game session rounds.
 * 
 * @author michael
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "game_session_round")
public class DbGameSessionRoundVariable implements Serializable
{
    /**
	 * The ID of this game session round.
	 */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * The round of this game session round.
	 */
	@Column(name = "round_num", nullable = false)
	private Integer roundNum;

	/**
	 * The variable_name.
	 */
	@Column(name = "variable_name", nullable = false, length = 50)
	private String variableName;

	/**
	 * The variable_value.
	 */
	@Column(name = "variable_value", nullable = false, length = 100)
	private String variableValue;
	
	/**
	 * The variable_type.
	 */
	@Column(name = "variable_type", nullable = false, length = 100)
	private String variableType;

	/**
	 * The game session of this round.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_session_id", nullable = false)
	private DbGameSession gameSession;

}