package ddo.argonnessen.argonauts.discord.cmd;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.common.po.Guild;
import ddo.argonnessen.argonauts.common.po.Player;
import ddo.argonnessen.argonauts.common.po.Server;
import ddo.argonnessen.argonauts.common.po.repository.GuildRepository;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;
import ddo.argonnessen.argonauts.common.query.WhoCommandQuery;
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
public class WhoCommandExecution implements CommandExecution {

	/**
	 * query
	 */
	@Autowired
	WhoCommandQuery query;
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
		if (!Command.WHO.equals(a.getCommand())) {
			return null;
		}
		CommandPayLoad payload = a.getPayload();
		Server s = getServer(payload, serverRepository);
		if (s == null) {
			throw new CommandException("Server must be set or use defaults"); //$NON-NLS-1$
		}
		Guild g = getGuild(payload, guildRepository);
		if (g == null) {
			throw new CommandException("Guild must be set or use defaults"); //$NON-NLS-1$
		}
		Collection<Player> players = query.who(s, g);
		return getMessage(players, s, g);
	}

	/**
	 * @param players
	 * @param s
	 * @param g
	 * @return
	 */
	String getMessage(Collection<Player> players, Server s, Guild g) {
		StringBuilder sb = new StringBuilder();
		sb.append("These are "); //$NON-NLS-1$
		sb.append(players);
		sb.append(" the players present"); //$NON-NLS-1$
		if (s == null) {
			sb.append(" in DDO"); //$NON-NLS-1$
		} else {
			sb.append(" on ").append(s.getName()); //$NON-NLS-1$
			if (g != null) {
				sb.append(" for ").append(g.getName()); //$NON-NLS-1$
			}
		}
		return sb.toString();
	}
}
