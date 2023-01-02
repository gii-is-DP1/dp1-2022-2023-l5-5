package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication()
public class MinesweeperApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MinesweeperApplication.class, args);
	}

}
