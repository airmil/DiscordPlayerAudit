package ddo.argonnessen.argonauts.common.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddo.argonnessen.argonauts.common.po.Player;

/**
 * 
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
	// empty
}
