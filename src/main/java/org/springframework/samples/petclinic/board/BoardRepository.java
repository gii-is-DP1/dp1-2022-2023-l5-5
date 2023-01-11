package org.springframework.samples.petclinic.board;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer>{


	@Query("SELECT board FROM Board board")
	List<Board> findAll();
	
	@Query("SELECT board FROM Board board")
	List<Board> findAll(Pageable pageable);

    @Query("SELECT board FROM Board board WHERE board.gameStatus LIKE :gameStatus")
	List<Board> findAllGamesByStatus(GameStatus gameStatus);
	
	@Query("SELECT board FROM Board board WHERE board.player.user.username LIKE :username AND board.gameStatus NOT LIKE :gameStatus AND board.finishTime != NULL  ORDER BY board.finishTime DESC")
	List<Board> findAllGamesByPlayerAndStatus(String username, GameStatus gameStatus);

	@Query("SELECT board FROM Board board WHERE board.player.user.username =:username AND board.gameStatus NOT LIKE :gameStatus ORDER BY board.finishTime DESC")
	List<Board> findAllGamesByPlayerNotByStatus(@Param("username") String username, GameStatus gameStatus);
	
	@Query("SELECT board FROM Board board WHERE board.player.user.username =:username")
	List<Board> findAllGamesByPlayer(@Param("username") String username);
	
	@Query("SELECT COUNT(board) FROM Board board")
	Integer nTotalGames();

	@Query("SELECT COUNT(board) FROM  Board board WHERE board.player.user.username =:username")
	Integer nTotalGamesPlayer(@Param("username") String username);

	@Query("SELECT COUNT(board) FROM  Board board WHERE board.player.user.username =:username AND board.gameStatus LIKE :gameStatus")
	Integer nTotalGamesByPlayerAndStatus(@Param("username") String username, GameStatus gameStatus);
}