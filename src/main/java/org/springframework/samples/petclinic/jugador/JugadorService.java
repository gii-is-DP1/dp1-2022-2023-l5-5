package org.springframework.samples.petclinic.jugador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {
    
	@Autowired
    private JugadorRepository jugadorRepository;	

	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;


	

	@Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}	

    @Transactional(readOnly = true)
	public Jugador findJugadorById(int id) throws DataAccessException {
		return jugadorRepository.findById(id);
	}

	@Transactional
	public List<Jugador> findAll(){
		List<Jugador> allJugadores= new ArrayList<Jugador>();
		jugadorRepository.findAll().forEach(allJugadores::add);
		return allJugadores;
	}

	// @Transactional(readOnly = true)
	// public Collection<Jugador> findJugadorByNombreUsuario(String nombreUsuario) throws DataAccessException {
	// 	return jugadorRepository.findByNombreUsuario(nombreUsuario);
	// }



	@Transactional
	public void saveJugador(Jugador jugador) throws DataAccessException {
		//creating jugador
		jugadorRepository.save(jugador);		
		//creating user
		userService.saveUser(jugador.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(jugador.getUser().getUsername(), "jugador");
	}	

}
