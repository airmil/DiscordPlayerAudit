package ddo.argonnessen.argonauts.poc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.common.po.Server;
import ddo.argonnessen.argonauts.common.po.repository.ServerRepository;

/**
 * 
 */
@Service
public class PocSpringService {

	/**
	 * 
	 */
	@Autowired
	ServerRepository serverRepository;

	/**
	 * @return all servers
	 */
	public List<Server> list() {
		return serverRepository.findAll();
	}

	/**
	 * @param name
	 * @return server
	 */
	public Server load(String name) {
		Optional<Server> findById = serverRepository.findById(name);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
}
