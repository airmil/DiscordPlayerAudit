package ddo.argonnessen.argonauts.common.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddo.argonnessen.argonauts.common.po.Guild;

/**
 * 
 */
@Repository
public interface GuildRepository extends JpaRepository<Guild, String> {
	// empty
}
