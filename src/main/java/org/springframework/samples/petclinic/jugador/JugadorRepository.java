package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer> {


//   @Query("SELECT jugador FROM Jugador jugador WHERE jugador.id =:id")
//	public Jugador findById(@Param("id") int id);
//
//	@Query("SELECT jugador FROM Jugador jugador")
//	public List<Jugador> findAll();

	@Query("SELECT jugador FROM Jugador jugador")
	List<Jugador> findAllJugador();
}


