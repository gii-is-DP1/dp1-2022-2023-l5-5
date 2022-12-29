package org.springframework.samples.petclinic.board;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.square.Square;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(classes= {Service.class, Configuration.class}))
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PlayerService playerService;
	
	@Test
	public void testClickBoardwithoutMines() {
		Board b0 = new Board(3, 3, 0, 3, null);
		String bp0 = b0.toString2();
		assertEquals(bp0, "TTT\nTTT\nTTT\n"); 
		boardService.click(0, 0, b0);
		String bp3 = b0.toString2();
		assertEquals(bp3, "FFF\nFFF\nFFF\n");
	
	}

	@Test
	public void testClickCorner1() {
		Board b4 = new Board(5,5,5);
		click1(0, 0, b4);
		String bp1 = b4.toString2();
		assertEquals(bp1, "FTTTT\nTTTTT\nTFTTT\nTTTFT\nTFTTF\n");
	}
	
	@Test
	public void testClickCorner2() {
		Board b4 = new Board(5,5,5);
		click1(0, 4, b4);
		String bp1 = b4.toString2();
		assertEquals(bp1, "TFFFF\nTFFFF\nTTFFF\nTTTTT\nTTTTT\n");
	}
	
	@Test
	public void testClickCorner3() {
		Board b4 = new Board(5,5,5);
		boardService.click(4, 0, b4);
		String bp1 = b4.toString2();
		assertEquals(bp1, "TTTTT\nTTTTT\nTTTTT\nTTTTT\nFTTTT\n");
	}
  
	@Test
	public void testClickCorner4() {
		Board b4 = new Board(5,5,5);
		click1(4, 4, b4);
		String bp1 = b4.toString2();
		assertEquals(bp1, "FTTTT\nTTTTT\nTFTTT\nTTTFT\nTFTTF\n");
	}
	
	@Test
	@Transactional
	public void testSaveBoard() {
		Board board = new Board(3, 3, 3, 3, null);
		boardService.saveBoard(board);
		assertNotNull(board.getId());
	}
	
	@Test
	public void testFindBoardById() {
		Board board = new Board(3, 3, 3, 3, null);
		boardService.saveBoard(board);
		Board board2 = boardService.findBoardById(board.getId()).get();
		assertNotNull(board2.getId());
	}
	
// 	Este no va
//	@Test
//	public void testFindBoardByUsername() {
//		List<Board> boards = boardService.findBoardByUsername("meriglmar", GameStatus.NONE);
//		assertEquals(boards.size(), 6);
//	}
	
	public Board click1(int row, int column, Board board) {
		if(board.getGameStatus()==GameStatus.NONE) {
	 		board.setGameStatus(GameStatus.IN_PROGRESS);
	 		board.setStartTime(LocalDateTime.now());
		}
		
		Square s = board.squares.get(column+row*board.columnsNumber);
    	if(s.isMine()) {
	    	s.setCovered(false);
	    	board.squares.set(column+row*board.columnsNumber, s);
	    	for (int i = 0; i < board.rowsNumber; i++) {
	    		for (int j = 0; j < board.columnsNumber; j++) {
	    			Square c1 = board.squares.get(j+i*board.columnsNumber);
	    			if(c1.isMine()&&c1.isCovered) {
	    				c1.setCovered(false);
	    		    	board.squares.set(j+i*board.columnsNumber, c1);
	    			}
	    			if(c1.isFlag && !c1.isMine()) {
	    				c1.setWrong(true);
	    			}
	    		}
	    	}
	    	return board;
    	
    	}else if(s.valor!=0&&s.isCovered){
			s.setCovered(false);
			board.squares.set(column+row*board.columnsNumber, s);
			return board;
    	
    	}else if (s.valor==0&&s.isCovered){
    		s.setCovered(false);
			board.squares.set(column+row*board.columnsNumber, s);
    		List<Square> square = board.surroundingSquares(row, column);
    		for(int squ = 0; squ<square.size(); squ++) {
    			Square sq = square.get(squ);
    			if(sq.fila>=0 && sq.fila<board.rowsNumber&& sq.columna>=0 && sq.columna<board.columnsNumber) {
    				board = click1(sq.fila,sq.columna, board);
    			
    			}
    		}
    		return board;
    	}
    	return board;
    }
}
