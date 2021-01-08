/**
 * 
 */
package ddo.argonnessen.argonauts.playeraudit.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.arch.Pair;
import ddo.argonnessen.argonauts.arch.Transformation;
import ddo.argonnessen.argonauts.common.po.Classes;
import ddo.argonnessen.argonauts.common.po.Guild;
import ddo.argonnessen.argonauts.common.po.Location;
import ddo.argonnessen.argonauts.common.po.PlayerClass;
import ddo.argonnessen.argonauts.common.po.repository.ClassesRepository;
import ddo.argonnessen.argonauts.common.po.repository.GuildRepository;
import ddo.argonnessen.argonauts.common.po.repository.LocationRepository;
import ddo.argonnessen.argonauts.common.po.repository.PlayerClassRepository;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;
import ddo.argonnessen.argonauts.playeraudit.po.Player;
import ddo.argonnessen.argonauts.playeraudit.po.Server;

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
		player.setServer(getServer(a));
		player.setGender(p.getGender());
		player.setGroupId(p.getGroupId());
		player.setGuild(getGuild(p));
		player.setInParty(p.getInParty());
		player.setLocation(getLocation(p));
		player.setName(p.getName());
		player.setPlayerClasses(getPlayerClasses(p, player.getServer(), player));
		player.setRace(p.getRace());
		return player;
	}

	/**
	 * @param a
	 * @return
	 */
	ddo.argonnessen.argonauts.common.po.Server getServer(Pair<Player, Server> a) {
		ddo.argonnessen.argonauts.common.po.Server s = serverRepository.findById(a.getRight().getName()).get();
		return s;
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
			PlayerClass pc = new PlayerClass();
			pc.setLevel(playerClass.getLevel());
			pc.getKey().setName(a.getName());
			pc.getKey().setClasses(getClass(playerClass));
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
		Optional<Classes> findById = classesRepository.findById(playerClass.getName());
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
		c = classesRepository.save(c);
		return c;
	}

	/**
	 * @param a
	 * @return
	 */
	Location getLocation(Player a) {
		Optional<Location> findById = locationRepository.findById(a.getLocation().getHexId());
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
		l = locationRepository.save(l);
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
		g = guildRepository.save(g);
		return g;
	}
}
