package ddo.argonnessen.argonauts.discord;

import ddo.argonnessen.argonauts.discord.cmd.CommandExecution;

/**
 * discord commands
 */
public enum Command {

	/**
	 * total online on server
	 */
	COUNT,
	/**
	 * who
	 */
	WHO,
	/**
	 * help
	 */
	HELP;

	/**
	 * commandExecution to set
	 */
	private CommandExecution commandExecution;

	/**
	 * @return the commandExecution
	 */
	public CommandExecution getCommandExecution() {
		return commandExecution;
	}

	/**
	 * @param commandExecution
	 *            the commandExecution to set
	 */
	public void setCommandExecution(CommandExecution commandExecution) {
		this.commandExecution = commandExecution;
	}
}
