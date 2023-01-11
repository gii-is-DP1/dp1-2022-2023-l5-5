package org.springframework.samples.petclinic.board;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final String VIEWS_BOARD = "boards/board";
	private static final String VIEWS_NEW_BOARD = "boards/setDifficulty";
	private static final String VIEWS_LIST_GAMES = "boards/gamesList";
	private static final String VIEWS_LIST_INPROGRESS_GAMES = "boards/gamesListInProgress";
	private static final String VIEWS_LIST_PLAYER_GAMES = "boards/gamesListPlayer";
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(path = "/game/**")
	public String board(ModelMap modelMap) {
		return VIEWS_BOARD;
	}
	
	@GetMapping(value = "/listinprogress")
	public String processFindFormProgress(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null)
			if (authentication.isAuthenticated()) {
				List<Board> board = boardService.findAllGamesInProgress(GameStatus.IN_PROGRESS);
				modelMap.addAttribute("board", board);
				return VIEWS_LIST_INPROGRESS_GAMES;
			} else {
				System.out.println("User not authenticated");
		}
		return "welcome";
		
	}
    
	//El admin ve el listado de partidas
	@GetMapping(path="/list")
	public String processFindForm(Map<String, Object> model
		 
//		,@PageableDefault(page = 0, size = 6) @SortDefault.SortDefaults({
//		@SortDefault(sort = "id", direction = Sort.Direction.ASC), 
//		@SortDefault(sort = "rowsNumber", direction = Sort.Direction.DESC),}) Pageable pageable
		) {
//		Integer page = 0;
		List<Board> results = boardService.findAllWonAndLostGames(); //page, pageable
//		Integer numResults = results.size();
//		//modelMap.addAttribute("board", board);
//		model.put("pageNumber", pageable.getPageNumber());
//		model.put("hasPrevious", pageable.hasPrevious());
//		Double totalPages = Math.ceil(numResults / (pageable.getPageSize()));
//		model.put("totalPages", totalPages);
		model.put("selections", results);
		return VIEWS_LIST_GAMES;
	}
	
	@GetMapping(value= "/listplayer")
	public String processFindFormPlayer(Board board, BindingResult result, Map<String, Object> model) {

		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		
	    List<Board> results = this.boardService.findAllGamesByPlayerNotByStatus(currentUser.getUsername(), GameStatus.NONE);
		model.put("selections", results);
		return VIEWS_LIST_PLAYER_GAMES;
		
	}
	
	@GetMapping(value = "setDifficulty")
	public String setDifficulty() {
		return VIEWS_NEW_BOARD;
	}

}
