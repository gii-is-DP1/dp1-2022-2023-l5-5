package org.springframework.samples.petclinic.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardRequestController {

	@Autowired
	public BoardService boardService;
	
	@Autowired
	public PlayerService playerService;
	
	@GetMapping()
	public Iterable<Board> all(){
		return boardService.findAllBoards();
	}
	
	
	@GetMapping(path="/{boardId}")
	public Board valor(@PathVariable("boardId") int boardId) {
		Board res = boardService.findBoardById(boardId).get();
		return res;
	}
	

	@GetMapping(path = "/new/{difficulty}")
	public Board nuevo(@PathVariable("difficulty") int difficulty) {
		int row, column, mine = 0;
		if(difficulty==1) {
			row = 8;
			column = 8;
			mine = 9;
		}else if (difficulty==2) {
			row = 14;
			column = 14;
			mine = 30;
		}else {
			row= 24;
			column= 24;
			mine= 86;
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Board board = new Board();
//    	if (authentication.getName()=="anonymousUser") {
//    		board = new Board(row, column, mine, mine, null);
//    	} else {
    		User currentUser = (User) authentication.getPrincipal();
    		board = new Board(row, column, mine, mine, playerService.getPlayerByUsername(currentUser.getUsername()));
//    	}
		boardService.saveBoard(board);
		
		return board;
	}
	
	@GetMapping("/{boardId}/click/{row}/{column}")
	public Board click(@PathVariable("boardId") int id, @PathVariable("row") int r, @PathVariable("column") int c) {
		Board board = boardService.findBoardById(id).get();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			if (authentication.isAuthenticated()) {
				if ((board.getPlayer().getUser().getUsername().equals(authentication.getName()))) {	
					boardService.click(r, c, board);
					boardService.hasWon(board);
					boardService.hasLost(board);
					boardService.saveBoard(board);
					return board;
				}
			}
		}
		return board;
	}
	

	@GetMapping("/{boardId}/rightClick/{row}/{column}")
	public Board clickDerecho(@PathVariable("boardId") int id, @PathVariable("row") int f, @PathVariable("column") int c) {
		Board t = boardService.findBoardById(id).get();
		boardService.rightClick(f, c, t);
		boardService.saveBoard(t);
		return t;	

	}
	
}
