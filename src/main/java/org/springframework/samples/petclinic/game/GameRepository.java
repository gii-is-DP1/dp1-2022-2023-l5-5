package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



//@Repository
//public interface GameRepository extends CrudRepository<Game, Integer>{
	
//	@Query("SELECT game FROM Game game")
//	List<Game> findGames();
//	
//	@Query("SELECT game FROM Game game WHERE game.id =:id")
//	public Optional<Game> findById(@Param("id") int id);
//
//	@Query("SELECT game FROM Game game WHERE game.inProgress=false")
//	public List<Game> findAllGamesNotInProgress();
//
//	@Query("SELECT game FROM Game game WHERE game.inProgress=true")
//	public List<Game> findAllGamesInProgress();
//
//	@Query("SELECT game FROM Game game WHERE game.player.user.username =:username")
//	public List<Game> findAllGamesPlayer(@Param("username") String username);
//
//	@Query("SELECT DISTINCT difficulty FROM Game")
//	List<String> findAllDifficulties();
//
//	@Query("SELECT COUNT(game) FROM Game game")
//	Integer nTotalGames();
//
//	@Query("SELECT COUNT(game) FROM Game game WHERE game.player.user.username =:username")
//	Integer nTotalGamesPlayer(@Param("username") String username);
//
//}
