package ddo.argonnessen.argonauts.playeraudit.po;

import java.io.Serializable;

import ddo.argonnessen.argonauts.common.bean.Named;

/**
 * key for {@link Player}
 */
public class PlayerKey implements Named, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * name
	 */
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
