package ddo.argonnessen.argonauts.playeraudit.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddo.argonnessen.argonauts.arch.Pair;
import ddo.argonnessen.argonauts.common.po.repository.PlayerRepository;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;
import ddo.argonnessen.argonauts.common.query.EmptyTableQuery;
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
	PlayerTransformation playerTransformation = new PlayerTransformation();
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
	 * 
	 */
	@Autowired
	EmptyTableQuery query;

	/**
	 * do the actual job
	 * 
	 * @throws PlayerAuditException
	 */
	@Transactional
	public void execute() throws PlayerAuditException {
		PlayerAudit audit = new PlayerAudit();
		query.execute();
		Collection<String> serverNames = audit.getServerNames();
		for (String name : serverNames) {
			Server server = audit.getServer(name);
			ddo.argonnessen.argonauts.common.po.Server s = convertServer(server);
			storeServer(s);
			Set<Player> players = server.getPlayers();
			Set<ddo.argonnessen.argonauts.common.po.Player> playerPos = convertPlayers(players, server);
			storePlayers(playerPos);
		}
	}

	/**
	 * @param playerPos
	 * @param server
	 */
	void storeServer(ddo.argonnessen.argonauts.common.po.Server server) {
		serverRepository.saveAndFlush(server);
		serverRepository.flush();
	}

	/**
	 * @param playerPos
	 */
	void storePlayers(Set<ddo.argonnessen.argonauts.common.po.Player> playerPos) {
		for (ddo.argonnessen.argonauts.common.po.Player player : playerPos) {
			playerRepository.saveAndFlush(player);
		}
		playerRepository.flush();
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
	 * @param s
	 * @return
	 */
	private Set<ddo.argonnessen.argonauts.common.po.Player> convertPlayers(Set<Player> players, Server s) {
		Set<ddo.argonnessen.argonauts.common.po.Player> playerPos = new HashSet<>();
		for (Player player : players) {
			ddo.argonnessen.argonauts.common.po.Player player2 = playerTransformation.execute(new Pair<Player, Server>(player, s));
			playerPos.add(player2);
		}
		return playerPos;
	}
}
