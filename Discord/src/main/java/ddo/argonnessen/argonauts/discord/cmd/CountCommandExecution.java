package ddo.argonnessen.argonauts.discord.cmd;

import java.util.Optional;

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
	public String execute(CommandBean a) {
		if (!Command.COUNT.equals(a.getCommand())) {
			return null;
		}
		CommandPayLoad payload = a.getPayload();
		Server s = getServer(payload);
		Guild g = getGuild(payload);
		Integer count = query.count(s, g);
		return getMessage(count, s, g);
	}

	/**
	 * @param count
	 * @param s
	 * @param g
	 * @return
	 */
	private String getMessage(Integer count, Server s, Guild g) {
		StringBuilder sb = new StringBuilder();
		sb.append("There are "); //$NON-NLS-1$
		sb.append(count);
		sb.append(" players"); //$NON-NLS-1$
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

	/**
	 * @param payload
	 * @return
	 */
	private Guild getGuild(CommandPayLoad payload) {
		if (payload != null && payload.size() > 1) {
			Optional<Guild> g = guildRepository.findById(payload.get(1));
			if (g.isPresent()) {
				return g.get();
			}
		}
		return null;
	}

	/**
	 * @param payload
	 * @return
	 */
	private Server getServer(CommandPayLoad payload) {
		if (payload!=null&&payload.size()>0) {
			Optional<Server> s = serverRepository.findById(payload.get(0));
			if (s.isPresent()) {
				return s.get();
			}
		}
		return null;
	}
}
