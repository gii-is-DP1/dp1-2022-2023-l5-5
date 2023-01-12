package org.springframework.samples.petclinic.achievements;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AchievementsController.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration = SecurityConfiguration.class)
public class AchievementsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AchievementsService achievementService;
	
	@MockBean
	private StatisticsService statisticsService;
	
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testProcessFindForm() throws Exception {
		mockMvc.perform(get("/achievements/list")) 
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pageNumber"))
			.andExpect(model().attributeExists("hasPrevious"))
			.andExpect(model().attributeExists("totalPages"))
			.andExpect(model().attributeExists("selections"))
		    .andExpect(view().name("achievements/achievementsList"));
	}
	
//	@WithMockUser(value = "spring", authorities={"player"})
//	@Test
//	public void testShowPlayerAchievements() throws Exception {
//		mockMvc.perform(get("/achievements/myprofile")) 
//			.andExpect(status().isOk())
////			.andExpect(model().attributeExists("selections"))
////			.andExpect(model().attributeExists("selections2"))
////			.andExpect(model().attributeExists("player"))
//		    .andExpect(view().name("achievements/playerAchievements"));
//	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/achievements/new"))
		.andExpect(status().isOk()) 
		.andExpect(model().attributeExists("achievement"))
		.andExpect(view().name("achievements/createAchievementsForm"));
	}
	
//	//Arreglarlo
//	@WithMockUser(value = "spring")
//	@Test
//	public void testProcessCreationFormSuccess() throws Exception {	
//		mockMvc.perform(post("/achievements/new") 
//					.with(csrf())
//						.param("title", "Won difficult games")
//						.param("number", "5")
//						.param("achievementType", "1,'Won games'"))
//				.andExpect(status().is2xxSuccessful())
//			    .andExpect(view().name("achievements/list"));
//	}
	

	@WithMockUser(value = "spring")
	@Test
	public void testProcessCreationFormHasErrors() throws Exception {	
		mockMvc.perform(post("/achievements/new") 
					.with(csrf())
					.param("title", "Won difficult games")
					.param("number", "5")
					.param("achievementType", ""))
				.andExpect(status().isOk())
			    .andExpect(view().name("achievements/createAchievementsForm"));
	}
	
	

}
