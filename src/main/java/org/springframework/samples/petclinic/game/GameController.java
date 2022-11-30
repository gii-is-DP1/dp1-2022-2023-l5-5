package org.springframework.samples.petclinic.game;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
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
	private BoardService boardService;
	
	private static final String VIEWS_GAME_CREATE_FORM = "games/createGameForm";
	//private static final String VIEWS_DELETE_GAME = "games/gameDelete";

	@Autowired 
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model){
		
		Game game = new Game();
		model.put("game", game); 
		model.put("difficulties", gameService.findAllDifficulties());
		return VIEWS_GAME_CREATE_FORM;
	}
	
	@PostMapping(value="/new")
	public String processCreationForm(Game game, BindingResult result, ModelMap model) {
		//String view =VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		if(result.hasErrors()) {
            
			return VIEWS_GAME_CREATE_FORM;
		}else {
			game.setNumClicks(0);
            game.setInProgress(true);
		    game.setLostGame(false);
			game.setStartTime(LocalDateTime.now());
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
	        String user = currentUser.getUsername();
	        Player player = this.playerService.getPlayerByUsername(user);
	        game.setPlayer(player); 
	        Difficulty diff = game.getDifficulty();
	        String boardId = "";
	        if (diff == Difficulty.EASY) {
	        	Board facil = boardService.findBoardById(1);
	        	game.setBoard(facil);
	        	game.setDifficulty(Difficulty.EASY);
	        	boardId = "1";
	        }else if (diff == Difficulty.MEDIUM) {
	        	Board medio = boardService.findBoardById(2);
	        	game.setBoard(medio);
	        	game.setDifficulty(Difficulty.MEDIUM);
	        	boardId = "2";
	        }else {
	        	Board dificil = boardService.findBoardById(3);
	        	game.setBoard(dificil);
	        	game.setDifficulty(Difficulty.DIFFICULT);
	        	boardId = "3";
	        }

			this.gameService.save(game);

			return "redirect:/boards/prueba/" + boardId;
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
	
	@GetMapping(value= "/statistics")
	public String statistics(Game game, BindingResult result, Map<String, Object> model) {
		
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		Integer nTotal = this.gameService.findnTotalGames();
		Integer gamesPlayerTotal = this.gameService.findnTotalGamesPlayer(currentUser.getUsername());
		model.put("nTotal", nTotal);
		model.put("gamesPlayerTotal", gamesPlayerTotal);
		return "games/statistics";
	}

}
