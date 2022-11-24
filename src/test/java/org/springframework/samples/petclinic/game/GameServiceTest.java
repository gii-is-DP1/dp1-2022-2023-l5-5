package org.springframework.samples.petclinic.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTest {
	
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private TableroService tableroService;
	
	
	//Test de crear juego (H6) No va a ir hasta que podamos crear el juego correctamente, pero está bien
//	@Test
//	void shouldFindGames() {
//		
//		List<Game> list = this.gameService.findGames();
//		int size1 = list.size();
//		Game game = new Game();
//		game.setDifficulty(Difficulty.EASY);
//		game.setStartTime(LocalDateTime.of(1990, 06, 20, 8, 05, 00, 0));
//		game.setFinishTime(LocalDateTime.of(1990, 06, 20, 8, 06, 10, 0));
//		game.setNumClicks(Integer.valueOf(50));
//		game.setLostGame(true);
//		game.setInProgress(false);
//		game.setPlayer(this.playerService.getPlayerByUsername("paomarsan"));
//		game.setTablero(this.tableroService.findTableroById(1));
//		
//		this.gameService.save(game);
//		
//		List<Game> list2 = this.gameService.findGames();
//		int size2 = list2.size();
//		
//		assertThat(size1<size2);
//		
//	}
	
	
	//Test de listar partidas de un jugador (H7)
	@Test
	void shouldFindAllGamesPlayer() throws Exception {
		Collection<Game> games = this.gameService.findAllGamesPlayer("paomarsan");
		assertThat(games.size()).isEqualTo(2);
		Game[] gameArr = games.toArray(new Game[games.size()]);
		assertThat(gameArr[0].getPlayer()).isNotNull();
		assertThat(gameArr[0].getPlayer().getUser().getUsername().equals("paomarsan"));
	}
	
	
	//Test de listar partidas InProgress (H8)
	@Test
	void shouldFindAllGamesInProgress() throws Exception {
		Collection<Game> games = this.gameService.findAllGamesInProgress();
		assertThat(games.size()).isEqualTo(3);
		Game[] gameArr = games.toArray(new Game[games.size()]);
		assertThat(gameArr[1].getInProgress().equals(true));
	}
	
	//Test de listar partidas NotInProgress (H9)
		@Test
		void shouldFindAllGamesNotInProgress() throws Exception {
			Collection<Game> games = this.gameService.findAllGamesNotInProgress();
			assertThat(games.size()).isEqualTo(4);
			Game[] gameArr = games.toArray(new Game[games.size()]);
			assertThat(gameArr[0].getInProgress().equals(false));
		}
	
	
	

}
