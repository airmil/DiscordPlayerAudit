/**
 * 
 */
package ddo.argonnessen.argonauts.common.po;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ddo.argonnessen.argonauts.common.po.key.PlayerClassKey;

/**
 * 
 */
@Entity
@Table(name = "playerclass")
public class PlayerClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * name
	 */
	@EmbeddedId
	PlayerClassKey key = new PlayerClassKey();
	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "servername", insertable = false, updatable = false),
			@JoinColumn(name = "name", insertable = false, updatable = false) })
	Player player;

	/**
	 * level
	 */
	Integer level;


	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the key
	 */
	public PlayerClassKey getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(PlayerClassKey key) {
		this.key = key;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
