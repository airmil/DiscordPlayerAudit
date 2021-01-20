package ddo.argonnessen.argonauts.discord.cmd;

import java.util.HashMap;
import java.util.Map;

import ddo.argonnessen.argonauts.discord.Command;

/**
 * mapping for commandExecutions
 */
public class CommandExecutionMapper {

	/**
	 * 
	 */
	private CommandExecutionMapper() {
		// empty
	}

	/**
	 * 
	 */
	static final Map<Command, Class<? extends CommandExecution>> map = new HashMap<>();
	static {
		map.put(Command.COUNT, CountCommandExecution.class);
		map.put(Command.WHO, WhoCommandExecution.class);
		map.put(Command.HELP, HelpCommandExecution.class);
	}

	/**
	 * @param command
	 * @return {@link CommandExecution}
	 */
	public static Class<? extends CommandExecution> getCommandExecution(Command command) {
		return map.get(command);
	}

}
