package org.springframework.samples.petclinic.player;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@DataJpaTest(includeFilters = @ComponentScan.Filter(classes= {Configuration.class}))
public class PlayerTest {

	private PlayerService playerService;
	
	@Test
	public void testExcepciones() {
		Player player = new Player();
        player.setId(14);
        player.setFirstName("John");
        assertThrows(NullPointerException.class,() -> playerService.savePlayer(player), "Tienes que rellenar los campos obligatorios");
        
        
	}
}
