package org.springframework.samples.petclinic.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)

public class BoardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	public BoardService boardService;
	
	@MockBean
	public PlayerService playerService;
	
	
	@WithMockUser(value = "spring")
	@Test
	public void testBoard() throws Exception {
		mockMvc.perform(get("/board/game?difficulty=2"))
				.andExpect(status().isOk())
				.andExpect(view().name("boards/board"));
	}
	
	

	
}
