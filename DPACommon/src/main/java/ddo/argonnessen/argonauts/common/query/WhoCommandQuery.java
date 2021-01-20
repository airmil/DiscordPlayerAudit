package ddo.argonnessen.argonauts.common.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import ddo.argonnessen.argonauts.common.po.Guild;
import ddo.argonnessen.argonauts.common.po.Player;
import ddo.argonnessen.argonauts.common.po.Server;
import ddo.argonnessen.argonauts.common.po.repository.PlayerRepository;

/**
 * queries for count command
 */
@Repository
public class WhoCommandQuery {

	/**
	 * 
	 */
	@Autowired
	PlayerRepository pr;

	/**
	 * @param s
	 * @param g
	 * @return count
	 */
	public Collection<Player> who(Server s, Guild g) {
		return pr.findAll(new Specification<Player>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (s != null) {
					predicates.add(criteriaBuilder.equal(root.get("key").get("server"), s)); //$NON-NLS-1$ //$NON-NLS-2$
				}
				if (g != null) {
					predicates.add(criteriaBuilder.equal(root.get("guild"), g)); //$NON-NLS-1$
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}
}
