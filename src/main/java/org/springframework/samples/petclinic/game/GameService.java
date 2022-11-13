package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<Game> findGames() {
		return this.gameRepository.findGames();
	}

	@Transactional
    public Game save(Game game){
        return gameRepository.save(game);       
    }
	
	@Transactional
	public void deleteGame(int id) {
		gameRepository.deleteById(id); 
	}
}
