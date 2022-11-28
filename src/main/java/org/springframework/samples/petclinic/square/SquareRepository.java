package org.springframework.samples.petclinic.square;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SquareRepository extends CrudRepository<Square,Integer>{
    
    

    @Query("SELECT square FROM Square square WHERE square.coordinateX = :coordinateX AND square.coordinateY = :coordinateY")
    public Square findByPosition(@Param("coordinateX") int coordinateX, @Param("coordinateY") int coordinateY );




    @Query("SELECT square FROM Square square")
	public List<Square> findAll();



    @Query("SELECT square FROM Square square WHERE square.id =:id")
	public Square findById(@Param("id") int id);
}
