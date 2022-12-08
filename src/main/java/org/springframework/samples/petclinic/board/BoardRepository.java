package org.springframework.samples.petclinic.board;



import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends CrudRepository<Board, Integer>{


	@Query("SELECT board FROM Board board")
	public List<Board> findAll();
	
//	@Query("SELECT board FROM Board board WHERE board.inProgress=false")
//	public List<Board> findAllGamesNotInProgress();

//	@Query("SELECT game FROM Game game WHERE game.inProgress=true")
//	public List<Board> findAllGamesInProgress();
	
	@Query("SELECT board FROM Board board WHERE board.player.user.username LIKE :username AND board.gameStatus NOT LIKE :gameStatus AND board.finishTime != NULL  ORDER BY board.finishTime DESC")
	List<Board> findBoardByUsername(String username, GameStatus gameStatus);

	@Query("SELECT board FROM Board board WHERE board.gameStatus NOT LIKE :gameStatus ORDER BY board.finishTime DESC")
	List<Board> findAllGamesNotInProgress(GameStatus gameStatus);
	
	@Query("SELECT board FROM Board board WHERE board.player.user.username =:username")
	public List<Board> findAllGamesPlayer(@Param("username") String username);
	
//	@Query("SELECT COUNT(game) FROM Game game")
//	Integer nTotalGames();
//
//	@Query("SELECT COUNT(game) FROM Game game WHERE game.player.user.username =:username")
//	Integer nTotalGamesPlayer(@Param("username") String username);
}