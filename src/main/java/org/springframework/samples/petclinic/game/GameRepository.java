package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Optional;


import org.springframework.samples.petclinic.game.Game;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.dao.DataAccessException;


@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{
	
	@Query("SELECT game FROM Game game")
	Game findGame();
	

    

}
