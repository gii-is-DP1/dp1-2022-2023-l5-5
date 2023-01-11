package org.springframework.samples.petclinic.achievements;

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
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementsServiceTest {
	
	@Autowired
	private AchievementsService achievementsService;
	
	@Test
	void shouldFindAllAchievementsPageable() {
		
		Pageable pageable = PageRequest.of(0, 10);

		List<Achievement> list = this.achievementsService.findAllAchievements(0, pageable);
		int size1 = list.size();

		AchievementType at = new AchievementType();
		at.setId(1);
		at.setName("Won games");
		
		Achievement ac = new Achievement();
		ac.setAchievementType(at);
		ac.setTitle("Win 3 easy games");
		ac.setNumber(3);

		this.achievementsService.saveAchievement(ac);

		List<Achievement> list2 = this.achievementsService.findAllAchievements(0, pageable);
		int size2 = list2.size();

		assertThat(size1 < size2);
	}
	
	@Test
	void shouldFindAllAchievements() {

		List<Achievement> list = this.achievementsService.findAllAchievements();
		int size1 = list.size();

		AchievementType at = new AchievementType();
		at.setId(1);
		at.setName("Won games");
		
		Achievement ac = new Achievement();
		ac.setAchievementType(at);
		ac.setTitle("Win 3 easy games");
		ac.setNumber(3);

		this.achievementsService.saveAchievement(ac);

		List<Achievement> list2 = this.achievementsService.findAllAchievements();
		int size2 = list2.size();

		assertThat(size1 < size2);
	}
	
	@Test
	void shouldCountAllAchievements() {
		
		AchievementType at1 = new AchievementType();
		at1.setId(1);
		at1.setName("Won games");
		
		AchievementType at2 = new AchievementType();
		at2.setId(2);
		at2.setName("Played games");
		
		Achievement ac1 = new Achievement();
		ac1.setAchievementType(at1);
		ac1.setTitle("Win 3 easy games");
		ac1.setNumber(3);
		
		Achievement ac2 = new Achievement();
		ac2.setAchievementType(at2);
		ac2.setTitle("Played 5 medium games");
		ac2.setNumber(5);
		
		this.achievementsService.saveAchievement(ac1);
		this.achievementsService.saveAchievement(ac2);

		int count = this.achievementsService.countAllAchievements();
		
		assertThat(count).isEqualTo(14);
	}
	
	@Test
	void shouldGetAchievementById(){
		Achievement achiev = this.achievementsService.getAchievementById(1).get();
		assertThat(achiev.getTitle()).isEqualTo("Has won 2 games or more?");
	}
	
	@Test
	@Transactional
	void shouldSaveAchievement() {
		
		Pageable pageable = PageRequest.of(0, 10);
	
		List<Achievement> list1 = this.achievementsService.findAllAchievements(0, pageable);
		int size1 = list1.size();
		
		AchievementType at = new AchievementType();
		at.setId(1);
		at.setName("Won games");
		
		Achievement ac = new Achievement();
		ac.setAchievementType(at);
		ac.setTitle("Win 3 easy games");
		ac.setNumber(3);
		
		this.achievementsService.saveAchievement(ac);
		
		List<Achievement> list2 = this.achievementsService.findAllAchievements(0, pageable);
		int size2 = list2.size();
		
		assertThat(size2>size1);
	}
	
	@Test
	void shouldFindAllAchievementTypes() {
		java.util.Collection<AchievementType> list = this.achievementsService.findAllAchievementTypes();
		int size1 = list.size();

		AchievementType at = new AchievementType();
		at.setId(1);
		at.setName("Won games");
		
		Achievement ac = new Achievement();
		ac.setAchievementType(at);
		ac.setTitle("Win 3 easy games");
		ac.setNumber(3);

		this.achievementsService.saveAchievement(ac);

		java.util.Collection<AchievementType> list2 = this.achievementsService.findAllAchievementTypes();
		int size2 = list2.size();

		assertThat(size1 < size2);
	}
	
	@Test
	@Transactional
	void shoulDeleteAchievement() {
		
		AchievementType at = new AchievementType();
		at.setId(1);
		at.setName("Won games");
		
		Achievement ac = new Achievement();
		ac.setAchievementType(at);
		ac.setTitle("Win 3 easy games");
		ac.setNumber(3);
				
		this.achievementsService.saveAchievement(ac);
		
		Integer id = ac.getId();
		
		assertNotNull(achievementsService.getAchievementById(id));
		
		achievementsService.deleteAchievement(id);
		
		assertTrue(achievementsService.getAchievementById(id).isEmpty());
	}
	
}
