package org.springframework.samples.petclinic.statistics;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = StatisticsController.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration = SecurityConfiguration.class)
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StatisticsService statisticsService;
	
	@MockBean
	private PlayerService playerService;
	
	@WithMockUser(value = "spring")
	@Test
	public void testStatistics() throws Exception {
		mockMvc.perform(get("/statistic/statistics"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("nTotal"))
				.andExpect(model().attributeExists("gamesPlayerTotal"))
				.andExpect(model().attributeExists("gamesPlayerTotalWon"))
				.andExpect(model().attributeExists("minesActivated"))
				.andExpect(model().attributeExists("gamesPlacedFlags"))
				.andExpect(model().attributeExists("minutesTotalPlayed"))
				.andExpect(model().attributeExists("secondsTotalPlayed"))
				.andExpect(model().attributeExists("minutesTotalPlayer"))
				.andExpect(model().attributeExists("secondsTotalPlayer"))
				.andExpect(model().attributeExists("avgminTimePlayed"))
				.andExpect(model().attributeExists("avgsecTimePlayed"))
				.andExpect(model().attributeExists("avgminTimePlayer"))
				.andExpect(model().attributeExists("avgsecTimePlayer"))
				.andExpect(model().attributeExists("maxminTimePlayed"))
				.andExpect(model().attributeExists("maxsecTimePlayed"))
				.andExpect(model().attributeExists("maxminTimePlayer"))
				.andExpect(model().attributeExists("maxsecTimePlayer"))
				.andExpect(model().attributeExists("minminTimePlayed"))
				.andExpect(model().attributeExists("minsecTimePlayed"))
				.andExpect(model().attributeExists("minminTimePlayer"))
				.andExpect(model().attributeExists("minsecTimePlayer"))
				.andExpect(model().attributeExists("numWonPlayed"))
				.andExpect(model().attributeExists("numLostPlayer"))
				.andExpect(model().attributeExists("numLostPlayed"))
				.andExpect(model().attributeExists("numGamesWinEasyPlayer"))
				.andExpect(model().attributeExists("numGamesWinMediumPlayer"))
				.andExpect(model().attributeExists("numGamesWinDifficultPlayer"))
				.andExpect(model().attributeExists("numGamesWinEasyPlayed"))
				.andExpect(model().attributeExists("numGamesWinMediumPlayed"))
				.andExpect(model().attributeExists("numGamesWinDifficultPlayed"))
				.andExpect(view().name("statistics/statis"));
				
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testShowRanking() throws Exception {
		mockMvc.perform(get("/statistic/rankings"))
				.andExpect(status().isOk())
				.andExpect(view().name("statistics/ranking"));
	}
	

	
}
