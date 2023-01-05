package org.springframework.samples.petclinic.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    
    private PlayerRepository playerRepository;	

	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}	

	@Transactional(readOnly = true)
	public List<Player> findAllPlayers(Integer page, Pageable pageable) {
		return this.playerRepository.findAllPlayers(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Player> findAll() {
		return this.playerRepository.findAllPlayers();
	}

	@Transactional(readOnly = true)
	public Integer countAllPlayers() throws DataAccessException {
		return playerRepository.countAllPlayers();
	}


	@Transactional
	public void savePlayer(Player player) throws DataAccessException {
		//creating player
		playerRepository.save(player);		
		//creating user
		userService.saveUser(player.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(player.getUser().getUsername(), "player");
	}	
	
	@Transactional
	public Boolean validator(Player player) throws DataAccessException {
		Boolean res = null;
		Iterable<Player> lista = this.playerRepository.findAll(); //El findAll devuelve un Iterable
		List<String> lis = new ArrayList<>();
		for (Player a : lista) {
			lis.add(a.getUser().getUsername());
		}
		if (lis.contains(player.getUser().getUsername())) {
			res = true;
		} else {
			res = false;
		}
		
		return res;
	}
	
	@Transactional(readOnly = true)
	public Optional<Player> getPlayerById(int id) throws DataAccessException {
		return playerRepository.findById(id);
	}
	
	
	@Transactional(readOnly = true)
	public Player getPlayerByUsername(String username) throws DataAccessException {
		return playerRepository.findPlayerByUsername(username);
	}

	@Transactional
	public void deletePlayer(Integer id) {
		playerRepository.deleteById(id); 

	}



}
