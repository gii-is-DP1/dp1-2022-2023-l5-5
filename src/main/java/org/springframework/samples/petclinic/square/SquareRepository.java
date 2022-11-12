package org.springframework.samples.petclinic.square;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SquareRepository extends CrudRepository<Square,Integer>{
    
    

    @Query("SELECT square FROM Square square WHERE square.coordenadaX = :coordenadaX AND square.coordenadaY = :coordenadaY")
    public Square findByPosition(@Param("coordenadaX") int coordenadaX, @Param("coordenadaY") int coordenadaY );




    @Query("SELECT square FROM Square square")
	public List<Square> findAll();



    @Query("SELECT square FROM Square square WHERE square.id =:id")
	public Square findById(@Param("id") int id);
}
