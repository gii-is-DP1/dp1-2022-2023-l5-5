package org.springframework.samples.petclinic.board;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends CrudRepository<Board, Integer>{



	@Query("SELECT board FROM Board board WHERE board.id =:id")
	public Board findById(@Param("id") int id);

	@Query("SELECT board FROM Board board")
	public List<Board> findAll();
}