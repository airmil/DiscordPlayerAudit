package ddo.argonnessen.argonauts.common.po.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ddo.argonnessen.argonauts.common.bean.Named;
import ddo.argonnessen.argonauts.common.po.Classes;
import ddo.argonnessen.argonauts.common.po.Server;

/**
 * key for {@link ddo.argonnessen.argonauts.common.po.PlayerClass}
 */
@Embeddable
public class PlayerClassKey implements Named, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * name
	 */
	@Column
	String name;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "servername")
	Server server;
	/**
	 * class
	 */
	@ManyToOne
	@JoinColumn(name = "clazz")
	Classes classes;

	/**
	 * @return serverName
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * @param server
	 */
	public void setServer(Server server) {
		this.server = server;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the {@link Classes}
	 */
	public Classes getClasses() {
		return classes;
	}

	/**
	 * @param classes
	 *            the Classes to set
	 */
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof PlayerClassKey) {
			PlayerClassKey key = (PlayerClassKey) obj;
			return key.name.equals(name) && key.server.equals(server) && key.classes.equals(classes);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (name + server + classes).hashCode();
	}

	@Override
	public String toString() {
		return name + "," + server.getName() + "," + classes.getName(); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
