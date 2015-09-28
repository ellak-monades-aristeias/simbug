package gr.aua.simbug.model;

import gr.aua.simbug.game.GameSessionRoundPlayerVariable;

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
 * The {@code GameSessionRoundPlayerVariable} class represents game session round player variables.
 * 
 * @author michael
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "game_session_round_player_variable")
public class DbGameSessionRoundPlayerVariable implements Serializable
{
	/**
	 * The ID of this game session round player variable.
	 */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * The player_uuid of this game session player variable.
	 */
	@Column(name = "player_uuid", nullable = false, length = 100)
	private String playerUuid;

	/**
	 * The round of this game session round.
	 */
	@Column(name = "round_num", nullable = false)
	private Long roundNum;

	/**
	 * The category of this game session variable.
	 */
	@Column(name = "category", nullable = false)
	private Integer category;

	/**
	 * The variable_name.
	 */
	@Column(name = "variable_name", nullable = false, length = 50)
	private String variableName;

	/**
	 * The variable_value.
	 */
	@Column(name = "variable_value", nullable = true, length = 100)
	private String variableValue;
	
	/**
	 * The variable_type.
	 */
	@Column(name = "variable_type", nullable = false, length = 100)
	private String variableType;

	/**
	 * The game session of this round.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "game_session_id", nullable = false)
	private DbGameSession gameSession;

	transient private String sessionUuid;

	public DbGameSessionRoundPlayerVariable() 
	{
	}

	/**
	 * 
	 * @param gsrpv
	 */
	public DbGameSessionRoundPlayerVariable(GameSessionRoundPlayerVariable gsrpv) 
	{
		this.category = gsrpv.getVariableCategory();
		this.variableName = gsrpv.getVariableName();
		this.variableValue = gsrpv.getVariableValue();
		this.variableType = gsrpv.getVariableType();
		this.sessionUuid = gsrpv.getUuidOfGameSession();
		this.roundNum = gsrpv.getRoundNum();
		this.playerUuid = gsrpv.getPlayerUuid();
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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
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