package ddo.argonnessen.argonauts.poc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddo.argonnessen.argonauts.common.po.Server;

/**
 * 
 */
@Repository
public interface ServerRepository extends JpaRepository<Server, String> {
	// empty
}
