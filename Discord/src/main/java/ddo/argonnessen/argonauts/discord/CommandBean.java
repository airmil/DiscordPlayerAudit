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
}
