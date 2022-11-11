package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
	
	
	private GameService gameService;
	
	private static final String VIEWS_GAME_CREATE_OR_UPDATE_FORM = "games/createOrUpdateGameForm";

	@Autowired 
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping(path = "/new")
	public String initCreationForm(ModelMap model) {
		model.put("game", new Game()); 
		return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(path="/new")
	public String processCreationForm(@Valid Game game, BindingResult result, ModelMap model) {
		String view ="welcome";
		if(result.hasErrors()) {
			//model.put("game", game);
			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		}else {
			gameService.save(game);
			//model.put("message", "Game sucessfully saved!");
			model.addAttribute("message", "Game sucessfully saved!");
		}
		return view;
	}
	
	//No sé si se puede editar una partida ya creada
	@GetMapping(value = "/edit/{id}")
	public String initUpdateGameForm(@PathVariable("id") int id, Model model) {
		Game game = this.gameService.getGameById(id).get();
		model.addAttribute(game);
		return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/edit/{id}")
	public String processUpdateGameForm(@Valid Game game, 
			BindingResult result, @PathVariable("id") int id, ModelMap model) {
		String view ="welcome";
		if (result.hasErrors()) {
			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		}else {
			//game.setId(id)
			gameService.save(game);
			//model.put("message", "Game sucessfully saved!");
			model.addAttribute("message", "Game sucessfully saved!");
		}
		return view;
	}
	
	@GetMapping(value= {"/list"})
	public String processFindForm(Player player, BindingResult result, Map<String, Object> model) {

		List<Game> results = this.gameService.findAllGames();
		
			model.put("selections", results);
			return "games/gamesList";
		
	}

}
