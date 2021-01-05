package ddo.argonnessen.argonauts.common.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import ddo.argonnessen.argonauts.common.bean.Named;

/**
 * Persistent Object for Server
 */
@Entity
public class Server implements Named, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * name
	 */
	@Id
	private String name;

	/**
	 * default constructor
	 */
	public Server() {
		super();
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
