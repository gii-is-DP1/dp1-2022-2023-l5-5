package org.springframework.samples.petclinic.statistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.board.GameStatus;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class StatisticsServiceTest {
	
	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private BoardService boardService;
	
	@Test
	void shouldFindAllWonAndLostGamesGlobal() {
		
        Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.LOST);
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.IN_PROGRESS);
        boardService.saveBoard(board3);

        List<Board> result = statisticsService.findAllWonAndLostGamesGlobal();
        
        assertThat(result).hasSize(20);
	}
	
	@Test
	void shouldFindAllWonAndlostGamesPlayer() {

        List<Board> result = statisticsService.findAllWonAndlostGamesPlayer("meriglmar");
        
        assertThat(result).hasSize(4);
	}
	
	@Test
	void shouldFindnTotalGames() {
		
		Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.LOST);
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.IN_PROGRESS);
        boardService.saveBoard(board3);

        long result = statisticsService.findnTotalGames();
        
        assertThat(result).isEqualTo(20);
	}
	
	@Test
	void shouldFindnTotalGamesPlayer() {

        long result = statisticsService.findnTotalGamesPlayer("meriglmar");
        
        assertThat(result).isEqualTo(4);
	}
	
	@Test
	void shouldFindnTotalGamesPlayerWon() {
		
		long result = statisticsService.findnTotalGamesPlayerWon("meriglmar");
		
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	void shouldFindnTotalActivatedMines() {
		
		long result = statisticsService.findnTotalActivatedMines("meriglmar");
		
		assertThat(result).isEqualTo(1);
	}
	
//	@Test
//	void shouldFindnTotalPlacedFlags() {
//		
//		long result = statisticsService.findnTotalPlacedFlags("meriglmar");
//	}
	
	//Falta el método de los clicks también, cuando se haga en el servicio se hace el test
	
	@Test
	void testTotalDurationGamesPlayed() {
		
		Board board1 = new Board();
		board1.setDuration(Duration.ofSeconds(300));
		boardService.saveBoard(board1);
		Board board2 = new Board();
		board2.setDuration(Duration.ofSeconds(550));
		boardService.saveBoard(board2);
		Board board3 = new Board();
		board3.setDuration(Duration.ofSeconds(210));
		boardService.saveBoard(board3);

		long totalDuration = statisticsService.totalDurationGamesPlayed();

		assertThat(totalDuration).isEqualTo(373); 
	}
	
	@Test
	void testAverageDurationGamesPlayed() {
		Board board1 = new Board();
		board1.setDuration(Duration.ofSeconds(300));
		boardService.saveBoard(board1);
		Board board2 = new Board();
		board2.setDuration(Duration.ofSeconds(550));
		boardService.saveBoard(board2);
		Board board3 = new Board();
		board3.setDuration(Duration.ofSeconds(210));
		boardService.saveBoard(board3);

		long totalDuration = statisticsService.averageDurationGamesPlayed();

		assertThat(totalDuration).isEqualTo(20);
	}
	
	@Test
	void testMaxDurationGamesPlayed() {
		Board board1 = new Board();
		board1.setDuration(Duration.ofSeconds(300));
		boardService.saveBoard(board1);
		Board board2 = new Board();
		board2.setDuration(Duration.ofSeconds(550));
		boardService.saveBoard(board2);
		Board board3 = new Board();
		board3.setDuration(Duration.ofSeconds(210));
		boardService.saveBoard(board3);

		long totalDuration = statisticsService.maxDurationGamesPlayed();

		assertThat(totalDuration).isEqualTo(103);
	}
	
	@Test
	void testMinDurationGamesPlayed() {
		Board board1 = new Board();
		board1.setDuration(Duration.ofSeconds(300));
		boardService.saveBoard(board1);
		Board board2 = new Board();
		board2.setDuration(Duration.ofSeconds(550));
		boardService.saveBoard(board2);
		Board board3 = new Board();
		board3.setDuration(Duration.ofSeconds(210));
		boardService.saveBoard(board3);

		long totalDuration = statisticsService.minDurationGamesPlayed();

		assertThat(totalDuration).isEqualTo(1);
	}	
	
	@Test
	void testNumGamesWonPlayed() {
		Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.LOST);
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.IN_PROGRESS);
        boardService.saveBoard(board3);

        long result = statisticsService.numGamesWonPlayed();
        
        assertTrue(result == 15);
	}
	
	@Test
	void testNumGamesLostPlayed() {
		Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.LOST);
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.IN_PROGRESS);
        boardService.saveBoard(board3);

        long result = statisticsService.numGamesLostPlayed();
        
        assertTrue(result == 5);
	}
	
	@Test
	void testNumGamesWinEasyPlayed() {
		Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(8); //8 es Easy
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(14); //14 es Medium
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(24); //24 es Difficult
        boardService.saveBoard(board3);

        long result = statisticsService.numGamesWinEasyPlayed();
        
        assertTrue(result == 7);
	}
	
	@Test
	void testNumGamesWinMediumPlayed() {
		Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(8); //8 es Easy
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(14); //14 es Medium
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(24); //24 es Difficult
        boardService.saveBoard(board3);

        long result = statisticsService.numGamesWinMediumPlayed();
        
        assertTrue(result == 4);
	}
	
	@Test
	void testNumGamesWinDifficultPlayed() {
		Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(8); //8 es Easy
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(14); //14 es Medium
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(24); //24 es Difficult
        boardService.saveBoard(board3);

        long result = statisticsService.numGamesWinDifficultPlayed();
        
        assertTrue(result == 4);
	}
	
	@Test
	void testTotalDurationGamesPlayer() {
		
		long result = statisticsService.totalDurationGamesPlayer("angbermar1");
		
		assertThat(result).isEqualTo(56);
	}
	
	@Test
	void testAverageDurationGamesPlayer() {
		
		long result = statisticsService.averageDurationGamesPlayer("angbermar1");
		
		assertThat(result).isEqualTo(14);
	}
	
	@Test
	void testMaxDurationGamesPlayer() {
		
		long result = statisticsService.maxDurationGamesPlayer("angbermar1");
		
		assertThat(result).isEqualTo(35);
	}
	
	@Test
	void testMinDurationGamesPlayer() {
		
		long result = statisticsService.maxDurationGamesPlayer("angbermar1");
		
		assertThat(result).isEqualTo(35);
	}
	
	
	
	
}
