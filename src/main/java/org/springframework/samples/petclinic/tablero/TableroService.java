package org.springframework.samples.petclinic.tablero;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
    
	@Autowired
    private TableroRepository tableroRepository;	




	

	@Autowired
	public TableroService(TableroRepository tableroRepository) {
		this.tableroRepository = tableroRepository;
	}	

    @Transactional(readOnly = true)
	public Optional<Tablero> findTableroById(int id) throws DataAccessException {
		return tableroRepository.findById(id);
	}

	@Transactional
	public List<Tablero> findAll(){
		List<Tablero> allTableros= new ArrayList<Tablero>();
		tableroRepository.findAll().forEach(allTableros::add);
		return allTableros;
	}

	// @Transactional(readOnly = true)
	// public Collection<Jugador> findJugadorByNombreUsuario(String nombreUsuario) throws DataAccessException {
	// 	return jugadorRepository.findByNombreUsuario(nombreUsuario);
	// }



}
