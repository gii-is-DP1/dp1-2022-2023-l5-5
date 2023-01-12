package org.springframework.samples.petclinic.player;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = PlayerController.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration = SecurityConfiguration.class)
public class PlayerControllerTest {

	private static final int TEST_PLAYER_ID = 1;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlayerService playerService;
	
	@BeforeEach
    public void setUp() {
        Player player = new Player();
        player.setFirstName("Mercedes");
        player.setId(2);
        User user = new User();
        user.setUsername("meriglmar");
        user.setEnabled(true);
        player.setUser(user);
        Optional<Player> op = Optional.ofNullable(player);
        Mockito.when(playerService.getPlayerById(1)).thenReturn(op);
        Mockito.when(playerService.getPlayerByUsername(any(String.class))).thenReturn(player);
  
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/players/new"))
		.andExpect(status().isOk()) 
		.andExpect(model().attributeExists("player"))
		.andExpect(view().name("players/createPlayerForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testProcessCreationFormSuccess() throws Exception {	
		mockMvc.perform(post("/players/new") 
					.with(csrf())
						.param("firstName", "John")
						.param("lastName", "Doe")
						.param("mail", "john@gmail.com")
						.param("user.username", "john")
						.param("user.password", "password123"))
				.andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/"));
	}
	

	@WithMockUser(value = "spring")
	@Test
	public void testProcessCreationFormHasErrors() throws Exception {	
		mockMvc.perform(post("/players/new") 
					.with(csrf())
						.param("firstName", "John")
						.param("lastName", "Doe")
						.param("mail", "john")
						.param("user.username", "john")
						.param("user.password", "password123"))
				.andExpect(status().isOk())
			   .andExpect(view().name("players/createPlayerForm"));
	}
	
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testInitUpdateFormSuccess() throws Exception {
		mockMvc.perform(get("/players/myprofile/{id}/edit", TEST_PLAYER_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("players/updatePlayerForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testInitUpdateFormHasErrors() throws Exception {
		mockMvc.perform(get("/players/myprofile/{id}/edit", TEST_PLAYER_ID))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring", authorities={"admin", "player"})
	@Test
	public void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/players/myprofile/{id}/edit", TEST_PLAYER_ID)
							.with(csrf())
							.param("firstName", "John")
							.param("lastName", "Doe")
							.param("mail", "john@gmail.com")
							.param("user.username", "john")
							.param("user.password", "password123"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}
	
    @WithMockUser(value = "spring", authorities={"admin", "player"})
	@Test
	public void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/players/myprofile/{id}/edit", TEST_PLAYER_ID)
							.with(csrf())
							.param("firstName", "John")
							.param("lastName", "Doe")
							.param("mail", "john")
							.param("user.username", "john")
							.param("user.password", "password123"))
				.andExpect(status().isOk())
				.andExpect(view().name("players/updatePlayerForm"));
	}
    
	
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testDeletePlayer() throws Exception {
		mockMvc.perform(get("/players/myprofile/{id}/deleteAdmin", TEST_PLAYER_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("players/playersDeleteAdmin"));
	}
	
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testDeletePlayerSuccess() throws Exception {
		mockMvc.perform(get("/players/myprofile/{id}/deleteConfirmAdmin", TEST_PLAYER_ID))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/players/list?firstName=&page=0"));
	}
	
	@Test
	@WithMockUser(value = "spring", authorities={"admin"})
	public void testProcessFindForm() throws Exception {
		mockMvc.perform(get("/players/list?firstName=&page=0"))
			   .andExpect(status().isOk())
				.andExpect(model().attributeExists("pageNumber"))
				.andExpect(model().attributeExists("hasPrevious"))
				.andExpect(model().attributeExists("totalPages"))
				.andExpect(model().attributeExists("selections"))
				.andExpect(view().name("players/playersList"));
	}
	
	@WithMockUser(value = "spring", authorities={"admin"})
	@Test
	public void testShowPlayerProfileAdmin() throws Exception {
		mockMvc.perform(get("/players/list/{username}", "meriglmar"))
				.andExpect(status().isOk())
				.andExpect(view().name("players/playersProfile"));
	}
	
	@WithMockUser(value = "spring", authorities={"player"})
	@Test
	public void testShowPlayerProfile() throws Exception {
		mockMvc.perform(get("/players/myprofile"))
				.andExpect(status().isOk())
				.andExpect(view().name("players/playersProfile"));
	}
	
}
