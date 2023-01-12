package org.springframework.samples.petclinic.achievements;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
	
	private static final int TEST_ACHIEV_ID = 1;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AchievementsService achievementService;
	
	@MockBean
	private StatisticsService statisticsService;
	
	@BeforeEach
    public void setUp() {
        
		AchievementType at = new AchievementType();
		at.setId(1);
		at.setName("Won games");
		
		Achievement achiev = new Achievement();
        achiev.setTitle("Has won 2 games or more?");
        achiev.setNumber(2);
        achiev.setId(1);
        achiev.setAchievementType(at);
                
        Optional<Achievement> op = Optional.ofNullable(achiev);
        Mockito.when(achievementService.getAchievementById(1)).thenReturn(op);
  
	}
	
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
	
	
	@WithMockUser(value = "spring")
	@Test
	public void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/achievements/new"))
		.andExpect(status().isOk()) 
		.andExpect(model().attributeExists("achievement"))
		.andExpect(view().name("achievements/createAchievementsForm"));
	}
		

	@WithMockUser(value = "spring")
	@Test
	public void testProcessCreationForm() throws Exception {	
		mockMvc.perform(post("/achievements/new") 
					.with(csrf())
					.param("title", "Won difficult games")
					.param("number", "5")
					.param("achievementType", "1"))
				.andExpect(status().isOk())
			    .andExpect(view().name("achievements/createAchievementsForm"));
	}
	
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testInitUpdateAchievementForm() throws Exception {
		mockMvc.perform(get("/achievements/{id}/edit", TEST_ACHIEV_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("achievements/updateAchievementsForm"));
	}
	
	
    @WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testProcessUpdateAchievementForm() throws Exception {
		mockMvc.perform(post("/achievements/{id}/edit", TEST_ACHIEV_ID)
							.with(csrf())
							.param("title", "Won difficult games")
							.param("number", "5")
							.param("achievementType", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("achievements/updateAchievementsForm"));
	}
    
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testRedirectDelete() throws Exception {
		mockMvc.perform(get("/achievements/{id}/delete", TEST_ACHIEV_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("achievements/achievementsDelete"));
	}
	
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testDeleteAchievementAdmin() throws Exception {
		mockMvc.perform(get("/achievements/{id}/deleteConfirm", TEST_ACHIEV_ID))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/achievements/list"));
	}
	
	

}
