package org.springframework.samples.petclinic.board;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
    

	@Autowired
	private final BoardService boardService;

    @Autowired
	public BoardController(BoardService boardService, AuthoritiesService authoritiesService) {
		this.boardService = boardService;
	}

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	
    //Código que habrá que poner dentro de un método para crear un board
    

    

	@GetMapping(value = "/boards")
	public String processFindForm(Board board, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (board.getMines_number()==null) {
			board.setMines_number(0); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Board> results = this.boardService.findAll();
		
			// multiple owners found
			model.put("selections", results);
			return "boards/boardsList";
		
	}

	@GetMapping("/boards/{boardId}")
	public ModelAndView showBoard(@PathVariable("boardId") int boardId) {
		ModelAndView mav = new ModelAndView("boards/boardsDetails");
		mav.addObject(this.boardService.findBoardById(boardId));
		return mav;
	}


	@GetMapping("/boards/prueba/{boardId}")
	public String prueba(Map<String,Object> model, HttpServletResponse response, @PathVariable("boardId") int boardId){
		response.addHeader("Refresh", "1");
		model.put("now", new Date());
		model.put("board", this.boardService.findBoardById(boardId));
		model.put("boardImagen","resources/images/tablero-buscaminas.jpg");
		return "boards/boardsPrueba";
	}	

	
	

}
