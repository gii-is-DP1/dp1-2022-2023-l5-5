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

	@Autowired
	private BoardService boardService;
	
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
	public String processFindForm(ModelMap modelMap, @PageableDefault(page = 0, size = 6) 
		@SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)}) Pageable pageable) {
		Integer page = 0;
		List<Board> results = boardService.findAllWonAndLostGamesPageable(page, pageable);
		Integer numResults = results.size();
		//modelMap.addAttribute("board", board);
		modelMap.put("pageNumber", pageable.getPageNumber());
		modelMap.put("hasPrevious", pageable.hasPrevious());
		Double totalPages = Math.ceil(numResults / (pageable.getPageSize()));
		modelMap.put("totalPages", totalPages);
		modelMap.put("selections", results);
		return "boards/gamesList";
	}
	
	@GetMapping(value= "/listplayer")
	public String processFindFormPlayer(Board board, BindingResult result, Map<String, Object> model) {

		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		
	    List<Board> results = this.boardService.findAllGamesByPlayerNotByStatus(currentUser.getUsername(), GameStatus.NONE);
		model.put("board", results);
		return "boards/gamesListPlayer";
		
	}
	
	@GetMapping(value = "setDifficulty")
	public String setDifficulty() {
		return VIEWS_NEW_BOARD;
	}

}
