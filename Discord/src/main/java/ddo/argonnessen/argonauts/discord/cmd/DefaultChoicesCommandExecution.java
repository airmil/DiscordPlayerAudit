package ddo.argonnessen.argonauts.discord.cmd;

import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.discord.Command;
import ddo.argonnessen.argonauts.discord.CommandBean;
import ddo.argonnessen.argonauts.discord.exception.CommandException;

/**
 * expected payload count
 * 0 count all users
 * 1 -> server
 * 2 -> guild
 */
@Service
public class DefaultChoicesCommandExecution implements CommandExecution {

	@Override
	public String execute(CommandBean bean) throws CommandException {
		if (!Command.DEFAULT.equals(bean.getCommand())) {
			return emptyMessage();
		}
		return null;
	}

}
