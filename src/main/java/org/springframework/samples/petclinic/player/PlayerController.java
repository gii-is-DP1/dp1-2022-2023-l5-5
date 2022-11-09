package org.springframework.samples.petclinic.player;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/players")
public class PlayerController {
    
    private static final String VIEWS_PLAYER_CREATE_OR_UPDATE_FORM = "players/createOrUpdatePlayerForm";
    
	private final PlayerService playerService;

    @Autowired
	public PlayerController(PlayerService playerService, UserService userService, AuthoritiesService authoritiesService) {
		this.playerService = playerService;
	}

	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Player player = new Player(); //Objeto juagador
		model.put("player", player); //Meter en la vista el objeto jugador, y en la vista busco por el nombre entre ""
		return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Player player, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		}
		else if (playerService.validator(player) == true) {
			model.addAttribute("message", "This username is already chosen!");
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.playerService.savePlayer(player);
			
			return "redirect:/";
		}
	}
	
	@GetMapping(value = "/edit/{id}")
	public String initUpdatePlayerForm(@PathVariable("id") int id, Model model) {
		Player player = this.playerService.getPlayerById(id).get();
		model.addAttribute(player);
		return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/edit/{id}")
	public String processUpdatePlayerForm(@Valid Player player, 
			BindingResult result, @PathVariable("id") int id, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		} 
		else if (playerService.validator(player) == true){
			model.addAttribute("message", "This username is already chosen!");
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		} 
		else {
			//player.setId(id);
			this.playerService.savePlayer(player);
			return "redirect:/";
		}
	}
	

}
