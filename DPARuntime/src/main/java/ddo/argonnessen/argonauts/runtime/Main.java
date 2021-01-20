package ddo.argonnessen.argonauts.runtime;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * main bot class
 */
@SpringBootApplication
@EntityScan(basePackages = "ddo.argonnessen.argonauts")
@EnableJpaRepositories(basePackages = { "ddo.argonnessen.argonauts" })
@ComponentScan(basePackages = { "ddo.argonnessen.argonauts" })
@EnableAsync
public class Main implements CommandLineRunner {

	/**
	 * main service
	 */
	@Autowired
	MainService main;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		main.setToken(args[0]);
		main.execute(taskExecutor());
	}

	/**
	 * @return executor
	 */
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("PlayerAuditLookup-"); //$NON-NLS-1$
		executor.initialize();
		return executor;
	}
}
