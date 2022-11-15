package org.springframework.samples.petclinic.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
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
	
	
	//Test de findGames y save de Game, getPlayerByUsername de Player y findTableroById de Tablero
	@Test
	void shouldFindGames() {
		
		List<Game> list = this.gameService.findGames();
		int size1 = list.size();
		Game game = new Game();
		game.setDifficulty(Difficulty.EASY);
		game.setStartTime(LocalDateTime.of(1990, 06, 20, 8, 05, 00, 0));
		game.setFinishTime(LocalDateTime.of(1990, 06, 20, 8, 06, 10, 0));
		game.setNumClicks(Integer.valueOf(50));
		game.setLostGame(true);
		game.setInProgress(false);
		game.setPlayer(this.playerService.getPlayerByUsername("paomarsan"));
		game.setTablero(this.tableroService.findTableroById(1));
		
		this.gameService.save(game);
		
		List<Game> list2 = this.gameService.findGames();
		int size2 = list2.size();
		
		assertThat(size1<size2);
		
	}
	

}
