package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{
	
	@Query("SELECT game FROM Game game")
	List<Game> findGames();
	
	@Query("SELECT game FROM Game game")
	public List<Game> findAllGames();

    

}
