package org.springframework.samples.petclinic.tablero;



import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface TableroRepository extends Repository<Tablero, Integer>{
    void save(Tablero jugador) throws DataAccessException;



	@Query("SELECT tablero FROM Tablero tablero WHERE tablero.id =:id")
	public Tablero findById(@Param("id") int id);

	@Query("SELECT tablero FROM Tablero tablero")
	public List<Tablero> findAll();
}