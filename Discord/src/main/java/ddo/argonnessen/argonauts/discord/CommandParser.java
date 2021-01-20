package ddo.argonnessen.argonauts.discord;

/**
 * 
 */
public class CommandParser {

	/**
	 * 
	 */
	private static final String DELIMETER = " "; //$NON-NLS-1$

	/**
	 * 
	 */
	private static final String COMMAND_HEADER = "`"; //$NON-NLS-1$


	/**
	 * parse the input and converts it into output
	 * 
	 * @param input
	 * @return command bean
	 */
	public CommandBean parse(String input) {
		CommandBean output = null;
		if (input == null || !input.startsWith(COMMAND_HEADER)) {
			return null;
		}
		String in = input.substring(COMMAND_HEADER.length());
		String[] split = in.split(DELIMETER);
		Command c = Command.valueOf(split[0].toUpperCase());
		if (c == null) {
			return null;
		}
		output = new CommandBean();
		output.setCommand(c);
		for (int i = 1; i < split.length; i++) {
			output.getPayload().add(split[i]);
		}
		return output;
	}
}
