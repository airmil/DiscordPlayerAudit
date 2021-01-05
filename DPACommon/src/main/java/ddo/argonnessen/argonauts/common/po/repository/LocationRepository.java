package ddo.argonnessen.argonauts.common.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddo.argonnessen.argonauts.common.po.Location;

/**
 * 
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
	// empty
}
