package ddo.argonnessen.argonauts.runtime;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ddo.argonnessen.argonauts.discord.Bot;
import ddo.argonnessen.argonauts.playeraudit.exception.PlayerAuditException;
import ddo.argonnessen.argonauts.playeraudit.service.PlayerAuditCallService;

/**
 * 
 */
@Service
public class MainService {

	/**
	 * discord bot service
	 */
	@Autowired
	Bot b;
	/**
	 * 
	 */
	@Autowired
	PlayerAuditCallService playerAudit;
	/**
	 * token
	 */
	String token;

	/**
	 * 
	 */
	@Async
	void runPlayerAuditService() {
		while (true) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				continue;
			}
			updatePlayerAudit();
		}
	}


	/**
	 * 
	 */
	private void updatePlayerAudit() {
		try {
			playerAudit.execute();
		} catch (PlayerAuditException e) {
			e.printStackTrace();
		}
	}


	/**
	 * execute
	 * 
	 * @param executor
	 */
	public void execute(Executor executor) {
		updatePlayerAudit();
		Runnable command = new Runnable() {

			@Override
			public void run() {
				runPlayerAuditService();
			}
		};
		executor.execute(command);
		b.setToken(token);
		b.execute();
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
