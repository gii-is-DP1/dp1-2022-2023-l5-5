package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private static final String VIEWS_DELETE_GAME = "games/gameDelete";

	@Autowired 
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		model.put("game", new Game()); 
		return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value="/new")
	public String processCreationForm(@Valid Game game, BindingResult result, ModelMap model) {
		//String view =VIEWS_GAME_CREATE_OR_UPDATE_FORM;;
		if(result.hasErrors()) {
			//model.put("game", game);
			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		}else {
			this.gameService.save(game);
			//model.put("message", "Game sucessfully saved!");
			//model.addAttribute("message", "Game sucessfully saved!");
			return "redirect:/";
		}
		//return view;
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
		//String view ="welcome";
		if (result.hasErrors()) {
			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		}else {
			gameService.save(game);
			//model.put("message", "Game sucessfully saved!");
			//model.addAttribute("message", "Game sucessfully saved!");
			return "redirect:/";
		}
//		}else {
//			//game.setId(id)
//			gameService.save(game);
//			//model.put("message", "Game sucessfully saved!");
//			model.addAttribute("message", "Game sucessfully saved!");
//		}
//		return view;
	}
	
	@GetMapping(value= "/list")
	public String processFindForm(Game game, BindingResult result, Map<String, Object> model) {

		List<Game> results = this.gameService.findAllGamesNotInProgress();
		
			model.put("selections", results);
			return "games/gamesList";
		
	}

	@GetMapping(value= "/listinprogress")
	public String processFindFormProgress(Game game, BindingResult result, Map<String, Object> model) {

		List<Game> results = this.gameService.findAllGamesInProgress();
		
			model.put("selections", results);
			return "games/gamesListInProgress";
		
	}

	@GetMapping(value= "/listplayer")
	public String processFindFormPlayer(Game game, BindingResult result, Map<String, Object> model,@AuthenticationPrincipal User user) {

		// Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		// User currentUser=(User) authentication.getPrincipal();
		List<Game> results = this.gameService.findAllGamesPlayer(user.getUsername());
		System.out.println(user.getUsername());
		System.out.println("========================================================================================");
			model.put("selections", results);
			return "games/gamesListPlayer";
		
	}
	
	@GetMapping(value = "/{id}/delete")
	public String deleteGame(@PathVariable("id") int id) {
		gameService.deleteGame(id);
		
		return VIEWS_DELETE_GAME;
	}

}
