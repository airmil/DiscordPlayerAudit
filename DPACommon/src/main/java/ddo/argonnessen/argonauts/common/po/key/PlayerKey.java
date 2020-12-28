package ddo.argonnessen.argonauts.common.po.key;

import ddo.argonnessen.argonauts.common.bean.Named;

/**
 * key for {@link ddo.argonnessen.argonauts.common.po.Player}
 */
public class PlayerKey implements Named {

	/**
	 * name
	 */
	private String name;
	/**
	 * 
	 */
	private String serverName;

	/**
	 * @return serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
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
			return key.name.equals(name) && key.serverName.equals(serverName);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (name + serverName).hashCode();
	}
}
