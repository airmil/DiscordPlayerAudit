package ddo.argonnessen.argonauts.common.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddo.argonnessen.argonauts.common.po.Classes;

/**
 * 
 */
@Repository
public interface ClassesRepository extends JpaRepository<Classes, String> {
	// empty
}
