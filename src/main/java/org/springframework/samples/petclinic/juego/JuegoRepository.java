package org.springframework.samples.petclinic.juego;

import java.util.List;
import java.util.Optional;


import org.springframework.samples.petclinic.juego.Juego;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.dao.DataAccessException;


@Repository
public interface JuegoRepository extends CrudRepository<Juego, Integer>{
	
	@Query("SELECT juego FROM Juego juego")
	Juego findJuego();
	

    

}
