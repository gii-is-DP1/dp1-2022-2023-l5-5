package org.springframework.samples.petclinic.juego;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JuegoService {
	
	@Autowired
	private JuegoRepository juegoRepository;
	
	@Transactional(readOnly = true)
	public Juego findJuego() {
		return this.juegoRepository.findJuego();
	}

	@Transactional
    public Juego save(Juego juego){
        return juegoRepository.save(juego);       
    }
}
