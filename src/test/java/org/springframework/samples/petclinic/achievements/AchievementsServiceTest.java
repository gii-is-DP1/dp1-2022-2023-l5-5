package org.springframework.samples.petclinic.achievements;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementsServiceTest {
	
	@Autowired
	private AchievementsService achievementsService;
	
//	@Test
//	void shouldFindAllAchievementsPageable() {
//		
//		Pageable pageable = PageRequest.of(0, 10);
//
//		List<Player> list = this.playerService.findAllPlayers(0, pageable);
//		int size1 = list.size();
//
//		Player player = new Player();
//		player.setFirstName("John");
//		player.setLastName("Doe");
//		player.setMail("john@alum.us.es");
//
//		User user = new User();
//		user.setUsername("johndoe1");
//		user.setPassword("password123");
//		user.setEnabled(true);
//		player.setUser(user);
//
//		this.playerService.savePlayer(player);
//
//		List<Player> list2 = this.playerService.findAllPlayers(0, pageable);
//		int size2 = list2.size();
//
//		assertThat(size1 < size2);
//	}
}
