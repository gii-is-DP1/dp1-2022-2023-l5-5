package org.springframework.samples.petclinic.board;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


public interface BoardRepository extends CrudRepository<Board, Integer>{


	@Query("SELECT board FROM Board board")
	public List<Board> findAll();
	
	@Query("SELECT board FROM Board board")
	public List<Board> findAllPageable(Pageable pageable);

    @Query("SELECT board FROM Board board WHERE board.gameStatus LIKE :gameStatus")
	public List<Board> findAllGamesInProgress(GameStatus gameStatus);
	
	@Query("SELECT board FROM Board board WHERE board.player.user.username LIKE :username AND board.gameStatus NOT LIKE :gameStatus AND board.finishTime != NULL  ORDER BY board.finishTime DESC")
	List<Board> findBoardByUsername(String username, GameStatus gameStatus);

	@Query("SELECT board FROM Board board WHERE board.gameStatus NOT LIKE :gameStatus ORDER BY board.finishTime DESC")
	List<Board> findAllGamesNotInProgress(GameStatus gameStatus);
	
	@Query("SELECT board FROM Board board WHERE board.player.user.username =:username AND board.gameStatus NOT LIKE :gameStatus ORDER BY board.finishTime DESC")
	List<Board> findAllGamesByPlayerNotByStatus(@Param("username") String username, GameStatus gameStatus);
	
	@Query("SELECT board FROM Board board WHERE board.player.user.username =:username")
	public List<Board> findAllGamesPlayer(@Param("username") String username);
	
	@Query("SELECT COUNT(board) FROM Board board")
	Integer nTotalGames();

	@Query("SELECT COUNT(board) FROM  Board board WHERE board.player.user.username =:username")
	Integer nTotalGamesPlayer(@Param("username") String username);

	@Query("SELECT COUNT(board) FROM  Board board WHERE board.player.user.username =:username AND board.gameStatus LIKE :gameStatus")
	Integer nTotalGamesByPlayerAndStatus(@Param("username") String username, GameStatus gameStatus);
}