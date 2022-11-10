package org.springframework.samples.petclinic.player;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

	@Query("SELECT player FROM Player player")
	Player findPlayer();
	
	
//	@Query("SELECT jugador FROM Jugador Jugador WHERE jugador.user.username =:username")
//	Jugador findJugadorByUsername(String username);
	
	@Query("SELECT player FROM Player player")
	public List<Player> findAllPlayers();
}


