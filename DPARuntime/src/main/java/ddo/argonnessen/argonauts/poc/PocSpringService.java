package ddo.argonnessen.argonauts.poc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.common.po.Server;

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
}
