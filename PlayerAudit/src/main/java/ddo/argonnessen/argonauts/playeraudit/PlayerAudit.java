package ddo.argonnessen.argonauts.playeraudit;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import ddo.argonnessen.argonauts.playeraudit.exception.PlayerAuditException;
import ddo.argonnessen.argonauts.playeraudit.po.Player;
import ddo.argonnessen.argonauts.playeraudit.po.Server;

/**
 * bean to communicate with PlayerAudit
 */
public class PlayerAudit {

	/**
	 * API URL
	 */
	private static final String URL = "https://www.playeraudit.com/api/players?s="; //$NON-NLS-1$

	/**
	 * 
	 * @param serverName
	 * @return players
	 * 
	 * @throws PlayerAuditException
	 */
	public Set<Player> getServer(String serverName) throws PlayerAuditException {
		Set<Player> players = new HashSet<Player>();
		URL u;
		try {
			u = new URL(URL + serverName);
		} catch (MalformedURLException e) {
			throw new PlayerAuditException(e);
		}
		InputStream stream;
		try {
			stream = u.openStream();
		} catch (IOException e) {
			throw new PlayerAuditException(e);
		}
		InputStreamReader isr = new InputStreamReader(stream);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		JsonReader reader = new JsonReader(isr);
		Server s = gson.fromJson(reader, Server.class);
		if (s == null) {
			return players;
		}
		return s.getPlayers();
	}

}
