package org.springframework.samples.petclinic.game;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
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
		String view =VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		if(result.hasErrors()) {
			return view;
		}else {
			gameService.save(game);
			model.addAttribute("message", "Game sucessfully saved!");
		}
		return view;
	}

}
