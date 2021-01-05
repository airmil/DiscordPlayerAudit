package ddo.argonnessen.argonauts.playeraudit.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.common.po.repository.PlayerRepository;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;
import ddo.argonnessen.argonauts.playeraudit.PlayerAudit;
import ddo.argonnessen.argonauts.playeraudit.exception.PlayerAuditException;
import ddo.argonnessen.argonauts.playeraudit.po.Player;
import ddo.argonnessen.argonauts.playeraudit.po.Server;

/**
 * service to call on player audit and update the db
 */
@Service
public class PlayerAuditCallService {

	/**
	 * 
	 */
	ServerTransformation serverTransformation = new ServerTransformation();
	/**
	 * 
	 */
	@Autowired
	ServerRepository serverRepository;
	/**
	 * 
	 */
	@Autowired
	PlayerRepository playerRepository;

	/**
	 * do the actual job
	 * 
	 * @throws PlayerAuditException
	 */
	public void execute() throws PlayerAuditException {
		PlayerAudit audit = new PlayerAudit();
		Collection<String> serverNames = audit.getServerNames();
		for (String name : serverNames) {
			Server server = audit.getServer(name);
			Set<Player> players = server.getPlayers();
			Set<ddo.argonnessen.argonauts.common.po.Player> playerPos = convertPlayers(players);
			ddo.argonnessen.argonauts.common.po.Server s = convertServer(server);
			store(playerPos, s);
		}
	}

	/**
	 * @param playerPos
	 * @param server
	 */
	void store(Set<ddo.argonnessen.argonauts.common.po.Player> playerPos, ddo.argonnessen.argonauts.common.po.Server server) {
		serverRepository.save(server);
		for (ddo.argonnessen.argonauts.common.po.Player player : playerPos) {
			playerRepository.save(player);
		}
	}

	/**
	 * @param server
	 * @return
	 */
	ddo.argonnessen.argonauts.common.po.Server convertServer(Server server) {
		return serverTransformation.execute(server);
	}

	/**
	 * convert the {@link Player}s into {@link ddo.argonnessen.argonauts.common.po.Player}s
	 * 
	 * @param players
	 * @return
	 */
	private Set<ddo.argonnessen.argonauts.common.po.Player> convertPlayers(Set<Player> players) {
		Set<ddo.argonnessen.argonauts.common.po.Player> playerPos = new HashSet<>();
		return playerPos;
	}
}