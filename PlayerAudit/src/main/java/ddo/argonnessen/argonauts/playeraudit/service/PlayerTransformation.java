/**
 * 
 */
package ddo.argonnessen.argonauts.playeraudit.service;

import static ddo.argonnessen.argonauts.playeraudit.util.PlayerAuditConstants.ANONYMOUS;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.arch.Pair;
import ddo.argonnessen.argonauts.arch.Transformation;
import ddo.argonnessen.argonauts.common.po.Classes;
import ddo.argonnessen.argonauts.common.po.Guild;
import ddo.argonnessen.argonauts.common.po.Location;
import ddo.argonnessen.argonauts.common.po.PlayerClass;
import ddo.argonnessen.argonauts.common.po.Server;
import ddo.argonnessen.argonauts.common.po.repository.ClassesRepository;
import ddo.argonnessen.argonauts.common.po.repository.GuildRepository;
import ddo.argonnessen.argonauts.common.po.repository.LocationRepository;
import ddo.argonnessen.argonauts.common.po.repository.PlayerClassRepository;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;
import ddo.argonnessen.argonauts.playeraudit.po.Player;

/**
 * 
 */
@Service
public class PlayerTransformation implements Transformation<Pair<Player, Server>, ddo.argonnessen.argonauts.common.po.Player> {

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
	/**
	 * 
	 */
	@Autowired
	LocationRepository locationRepository;
	/**
	 * 
	 */
	@Autowired
	ClassesRepository classesRepository;
	/**
	 * 
	 */
	@Autowired
	PlayerClassRepository playerClassesRepository;

	/**
	 * 
	 */
	static AtomicInteger count = new AtomicInteger(1);

	@Override
	public ddo.argonnessen.argonauts.common.po.Player execute(Pair<Player, Server> a) {
		ddo.argonnessen.argonauts.common.po.Player player = getPlayer(a);
		return player;
	}

	/**
	 * @param a
	 * @return
	 */
	ddo.argonnessen.argonauts.common.po.Player getPlayer(Pair<Player, Server> a) {
		Player p=a.getLeft();
		ddo.argonnessen.argonauts.common.po.Player player = new ddo.argonnessen.argonauts.common.po.Player();
		player.getKey().setServer(a.getRight());
		player.setGender(p.getGender());
		player.setGroupId(p.getGroupId());
		player.setGuild(getGuild(p));
		player.setInParty(new Integer(1).equals(p.getInParty()));
		player.setLocation(getLocation(p));
		player.setName(getName(p));
		player.setPlayerClasses(getPlayerClasses(p, player.getKey().getServer(), player));
		player.setRace(p.getRace());
		return player;
	}

	/**
	 * @param p
	 * @return name
	 */
	private String getName(Player p) {
		String name = p.getName();
		if (ANONYMOUS.equals(name)) {
			name += "#" + count.getAndIncrement(); //$NON-NLS-1$
		}
		return name;
	}

	/**
	 * @param a
	 * @param server
	 * @param player
	 * @return
	 */
	Set<PlayerClass> getPlayerClasses(Player a, ddo.argonnessen.argonauts.common.po.Server server,
			ddo.argonnessen.argonauts.common.po.Player player) {
		Set<PlayerClass> classesSet = new HashSet<PlayerClass>();
		Set<ddo.argonnessen.argonauts.playeraudit.po.PlayerClass> classes = a.getClasses();
		for (ddo.argonnessen.argonauts.playeraudit.po.PlayerClass playerClass : classes) {
			Classes c = getClass(playerClass);
			if (c == null) {
				continue;
			}
			PlayerClass pc = new PlayerClass();
			pc.setLevel(playerClass.getLevel());
			pc.getKey().setName(a.getName());
			pc.getKey().setClasses(c);
			pc.getKey().setServer(server);
			pc.setPlayer(player);
			classesSet.add(pc);
		}
		return classesSet;
	}

	/**
	 * @param playerClass
	 * @return
	 */
	Classes getClass(ddo.argonnessen.argonauts.playeraudit.po.PlayerClass playerClass) {
		String name = playerClass.getName();
		if (name == null) {
			return null;
		}
		Optional<Classes> findById = classesRepository.findById(name);
		if (!findById.isPresent()) {
			Classes c = createClass(playerClass);
			return c;
		}
		return findById.get();
	}

	/**
	 * @param playerClass
	 * @return
	 */
	Classes createClass(ddo.argonnessen.argonauts.playeraudit.po.PlayerClass playerClass) {
		Classes c = new Classes();
		c.setName(playerClass.getName());
		c = classesRepository.saveAndFlush(c);
		return c;
	}

	/**
	 * @param a
	 * @return
	 */
	Location getLocation(Player a) {
		String hexId = a.getLocation().getHexId();
		if (hexId == null) {
			return null;
		}
		Optional<Location> findById = locationRepository.findById(hexId);
		if (!findById.isPresent()) {
			Location l = createLocation(a);
			return l;
		}
		return findById.get();
	}

	/**
	 * @param a
	 * @return
	 */
	Location createLocation(Player a) {
		Location l = new Location();
		ddo.argonnessen.argonauts.playeraudit.po.Location location = a.getLocation();
		l.setHexId(location.getHexId());
		l.setIsPublicSpace(location.getIsPublicSpace());
		l.setName(location.getName());
		l.setRegion(location.getRegion());
		l = locationRepository.saveAndFlush(l);
		return l;
	}

	/**
	 * @param a
	 * @return
	 */
	Guild getGuild(Player a) {
		Optional<Guild> findById = guildRepository.findById(a.getGuild());
		if (!findById.isPresent()) {
			Guild g = createGuild(a);
			return g;
		}
		return findById.get();
	}

	/**
	 * @param a
	 * @return
	 */
	Guild createGuild(Player a) {
		Guild g = new Guild();
		g.setName(a.getGuild());
		g = guildRepository.saveAndFlush(g);
		return g;
	}
}
