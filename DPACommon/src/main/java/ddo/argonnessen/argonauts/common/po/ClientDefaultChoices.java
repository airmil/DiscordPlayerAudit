package ddo.argonnessen.argonauts.common.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * default choices per client
 */
@Entity
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
