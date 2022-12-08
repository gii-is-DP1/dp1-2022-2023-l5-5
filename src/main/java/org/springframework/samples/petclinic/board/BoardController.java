package org.springframework.samples.petclinic.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final String VIEWS_BOARD = "boards/board";

	@Autowired
	private BoardService boardService;

	@Autowired
	private PlayerService playerService;
	
	@GetMapping(path = "/new/**")
	public String board(ModelMap modelMap) {
		return VIEWS_BOARD;
	}
	
//	@GetMapping(value= "/list")
//	public String processFindForm(Game game, BindingResult result, Map<String, Object> model) {
//
//		List<Board> results = this.boardService.findAllGamesNotInProgress();
//		
//			model.put("selections", results);
//			return "games/gamesList";
//		
//	}

//	@GetMapping(value= "/listinprogress")
//	public String processFindFormProgress(Game game, BindingResult result, Map<String, Object> model) {
//
//		List<Game> results = this.gameService.findAllGamesInProgress();
//		
//			model.put("selections", results);
//			return "games/gamesListInProgress";
//		
//	}

//	@GetMapping(value= "/listplayer")
//	public String processFindFormPlayer(Game game, BindingResult result, Map<String, Object> model) {
//
//		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//		User currentUser=(User) authentication.getPrincipal();
//		
//	    List<Game> results = this.gameService.findAllGamesPlayer(currentUser.getUsername());
//		model.put("selections", results);
//		return "games/gamesListPlayer";
//		
//	}
	
	@GetMapping(value = "/listinprogress")
	public String processFindFormProgress(ModelMap modelMap) {
		String vista = "boards/gamesListInProgress";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null)
			if (authentication.isAuthenticated()) {
				User currentUser = (User) authentication.getPrincipal();
				List<Board> board = boardService.findBoardByUsername(currentUser.getUsername(), GameStatus.IN_PROGRESS);
				modelMap.addAttribute("board", board);
				return vista;
			} else {
				System.out.println("User not authenticated");
		}
		return "welcome";
		
	}
    
	@GetMapping(path="/list")
	public String processFindForm(ModelMap modelMap) {
		List<Board> board = boardService.findAllGamesNotInProgress(GameStatus.NONE);
		modelMap.addAttribute("board", board);
		return "boards/gamesList";
	}
	
	@GetMapping(value= "/listplayer")
	public String processFindFormPlayer(Board board, BindingResult result, Map<String, Object> model) {

		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		
	    List<Board> results = this.boardService.findAllGamesPlayer(currentUser.getUsername());
		model.put("selections", results);
		return "boards/gamesListPlayer";
		
	}

	
	

}
