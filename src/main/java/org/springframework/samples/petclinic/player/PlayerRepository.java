package org.springframework.samples.petclinic.player;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
	
	@Query("SELECT player FROM Player player")
	List<Player> findAllPlayers();
	
	@Query("SELECT player FROM Player player")
	List<Player> findAllPlayers(Pageable pageable);
	
	@Query("SELECT COUNT(ID) FROM Player player")
	Integer countAllPlayers();

	@Query("SELECT player FROM Player player WHERE player.user.username=:username")
	public Player findPlayerByUsername(@Param("username") String username);
	
	
}


