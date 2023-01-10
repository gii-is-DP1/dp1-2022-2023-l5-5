package org.springframework.samples.petclinic.square;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SquareRepository extends CrudRepository<Square,Integer>{
	
	@Query("SELECT s from Square s where s.board=:board and s.row=:row and s.column=:column")
	Square findByPosition(@Param("board") int board, @Param("row") int row, @Param("column") int column);
}
