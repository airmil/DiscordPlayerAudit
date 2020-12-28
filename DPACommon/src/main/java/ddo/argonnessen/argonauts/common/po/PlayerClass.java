/**
 * 
 */
package ddo.argonnessen.argonauts.common.po;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 */
@Entity
public class PlayerClass {

	/**
	 * name
	 */
	@Id
	Classes clazz;

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
