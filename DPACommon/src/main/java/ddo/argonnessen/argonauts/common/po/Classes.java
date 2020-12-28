package ddo.argonnessen.argonauts.common.po;

import javax.persistence.Entity;
import javax.persistence.Id;

import ddo.argonnessen.argonauts.common.bean.Named;

/**
 * available classes
 */
@Entity
public class Classes implements Named {

	/**
	 * name
	 */
	@Id
	String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
