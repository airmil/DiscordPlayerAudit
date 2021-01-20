/**
 * 
 */
package ddo.argonnessen.argonauts.discord.cmd;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.common.po.Guild;
import ddo.argonnessen.argonauts.common.po.Server;
import ddo.argonnessen.argonauts.common.po.repository.GuildRepository;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;
import ddo.argonnessen.argonauts.discord.CommandBean;
import ddo.argonnessen.argonauts.discord.CommandPayLoad;
import ddo.argonnessen.argonauts.discord.exception.CommandException;

/**
 * 
 */
@Service
public interface CommandExecution {

	/**
	 * @param bean
	 * @return the output
	 * @throws CommandException
	 */
	String execute(CommandBean bean) throws CommandException;

	/**
	 * @param payload
	 * @param guildRepository
	 * @return guild
	 * @throws CommandException
	 */
	default Guild getGuild(CommandPayLoad payload, GuildRepository guildRepository) throws CommandException {
		if (payload != null && payload.size() > 1) {
			String id = payload.get(1);
			for (int i = 2; i < payload.size(); i++) {
				id += " " + payload.get(i); //$NON-NLS-1$
			}
			Optional<Guild> g = guildRepository.findById(id);
			if (!g.isPresent()) {
				throw new CommandException("There is no guild with the name **" + id + "**"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return g.get();
		}
		return null;
	}

	/**
	 * @param payload
	 * @param serverRepository
	 * @return server
	 * @throws CommandException
	 */
	default Server getServer(CommandPayLoad payload, ServerRepository serverRepository) throws CommandException {
		if (payload != null && payload.size() > 0) {
			String id = payload.get(0);
			Optional<Server> s = serverRepository.findById(id);
			if (!s.isPresent()) {
				throw new CommandException("There is no server with the name **" + id + "**"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return s.get();
		}
		return null;
	}

	/**
	 * @return empty message
	 */
	@SuppressWarnings("nls")
	default String emptyMessage() {
		return "";
	}
}
