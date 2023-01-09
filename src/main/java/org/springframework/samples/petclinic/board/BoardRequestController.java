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
	
	private static final int EASY_BOARD_SIZE = 8;
	private static final int MEDIUM_BOARD_SIZE = 14;
	private static final int DIFFICULT_BOARD_SIZE = 24;
	private static final double MINES_NUMBER = 0.15;

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
			row = EASY_BOARD_SIZE;
			column = EASY_BOARD_SIZE;
			mine = (int) (EASY_BOARD_SIZE * EASY_BOARD_SIZE * MINES_NUMBER);
		}else if (difficulty==2) {
			row = MEDIUM_BOARD_SIZE;
			column = MEDIUM_BOARD_SIZE;
			mine = (int) (MEDIUM_BOARD_SIZE * MEDIUM_BOARD_SIZE * MINES_NUMBER);
		}else {
			row= DIFFICULT_BOARD_SIZE;
			column= DIFFICULT_BOARD_SIZE;
			mine= (int) (DIFFICULT_BOARD_SIZE * DIFFICULT_BOARD_SIZE * MINES_NUMBER);
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Board board = new Board();
		User currentUser = (User) authentication.getPrincipal();
		board = new Board(row, column, mine, mine, playerService.getPlayerByUsername(currentUser.getUsername()));
		boardService.saveBoard(board);
		
		return board;
	}
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		User currentUser = (User) authentication.getPrincipal();
//		Board board = new Board();
//		if (boardService.findBoardByUsername(currentUser.getUsername(), GameStatus.IN_PROGRESS).isEmpty()) {
//			board = new Board(row, column, mine, mine, playerService.getPlayerByUsername(currentUser.getUsername()));
//			boardService.saveBoard(board);
//			
//		} else {
//			System.out.println("You must end a game to start another");
//		}
//		return board;
//	}
	
	@GetMapping("/{boardId}/click/{row}/{column}")
	public Board click(@PathVariable("boardId") int id, @PathVariable("row") int f, @PathVariable("column") int c) {
		Board board = boardService.findBoardById(id).get();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			if (authentication.isAuthenticated()) {
				//if((board.modifier.equals("anonymousUser")) || (board.getPlayer().getUser().getUsername().equals(authentication.getName()))) {
				if ((board.getPlayer().getUser().getUsername().equals(authentication.getName()))) {	
					boardService.click(f, c, board);
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
