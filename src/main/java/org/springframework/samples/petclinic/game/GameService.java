package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public Game findGame() {
		return this.gameRepository.findGame();
	}

	@Transactional
    public Game save(Game game){
        return gameRepository.save(game);       
    }
}
