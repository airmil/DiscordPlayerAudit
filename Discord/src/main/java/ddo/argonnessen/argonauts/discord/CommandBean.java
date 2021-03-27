package ddo.argonnessen.argonauts.discord;

/**
 * command bean
 */
public class CommandBean {

	/**
	 * 
	 */
	private Command command;
	/**
	 * 
	 */
	private CommandPayLoad payload = new CommandPayLoadImpl();
	/**
	 * the user who issued the command
	 */
	private String issuer;
	/**
	 * discord server that the command was issued
	 */
	private String discordServer;

	/**
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * @return the payload
	 */
	public CommandPayLoad getPayload() {
		return payload;
	}

	/**
	 * @param payload
	 *            the target to set
	 */
	public void setPayload(CommandPayLoad payload) {
		this.payload = payload;
	}

	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param issuer the issuer to set
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * @return the discordServer
	 */
	public String getDiscordServer() {
		return discordServer;
	}

	/**
	 * @param discordServer the discordServer to set
	 */
	public void setDiscordServer(String discordServer) {
		this.discordServer = discordServer;
	}
}
