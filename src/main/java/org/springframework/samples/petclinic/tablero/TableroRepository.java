package org.springframework.samples.petclinic.tablero;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TableroRepository extends CrudRepository<Tablero, Integer>{



	@Query("SELECT tablero FROM Tablero tablero WHERE tablero.id =:id")
	public Tablero findById(@Param("id") int id);

	@Query("SELECT tablero FROM Tablero tablero")
	public List<Tablero> findAll();
}