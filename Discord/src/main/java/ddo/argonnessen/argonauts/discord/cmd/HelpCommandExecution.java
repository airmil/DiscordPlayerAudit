package ddo.argonnessen.argonauts.discord.cmd;

import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.discord.Command;
import ddo.argonnessen.argonauts.discord.CommandBean;
import ddo.argonnessen.argonauts.discord.CommandParser;
import ddo.argonnessen.argonauts.discord.exception.CommandException;

/**
 * help command
 */
@Service
public class HelpCommandExecution implements CommandExecution {

	@SuppressWarnings("nls")
	@Override
	public String execute(CommandBean a) throws CommandException {
		if (!Command.HELP.equals(a.getCommand())) {
			return emptyMessage();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Discord Player audit is a simple tool to facilitate your games with DDO.\n");
		sb.append("The information is retrieved from player audit(https://www.playeraudit.com/)\n");
		sb.append("The following commands are availlable:\n");
		for (Command c : Command.values()) {
			sb.append("`").append(c.toString().toLowerCase()).append("`");
			sb.append("\n"); //$NON-NLS-1$
		}
		sb.append("To issue a command type ");
		sb.append(CommandParser.COMMAND_HEADER);
		sb.append("[command] [server name] [guild name]\n");
		return sb.toString();
	}
}
