package ddo.argonnessen.argonauts.common.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddo.argonnessen.argonauts.common.po.PlayerClass;

/**
 * 
 */
@Repository
public interface PlayerClassRepository extends JpaRepository<PlayerClass, String> {
	// empty
}
