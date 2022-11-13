package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;


@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{
	
	@Query("SELECT game FROM Game game")
	List<Game> findGames();
	
	@Query("SELECT game FROM Game game WHERE game.inProgress=false")
	public List<Game> findAllGamesNotInProgress();

	@Query("SELECT game FROM Game game WHERE game.inProgress=true")
	public List<Game> findAllGamesInProgress();

	@Query("SELECT game FROM Game game WHERE game.player.user.username =:username")
	public List<Game> findAllGamesPlayer(@Param("username") String username);

    

}
