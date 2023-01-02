package org.springframework.samples.petclinic.square;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.board.Board;

public interface SquareRepository extends CrudRepository<Square,Integer>{
    
    

//    @Query("SELECT square FROM Square square WHERE square.coordinateX = :coordinateX AND square.coordinateY = :coordinateY")
//    public Square findByPosition(@Param("coordinateX") int coordinateX, @Param("coordinateY") int coordinateY );
//
//
//
//
//    @Query("SELECT square FROM Square square")
//	public List<Square> findAll();
//
//
//
//    @Query("SELECT square FROM Square square WHERE square.id =:id")
//	public Square findById(@Param("id") int id);
	
	/*@Query("SELECT p from Square p where square.board=:board and square.row=:row and square.column=:column")
	Board findByPosition(@Param("board") int board,
			@Param("row") int row, @Param("column") int column);*/
}
