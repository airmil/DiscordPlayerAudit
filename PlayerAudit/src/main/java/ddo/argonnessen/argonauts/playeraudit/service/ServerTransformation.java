package ddo.argonnessen.argonauts.playeraudit.service;

import ddo.argonnessen.argonauts.arch.Transformation;
import ddo.argonnessen.argonauts.playeraudit.po.Server;

/**
 * server transformation
 */
public class ServerTransformation implements Transformation<Server, ddo.argonnessen.argonauts.common.po.Server> {

	@Override
	public ddo.argonnessen.argonauts.common.po.Server execute(Server a) {
		ddo.argonnessen.argonauts.common.po.Server server = new ddo.argonnessen.argonauts.common.po.Server();
		server.setName(a.getName());
		return server;
	}
}
