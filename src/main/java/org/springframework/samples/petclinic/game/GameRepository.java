package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.casilla.Casilla;



@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{
	
	@Query("SELECT game FROM Game game")
	List<Game> findGames();
	
	@Query("SELECT game FROM Game game WHERE game.id =:id")
	public Game findById(@Param("id") int id);
	

    

}
