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
	
	@Test
	public void testFindAllGamesByPlayerAndStatusOrder() {
		List<Board> boards = boardService.findAllGamesByPlayerAndStatusOrder("angbermar1", GameStatus.NONE);
		assertEquals(boards.size(), 4);
	}
	
	@Test
	public void testFindValueSquare() {
		Board b = new Board(3, 3, 0, 3, null);
		boardService.saveBoard(b);
		int s = b.getSquare(2, 2).getValue();
		assertEquals(s, 0);
	}
	
	@Test
	public void testDiscoverAllSquares() {
		Board b0 = new Board(10,10,0);
		Board bFinal = new Board(10,10,0);
		for(int i=0;i<b0.columnsNumber;i++) {
			for(int j=0;j<b0.rowsNumber;j++) {
				bFinal = boardService.click(i, j, b0);
			}
		}
		for(int i=0;i<b0.columnsNumber;i++) {
			for(int j=0;j<b0.rowsNumber;j++) {
				assertEquals(bFinal.getSquare(i,j).isCovered, false);
			}
		}
	}
	
	@Test
	public void testBoardSaveAndCount() {
		Board b0 = new Board(10,10,0);
		Board b1 = new Board(10,10,0);
		boardService.saveBoard(b0);
		boardService.saveBoard(b1);
		assertEquals(boardService.boardCount(), 22);
	}
	
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
    	
    	}else if(s.value!=0&&s.isCovered){
			s.setCovered(false);
			board.squares.set(column+row*board.columnsNumber, s);
			return board;
    	
    	}else if (s.value==0&&s.isCovered){
    		s.setCovered(false);
			board.squares.set(column+row*board.columnsNumber, s);
    		List<Square> square = board.surroundingSquares(row, column);
    		for(int squ = 0; squ<square.size(); squ++) {
    			Square sq = square.get(squ);
    			if(sq.row>=0 && sq.row<board.rowsNumber&& sq.column>=0 && sq.column<board.columnsNumber) {
    				board = click1(sq.row,sq.column, board);
    			
    			}
    		}
    		return board;
    	}
    	return board;
    }
	
	@Test
	public void testStateAfterStartGameWithRightClick() {
		Player p = new Player();
		Board b0 = new Board(10,10,10,10,p);
		boardService.rightClick(0, 4, b0);
		assertEquals(b0.gameStatus, GameStatus.IN_PROGRESS);
	}
	
	@Test 
	public void testRightClickOneTime() {
		Board b = new Board(10,10,10,10,null);
		Integer flagsNum = b.flagsNumber;
		Board b1 = boardService.rightClick(0, 0, b);
		assertEquals(b1.flagsNumber, flagsNum-1);
	}
	@Test 
	public void testRightClickTwoTime() {
		Board b = new Board(10,10,10,10,null);
		Integer flagsNum = b.flagsNumber;
		Board b1 = boardService.rightClick(0, 0, b);
		assertEquals(b1.flagsNumber, flagsNum-1);
		Board b2 = boardService.rightClick(0, 0, b1);
		assertEquals(b2.flagsNumber,flagsNum);
	}
	
	@Test
	public void testFindAllBoards() {
		assertEquals(boardService.findAllBoards().size(),20);
	}
	
	@Test
	public void testFindAllGamesByStatus() {
		List<Board> boards = boardService.findAllGamesByStatus(GameStatus.IN_PROGRESS);
		assertEquals(boards.size(), 2);
	}
	
	@Test
	public void testFindAllGamesPlayer() {
		List<Board> boards = boardService.findAllGamesByPlayer("meriglmar");
		assertEquals(boards.size(), 4);
	}
	
	@Test
	public void testBeforeStartGame(){
		Player p= new Player();
		Board b0= new Board(10,10,10,10,p);
		assertEquals(b0.gameStatus, GameStatus.NONE);
	}

	@Test
	public void testAfterStartGame(){
		Player p= new Player();
		Board b0= new Board(10,10,10,10,p);
		boardService.click(7, 7, b0);
		assertEquals(b0.gameStatus, GameStatus.IN_PROGRESS);
	}

	@Test
	public void testYouWon(){
		Board b0= new Board(5,5,5);
		click1(0, 4, b0);
		click1(1, 0, b0);
		click1(1, 1, b0);
		click1(2, 0, b0);
		click1(3, 0, b0);
		click1(4, 0, b0);
		click1(3, 1, b0);
		click1(3, 2, b0);
		click1(3, 4, b0);
		click1(4, 2, b0);
		click1(4, 3, b0);
		String b1=b0.toString2();
		assertEquals(b1, "TFFFF\nFFFFF\nFTFFF\nFFFTF\nFTFFT\n");
		assertEquals(BoardService.hasWon(b0).gameStatus, GameStatus.WON);
	}


	@Test
	public void testYouLost(){
		Board b0= new Board(5,5,5);
		click1(0,0,b0);
		Board b1=BoardService.hasLost(b0);
		assertEquals(b1.gameStatus, GameStatus.LOST);
	}

}
