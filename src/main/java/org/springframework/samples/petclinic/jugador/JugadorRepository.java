package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface JugadorRepository extends Repository<Jugador, Integer>{
    void save(Jugador jugador) throws DataAccessException;


    //@Query("SELECT jugador FROM Jugador WHERE jugador.nombreUsuario LIKE :nombreUsuario%")
	//public Collection<Jugador> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

	@Query("SELECT jugador FROM Jugador jugador WHERE jugador.id =:id")
	public Jugador findById(@Param("id") int id);

	@Query("SELECT jugador FROM Jugador jugador")
	public List<Jugador> findAll();
}
