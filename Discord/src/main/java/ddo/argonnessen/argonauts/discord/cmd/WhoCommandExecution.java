package ddo.argonnessen.argonauts.discord.cmd;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
			return emptyMessage();
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
	@SuppressWarnings("nls")
	String getMessage(Collection<Player> players, Server s, Guild g) {
		StringBuilder sb = new StringBuilder();
		if (players.size() == 0) {
			sb.append("None is pressent");
			return sb.toString();
		}
		if (players.size() > 1) {
			sb.append("These are ");
		} else {
			sb.append("This is ");
		}
		sb.append(getPlayers(players));
		if (players.size() > 1) {
			sb.append(" the players present");
		} else {
			sb.append(" the player present");
		}
		if (s == null) {
			sb.append(" in DDO");
		} else {
			sb.append(" on **").append(s.getName()).append("**");
			if (g != null) {
				sb.append(" for **").append(g.getName()).append("**");
			}
		}
		return sb.toString();
	}

	/**
	 * @param players
	 * @return
	 */
	Collection<String> getPlayers(Collection<Player> players) {
		Set<String> s = new HashSet<>();
		for (Player p : players) {
			s.add(p.getName());
		}
		return s;
	}
}
