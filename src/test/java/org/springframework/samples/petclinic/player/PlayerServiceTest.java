package org.springframework.samples.petclinic.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlayerServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlayerService playerService;
	
	@Test
	void shouldFindAllPlayers() {
		List<Player> list = this.playerService.findAllPlayers();
		int size1 = list.size();
		
		Player player = new Player();
		player.setFirstName("Pablo");
		player.setLastName("Martin");
		player.setMail("pabquide@alum.us.es");
		
		User user = new User();
		user.setUsername("pabquide");
		user.setPassword("p4bqu1de");
		user.setEnabled(true);
		player.setUser(user);
		
		this.userService.saveUser(user);
		
		List<Player> list2 = this.playerService.findAllPlayers();
		int size2 = list2.size();
		
		assertThat(size1<size2);
	}
	

	@Test
	void shouldGetPlayerByUsername() {
		Player player = this.playerService.getPlayerByUsername("meriglmar");
		String lastname = player.getLastName();
		
		assertThat(lastname.equals("Iglesias"));
	}
	
	
	
	
}
