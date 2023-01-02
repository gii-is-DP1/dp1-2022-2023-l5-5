package org.springframework.samples.petclinic.casilla;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CasillaRepository extends CrudRepository<Casilla,Integer>{
    
    

    @Query("SELECT casilla FROM Casilla casilla WHERE casilla.coordenadaX = :coordenadaX AND casilla.coordenadaY = :coordenadaY")
    public Casilla findByPosition(@Param("coordenadaX") int coordenadaX, @Param("coordenadaY") int coordenadaY );




    @Query("SELECT casilla FROM Casilla casilla")
	public List<Casilla> findAll();



    @Query("SELECT casilla FROM Casilla casilla WHERE casilla.id =:id")
	public Casilla findById(@Param("id") int id);
}
