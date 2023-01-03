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
	private static final String VIEWS_NEW_BOARD = "boards/setDifficulty";

	@Autowired
	private BoardService boardService;

	@Autowired
	private PlayerService playerService;
	
	@GetMapping(path = "/game/**")
	public String board(ModelMap modelMap) {
		return VIEWS_BOARD;
	}
	
	@GetMapping(value = "/listinprogress")
	public String processFindFormProgress(ModelMap modelMap) {
		String vista = "boards/gamesListInProgress";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null)
			if (authentication.isAuthenticated()) {
				List<Board> board = boardService.findAllGamesInProgress(GameStatus.IN_PROGRESS);
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
		model.put("board", results);
		return "boards/gamesListPlayer";
		
	}
	
	@GetMapping(value= "/statistics")
	public String statistics(Board board, BindingResult result, Map<String, Object> model) {
		
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		Integer nTotal = this.boardService.findnTotalGames();
		Integer gamesPlayerTotal = this.boardService.findnTotalGamesPlayer(currentUser.getUsername());
		Integer gamesPlayerTotalWon= this.boardService.findnTotalGamesPlayerWon(currentUser.getUsername(), GameStatus.WON);
		Integer gamesActivatedMines= this.boardService.findnTotalActvitadedMines(currentUser.getUsername(), GameStatus.LOST);
		long timeTotalPlayed = this.boardService.totalDurationGamesPlayed();
		long minutes = timeTotalPlayed/60;
		long seconds = timeTotalPlayed%60;
		model.put("nTotal", nTotal);
		model.put("gamesPlayerTotal", gamesPlayerTotal);
		model.put("gamesPlayerTotalWon", gamesPlayerTotalWon);
		model.put("minesActivated", gamesActivatedMines);
		model.put("minutesTotalPlayed", minutes);
		model.put("secondsTotalPlayed", seconds);
		return "boards/statistics";
		
	}
	
	@GetMapping(value = "setDifficulty")
	public String setDifficulty() {
		return VIEWS_NEW_BOARD;
	}

}
