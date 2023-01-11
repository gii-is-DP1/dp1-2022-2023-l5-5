package org.springframework.samples.petclinic.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlayerServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	void shouldFindAllPlayers() {
		Pageable pageable = PageRequest.of(0, 10);
		
		List<Player> list = this.playerService.findAllPlayers(0, pageable);
	 	int size1 = list.size();
	 	
	 	Player player = new Player();
	 	player.setFirstName("John");
	 	player.setLastName("Doe");
	 	player.setMail("john@alum.us.es");
		
	 	User user = new User();
	 	user.setUsername("johndoe1");
	 	user.setPassword("password123");
	 	user.setEnabled(true);
	 	player.setUser(user);
		
		this.playerService.savePlayer(player);
	
		List<Player> list2 = this.playerService.findAllPlayers(0, pageable);
		int size2 = list2.size();
		
	 	assertThat(size1<size2);
	}
	

	@Test
	void shouldCountAllPlayers() {
		Player player1 = new Player();
		player1.setFirstName("John");
		player1.setLastName("Doe");
		player1.setMail("john@alum.us.es");
		
		Player player2 = new Player();
		player2.setFirstName("Jane");
		player2.setLastName("Doe");
		player2.setMail("jane@alum.us.es");
		
		User user1 = new User();
		user1.setUsername("johndoe1");
		user1.setPassword("password123");
		user1.setEnabled(true);
		player1.setUser(user1);
		
		User user2 = new User();
		user2.setUsername("janedoe1");
		user2.setPassword("password123");
		user2.setEnabled(true);
		player2.setUser(user2);
		
		this.playerService.savePlayer(player1);
		this.playerService.savePlayer(player2);
		
		int count = this.playerService.countAllPlayers();

		assertThat(count).isEqualTo(15); 
	}

	
	@Test
	@Transactional
	void shouldSavePlayer() {
		
		Pageable pageable = PageRequest.of(0, 10);
	
		List<Player> list1 = this.playerService.findAllPlayers(0, pageable);
		int size1 = list1.size();
		
		Player player = new Player();
		player.setFirstName("John");
		player.setLastName("Doe");
		player.setMail("john@alum.us.es");
		
		User user = new User();
		user.setUsername("johndoe1");
		user.setPassword("password123");
		user.setEnabled(true);
		player.setUser(user);
		
		this.playerService.savePlayer(player);
		
		List<Player> list2 = this.playerService.findAllPlayers(0, pageable);
		int size2 = list2.size();
		
		assertThat(size2>size1);
	}
	

	
	@Test
	@Transactional
	void testValidator() {
		Player player = new Player();
		player.setFirstName("John");
		player.setLastName("Doe");
		player.setMail("john@alum.us.es");
		
		User user = new User();
		user.setUsername("johndoe1");
		user.setPassword("password123");
		user.setEnabled(true);
		player.setUser(user);
		
		player = playerRepository.save(player);
				
		assertTrue(playerService.validator(player));

	 }

	@Test
	void shouldFindPlayerById(){
		Player players = this.playerService.getPlayerById(1).get();
		assertThat(players.getFirstName()).isEqualTo("Pablo");
	}
	
	@Test
	void shouldGetPlayerByUsername() {
		Player player = this.playerService.getPlayerByUsername("meriglmar");
		String lastname = player.getLastName();
		
		assertThat(lastname.equals("Iglesias"));
	}
	
	@Test
	@Transactional
	void shouldDeletePlayer() {
		
		Player player = new Player();
		player.setId(2);
		player.setFirstName("Mercedes");
		player.setLastName("Iglesias");
		player.setMail("meriglmar@alum.us.es");
		
		User user = new User();
		user.setUsername("meriglmar");
		user.setPassword("mer1glm4r");
		user.setEnabled(true);
		player.setUser(user);
				
		this.playerService.savePlayer(player);
		
		Integer id = player.getId();
		
		assertNotNull(playerService.getPlayerById(id));
		
		playerService.deletePlayer(id);
		
		assertTrue(playerService.getPlayerById(id).isEmpty());
	}




	
	
}
