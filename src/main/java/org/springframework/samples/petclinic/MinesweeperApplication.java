package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
public class MinesweeperApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MinesweeperApplication.class, args);
	}

}
