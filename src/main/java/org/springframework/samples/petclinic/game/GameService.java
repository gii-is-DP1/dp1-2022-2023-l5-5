package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	
	@Transactional(readOnly = true)
	public Optional<Game> getGameById(int id) throws DataAccessException {
		return gameRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Game> findAllGames() {
		List<Game> allGames= new ArrayList<Game>();
		gameRepository.findAll().forEach(allGames::add);
		return allGames;
	}
}
