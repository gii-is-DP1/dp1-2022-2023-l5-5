package org.springframework.samples.petclinic.game;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
	
	@Autowired
	private GameService gameService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private TableroService boardService;
	
	private static final String VIEWS_GAME_CREATE_FORM = "games/createGameForm";
	//private static final String VIEWS_DELETE_GAME = "games/gameDelete";

	@Autowired 
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model){
		
		Game game = new Game();
        game.setStartTime(LocalDateTime.now());
        game.setNumClicks(0);
        game.setInProgress(true);
        game.setLostGame(false);
		model.put("game", game); 
		model.put("difficulties", gameService.findAllDifficulties());
		return VIEWS_GAME_CREATE_FORM;
	}
	
	@PostMapping(value="/new")
	public String processCreationForm(@Valid Game game, BindingResult result, ModelMap model) {
		//String view =VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		if(result.hasErrors()) {
            
			return VIEWS_GAME_CREATE_FORM;
		}else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
	        String user = currentUser.getUsername();
	        Player player = this.playerService.getPlayerByUsername(user);
	        game.setPlayer(player);
	        Difficulty diff = game.getDifficulty();
	        String boardId = "";
	        if (diff == Difficulty.EASY) {
	        	Tablero facil = boardService.findTableroById(1);
	        	game.setTablero(facil);
	        	game.setDifficulty(Difficulty.EASY);
	        	boardId = "1";
	        }else if (diff == Difficulty.MEDIUM) {
	        	Tablero medio = boardService.findTableroById(2);
	        	game.setTablero(medio);
	        	game.setDifficulty(Difficulty.MEDIUM);
	        	boardId = "2";
	        }else {
	        	Tablero dificil = boardService.findTableroById(3);
	        	game.setTablero(dificil);
	        	game.setDifficulty(Difficulty.DIFFICULT);
	        	boardId = "3";
	        }

			this.gameService.save(game);

			return "redirect:/tableros/prueba/" + boardId;
		}
		
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
	public String processFindFormPlayer(Game game, BindingResult result, Map<String, Object> model) {

		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		
	    List<Game> results = this.gameService.findAllGamesPlayer(currentUser.getUsername());
		model.put("selections", results);
		return "games/gamesListPlayer";
		
	}
	

}
