package org.springframework.samples.petclinic.statistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.board.GameStatus;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class StatisticsServiceTest {
	
	private static final int EASY_BOARD_SIZE = 8;
    private static final int MEDIUM_BOARD_SIZE = 14;
    private static final int DIFFICULT_BOARD_SIZE  = 24;
	
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
	
	@Test
	void shouldFindnTotalPlacedFlags() {
		long result = statisticsService.findnTotalPlacedFlags("paomarsan");
		assertThat(result).isEqualTo(9);
	}
	
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
        board1.setColumnsNumber(EASY_BOARD_SIZE); 
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(MEDIUM_BOARD_SIZE); 
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.WON);
        board1.setColumnsNumber(DIFFICULT_BOARD_SIZE); 
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
	

	@Test
	void testGamesWonPlayer() {
		Board board1 = new Board();
        board1.setGameStatus(GameStatus.WON);
        boardService.saveBoard(board1);

        Board board2 = new Board();
        board2.setGameStatus(GameStatus.LOST);
        boardService.saveBoard(board2);

        Board board3 = new Board();
        board3.setGameStatus(GameStatus.IN_PROGRESS);
        boardService.saveBoard(board3);

        List<Board> result = statisticsService.gamesWonPlayer();
        
		assertThat(result).hasSize(15);
	}
	
	@Test
	void testNumGamesLostPlayer() {
		
		long result = statisticsService.numGamesLostPlayer("pabquide");
		
		assertThat(result).isEqualTo(2);
	}
	
	@Test
	void testNumGamesWinEasyPlayer() {
		
		long result = statisticsService.numGamesWinEasyPlayer("pabquide");
		
		assertThat(result).isEqualTo(2);
	}
	
	@Test
	void testNumGamesWinMediumPlayer() {
		
		long result = statisticsService.numGamesWinMediumPlayer("pabquide");
		
		assertThat(result).isEqualTo(0);
	}
	
	@Test
	void testNumGamesWinDifficultPlayer() {
		
		long result = statisticsService.numGamesWinDifficultPlayer("pabquide");
		
		assertThat(result).isEqualTo(0);
	}
	
    @Test
    public void testRanking() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        User user3 = new User();
        user3.setUsername("user3");
        
        player1.setUser(user1);
        player2.setUser(user2);
        player3.setUser(user3);
        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        Board game1 = new Board();
        game1.setPlayer(player1);
        Board game2 = new Board();
        game2.setPlayer(player2);
        Board game3 = new Board();
        game3.setPlayer(player3);
        Board game4 = new Board();
        game4.setPlayer(player1);
        Board game5 = new Board();
        game5.setPlayer(player1);
        
        List<Board> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        games.add(game4);
        games.add(game5);

        List<Map.Entry<String, Integer>> result = statisticsService.ranking(players, games);

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getKey()).isEqualTo("user1");
        assertThat(result.get(0).getValue()).isEqualTo(3);
        assertThat(result.get(1).getKey()).isEqualTo("user2");
        assertThat(result.get(1).getValue()).isEqualTo(1);
        assertThat(result.get(2).getKey()).isEqualTo("user3");
        assertThat(result.get(2).getValue()).isEqualTo(1);
    }
	
    @Test
    public void testRankingEasy() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        User user3 = new User();
        user3.setUsername("user3");
        
        player1.setUser(user1);
        player2.setUser(user2);
        player3.setUser(user3);
        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        Board game1 = new Board();
        game1.setPlayer(player1);
        game1.setColumnsNumber(EASY_BOARD_SIZE);
        Board game2 = new Board();
        game2.setPlayer(player2);
        game2.setColumnsNumber(EASY_BOARD_SIZE);
        Board game3 = new Board();
        game3.setPlayer(player3);
        game3.setColumnsNumber(EASY_BOARD_SIZE);
        Board game4 = new Board();
        game4.setPlayer(player1);
        game4.setColumnsNumber(EASY_BOARD_SIZE);
        Board game5 = new Board();
        game5.setPlayer(player1);
        game5.setColumnsNumber(EASY_BOARD_SIZE);
        
        List<Board> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        games.add(game4);
        games.add(game5);

        List<Map.Entry<String, Integer>> result = statisticsService.ranking(players, games);

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getKey()).isEqualTo("user1");
        assertThat(result.get(0).getValue()).isEqualTo(3);
        assertThat(result.get(1).getKey()).isEqualTo("user2");
        assertThat(result.get(1).getValue()).isEqualTo(1);
        assertThat(result.get(2).getKey()).isEqualTo("user3");
        assertThat(result.get(2).getValue()).isEqualTo(1);
    }
    
    @Test
    public void testRankingMedium() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        User user3 = new User();
        user3.setUsername("user3");
        
        player1.setUser(user1);
        player2.setUser(user2);
        player3.setUser(user3);
        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        Board game1 = new Board();
        game1.setPlayer(player1);
        game1.setColumnsNumber(MEDIUM_BOARD_SIZE);
        Board game2 = new Board();
        game2.setPlayer(player2);
        game2.setColumnsNumber(MEDIUM_BOARD_SIZE);
        Board game3 = new Board();
        game3.setPlayer(player3);
        game3.setColumnsNumber(MEDIUM_BOARD_SIZE);
        Board game4 = new Board();
        game4.setPlayer(player1);
        game4.setColumnsNumber(MEDIUM_BOARD_SIZE);
        Board game5 = new Board();
        game5.setPlayer(player1);
        game5.setColumnsNumber(MEDIUM_BOARD_SIZE);
        
        List<Board> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        games.add(game4);
        games.add(game5);

        List<Map.Entry<String, Integer>> result = statisticsService.ranking(players, games);

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getKey()).isEqualTo("user1");
        assertThat(result.get(0).getValue()).isEqualTo(3);
        assertThat(result.get(1).getKey()).isEqualTo("user2");
        assertThat(result.get(1).getValue()).isEqualTo(1);
        assertThat(result.get(2).getKey()).isEqualTo("user3");
        assertThat(result.get(2).getValue()).isEqualTo(1);
    }
	
    @Test
    public void testRankingDifficult() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        User user3 = new User();
        user3.setUsername("user3");
        
        player1.setUser(user1);
        player2.setUser(user2);
        player3.setUser(user3);
        
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        Board game1 = new Board();
        game1.setPlayer(player1);
        game1.setColumnsNumber(DIFFICULT_BOARD_SIZE);
        Board game2 = new Board();
        game2.setPlayer(player2);
        game2.setColumnsNumber(DIFFICULT_BOARD_SIZE);
        Board game3 = new Board();
        game3.setPlayer(player3);
        game3.setColumnsNumber(DIFFICULT_BOARD_SIZE);
        Board game4 = new Board();
        game4.setPlayer(player1);
        game4.setColumnsNumber(DIFFICULT_BOARD_SIZE);
        Board game5 = new Board();
        game5.setPlayer(player1);
        game5.setColumnsNumber(DIFFICULT_BOARD_SIZE);
        
        List<Board> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        games.add(game4);
        games.add(game5);

        List<Map.Entry<String, Integer>> result = statisticsService.ranking(players, games);

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getKey()).isEqualTo("user1");
        assertThat(result.get(0).getValue()).isEqualTo(3);
        assertThat(result.get(1).getKey()).isEqualTo("user2");
        assertThat(result.get(1).getValue()).isEqualTo(1);
        assertThat(result.get(2).getKey()).isEqualTo("user3");
        assertThat(result.get(2).getValue()).isEqualTo(1);
    }
	
}
