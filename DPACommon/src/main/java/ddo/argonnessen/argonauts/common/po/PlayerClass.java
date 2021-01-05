/**
 * 
 */
package ddo.argonnessen.argonauts.common.po;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
}
