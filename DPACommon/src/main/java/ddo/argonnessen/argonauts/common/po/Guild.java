package ddo.argonnessen.argonauts.common.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import ddo.argonnessen.argonauts.common.bean.Named;

/**
 * guild
 */
@Entity
public class Guild implements Named, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
