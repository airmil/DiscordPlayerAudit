package ddo.argonnessen.argonauts.discord.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.common.po.Guild;
import ddo.argonnessen.argonauts.common.po.Server;
import ddo.argonnessen.argonauts.common.po.repository.GuildRepository;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;
import ddo.argonnessen.argonauts.common.query.CountCommandQuery;
import ddo.argonnessen.argonauts.discord.Command;
import ddo.argonnessen.argonauts.discord.CommandBean;
import ddo.argonnessen.argonauts.discord.CommandPayLoad;
import ddo.argonnessen.argonauts.discord.exception.CommandException;

/**
 * expected payload count
 * 0 count all users
 * 1 -> server
 * 2 -> guild
 */
@Service
public class CountCommandExecution implements CommandExecution {

	/**
	 * query
	 */
	@Autowired
	CountCommandQuery query;
	/**
	 * server repository
	 */
	@Autowired
	ServerRepository serverRepository;
	/**
	 * 
	 */
	@Autowired
	GuildRepository guildRepository;

	@Override
	public String execute(CommandBean a) throws CommandException {
		if (!Command.COUNT.equals(a.getCommand())) {
			return emptyMessage();
		}
		CommandPayLoad payload = a.getPayload();
		Server s = getServer(payload, serverRepository);
		Guild g = getGuild(payload, guildRepository);
		Integer count = query.count(s, g);
		return getMessage(count, s, g);
	}

	/**
	 * @param count
	 * @param s
	 * @param g
	 * @return
	 */
	@SuppressWarnings("nls")
	String getMessage(Integer count, Server s, Guild g) {
		StringBuilder sb = new StringBuilder();
		sb.append("There are "); //$NON-NLS-1$
		sb.append(count);
		sb.append(" players"); //$NON-NLS-1$
		if (s == null) {
			sb.append(" in DDO"); //$NON-NLS-1$
		} else {
			sb.append(" on ").append("**").append(s.getName()).append("**");
			if (g != null) {
				sb.append(" for ").append("**").append(g.getName()).append("**");
			}
		}
		return sb.toString();
	}


}
