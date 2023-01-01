package org.springframework.samples.petclinic.player;


import java.util.List;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;


@Controller
@RequestMapping("/players")
public class PlayerController {
    
	private static final String VIEWS_INICIO = "welcome";
    private static final String VIEWS_PLAYER_CREATE_FORM = "players/createPlayerForm";
    private static final String VIEWS_PLAYER_UPDATE_FORM = "players/updatePlayerForm";
    private static final String VIEWS_PLAYERS_LIST = "players/playersList";
    private static final String VIEWS_PLAYERS_PROFILE = "players/playersProfile";
    private static final String VIEWS_PLAYERS_DELETE = "players/playersDelete";
    private static final String VIEWS_PLAYERS_DELETE_ADMIN = "players/playersDeleteAdmin";
    
	private final PlayerService playerService;

    @Autowired
	public PlayerController(PlayerService playerService, UserService userService, AuthoritiesService authoritiesService) {
		this.playerService = playerService;
	}

  //Un usuario cualquiera puede crear un nuevo jugador
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Player player = new Player(); //Objeto juagador
		model.put("player", player); //Meter en la vista el objeto jugador, y en la vista busco por el nombre entre ""
		return VIEWS_PLAYER_CREATE_FORM;
	}
	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Player player, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_FORM;
		}
		else if (playerService.validator(player) == true) {
			model.addAttribute("message", "This username is already chosen!");
			return VIEWS_PLAYER_CREATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.playerService.savePlayer(player);
			
			return "redirect:/";
		}
	}
	
	//Un jugador edita su propio jugador
	@GetMapping(value = "/myprofile/{id}/edit")
	public String initUpdatePlayerForm(@PathVariable("id") int id, Model model) {
		Player player = this.playerService.getPlayerById(id).get();
		model.addAttribute(player);
		return VIEWS_PLAYER_UPDATE_FORM;
	}
	
	@PostMapping(value = "/myprofile/{id}/edit")
	public String processUpdatePlayerForm(@Valid Player player, 
			BindingResult result, @PathVariable("id") int id, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_UPDATE_FORM;
		} 
//		else if (playerService.validator(player) == true){
//			model.addAttribute("message", "This username is already chosen!");
//			return VIEWS_PLAYER_UPDATE_FORM;
//		} 
		else {
			player.setId(id);
			this.playerService.savePlayer(player);
			return "redirect:/";
		}
	}
	
	//Un jugador elimina su propio jugador
		@GetMapping(value = "/myprofile/{id}/delete")
		public String redirectDelete(@PathVariable("id") Integer id, ModelMap model) {
			Player player = this.playerService.getPlayerById(id).get();
			model.addAttribute(player);
			return VIEWS_PLAYERS_DELETE;
		}
		
	//El admin elimina un jugador
		@GetMapping(value = "/myprofile/{id}/deleteAdmin")
		public String redirectDeleteAdmin(@PathVariable("id") Integer id, ModelMap model) {
			Player player = this.playerService.getPlayerById(id).get();
			model.addAttribute(player);
			return VIEWS_PLAYERS_DELETE_ADMIN;
		}
	
	
	//Confirmación de eliminar para un player
	@GetMapping(value = "/myprofile/{id}/deleteConfirm")
	public String deletePlayer(@PathVariable("id") Integer id, ModelMap model) {
		this.playerService.deletePlayer(id);
		
		return "redirect:/logout";
	}
	
	//Confirmación de eliminar para un admin
		@GetMapping(value = "/myprofile/{id}/deleteConfirmAdmin")
		public String deletePlayerAdmin(@PathVariable("id") Integer id, ModelMap model) {
			this.playerService.deletePlayer(id);
			
			return "redirect:/";
		}
	

	//El admin ve el listado de jugadores
	@GetMapping(value = "/list")
	public String processFindForm(Player player, BindingResult result, Map<String, Object> model, 
		@PageableDefault(page = 0, size = 6) @SortDefault.SortDefaults({
			@SortDefault(sort = "id", direction = Sort.Direction.ASC),
			@SortDefault(sort = "mail", direction = Sort.Direction.DESC), }) Pageable pageable) {


		Integer numResults = this.playerService.countAllPlayers();
		Integer page = 0;
		List<Player> results = this.playerService.findAllPlayers(page, pageable);
			// multiple players found
			model.put("pageNumber", pageable.getPageNumber());
			model.put("hasPrevious", pageable.hasPrevious());
			Double totalPages = Math.ceil(numResults / (pageable.getPageSize()));
			model.put("totalPages", totalPages);
			model.put("selections", results);


		
			return VIEWS_PLAYERS_LIST;
		
	}
	
	//El admin ve el perfil de un jugador
	@GetMapping(value = "/list/{username}")
	public String showPlayerProfile(Player player, @PathVariable("username") String username, BindingResult result, Model model) {
		
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//User currentUser = (User) authentication.getPrincipal();
		
		Player results = this.playerService.getPlayerByUsername(username);
		model.addAttribute(results);
		return VIEWS_PLAYERS_PROFILE;
	}
	
	//Un jugador ve su propio perfil
	@GetMapping(value = "/myprofile")
	public String showPlayerProfile(Player player, BindingResult result, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		
		Player results = this.playerService.getPlayerByUsername(currentUser.getUsername());
		model.addAttribute(results);
		return VIEWS_PLAYERS_PROFILE;
	}

	



}
