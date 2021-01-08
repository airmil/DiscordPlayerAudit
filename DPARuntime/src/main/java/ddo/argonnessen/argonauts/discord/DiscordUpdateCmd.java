/**
 * 
 */
package ddo.argonnessen.argonauts.discord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ddo.argonnessen.argonauts.playeraudit.service.PlayerAuditCallService;

/**
 * 
 */
/**
 * 
 */
@SpringBootApplication
@EntityScan(basePackages = "ddo.argonnessen.argonauts")
@EnableJpaRepositories(basePackages = { "ddo.argonnessen.argonauts" })
@ComponentScan(basePackages = { "ddo.argonnessen.argonauts" })
public class DiscordUpdateCmd implements CommandLineRunner {

	/**
	 * service
	 */
	@Autowired
	PlayerAuditCallService service;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiscordUpdateCmd.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		service.execute();
	}
}
