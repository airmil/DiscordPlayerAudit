/**
 * 
 */
package ddo.argonnessen.argonauts.discord.exception;


/**
 * 
 */
public class CommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new CommandException.
	 */
	public CommandException() {
		super();
	}

	/**
	 * Creates a new CommandException with a message.
	 *
	 * @param message
	 *            the message
	 */
	public CommandException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new data exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new data exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public CommandException(Throwable cause) {
		super(cause);
	}
}
