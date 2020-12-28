package ddo.argonnessen.argonauts.poc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import ddo.argonnessen.argonauts.common.po.Server;

/**
 * 
 */
@SpringBootApplication
@EntityScan(basePackages = "ddo.argonnessen.argonauts")
public class PocSpring implements CommandLineRunner {
	
	/**
	 * 
	 */
	@Autowired
	PocSpringService service;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PocSpring.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Server> list = service.list();
		System.out.println(list);
	}
}
