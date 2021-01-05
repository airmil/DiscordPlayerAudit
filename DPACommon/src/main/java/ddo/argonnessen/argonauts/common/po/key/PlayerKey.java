package ddo.argonnessen.argonauts.common.po.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ddo.argonnessen.argonauts.common.bean.Named;
import ddo.argonnessen.argonauts.common.po.Server;

/**
 * key for {@link ddo.argonnessen.argonauts.common.po.Player}
 */
@Embeddable
public class PlayerKey implements Named, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * name
	 */
	private String name;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "servername")
	Server server;

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof PlayerKey) {
			PlayerKey key = (PlayerKey) obj;
			return key.name.equals(name) && key.server.equals(server);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (name + server).hashCode();
	}
}
