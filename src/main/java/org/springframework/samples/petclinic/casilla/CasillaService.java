package org.springframework.samples.petclinic.casilla;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CasillaService {
    
    @Autowired
    private CasillaRepository casillaRepository;

    @Autowired
	public CasillaService(CasillaRepository casillaRepository) {
		this.casillaRepository = casillaRepository;
	}

    
    @Transactional(readOnly = true)
	public Casilla findCasillaById(int id) throws DataAccessException {
		return casillaRepository.findById(id);
	}



    @Transactional
	public List<Casilla> findAll(){
		List<Casilla> allCasillas= new ArrayList<Casilla>();
		casillaRepository.findAll().forEach(allCasillas::add);
		return allCasillas;
	}



    @Transactional
    public Casilla findCasillaByPosition(int coordenadaX, int coordenadaY) throws DataAccessException{
        return casillaRepository.findByPosition(coordenadaX,coordenadaY);
    }
}
