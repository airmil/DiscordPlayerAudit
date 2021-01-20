package ddo.argonnessen.argonauts.runtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ddo.argonnessen.argonauts.discord.Bot;
import ddo.argonnessen.argonauts.poc.PocSpringService;

/**
 * main bot class
 */
@SpringBootApplication
@EntityScan(basePackages = "ddo.argonnessen.argonauts")
@EnableJpaRepositories(basePackages = { "ddo.argonnessen.argonauts" })
@ComponentScan(basePackages = { "ddo.argonnessen.argonauts" })
public class Main implements CommandLineRunner {

	/**
	 * main service
	 */
	@Autowired
	Bot b;

	/**
	 * 
	 */
	@Autowired
	PocSpringService service;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		b.run(args);
	}
}
