package ddo.argonnessen.argonauts.common.po;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * default choices per client
 */
public class ClientDefaultChoices {

	/**
	 * discord server
	 */
	@Id
	String discordServer;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "servername")
	Server server;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "guild")
	Guild guild;
}
