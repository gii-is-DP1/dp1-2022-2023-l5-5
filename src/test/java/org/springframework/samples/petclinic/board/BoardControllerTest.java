package org.springframework.samples.petclinic.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

@WebMvcTest(controllers = BoardController.class,
			excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration = SecurityConfiguration.class)
public class BoardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BoardService boardService;
	
	@MockBean
	private PlayerService playerService;
	
	
	@WithMockUser(value = "spring")
	@Test
	public void testBoard() throws Exception {
		mockMvc.perform(get("/board/game?dificulty=1"))
				.andExpect(status().isOk())
				.andExpect(view().name("boards/board"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testProcessFindFormProgress() throws Exception {
		mockMvc.perform(get("/board/listinprogress"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("board"))
				.andExpect(view().name("boards/gamesListInProgress"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testProcessFindForm() throws Exception {
		mockMvc.perform(get("/board/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("selections"))
				.andExpect(view().name("boards/gamesList"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testProcessFindFormPlayer() throws Exception {
		mockMvc.perform(get("/board/listplayer"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("board"))
				.andExpect(view().name("boards/gamesListPlayer"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testSetDifficulty() throws Exception {
		mockMvc.perform(get("/board/setDifficulty"))
				.andExpect(status().isOk())
				.andExpect(view().name("boards/setDifficulty"));
	}
	
	
	
	

	
}
