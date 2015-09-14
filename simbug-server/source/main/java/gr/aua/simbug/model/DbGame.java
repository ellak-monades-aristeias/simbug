package gr.aua.simbug.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The {@code Game} class represents games.
 * 
 * @author michael
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "game")
public class DbGame implements Serializable
{
	/**
	 * The ID of this game.
	 */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * The name of this game.
	 */
	@Column(name = "name", nullable = false, unique = true, length = 200)
	private String name;

	/**
	 * The type of this game.
	 */
	@Column(name = "type", nullable = true, length = 500)
	private String type;

}