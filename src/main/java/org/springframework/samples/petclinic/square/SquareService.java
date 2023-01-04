package org.springframework.samples.petclinic.square;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SquareService {
    
    private SquareRepository squareRepository;

    @Autowired
	public SquareService(SquareRepository squareRepository) {
		this.squareRepository = squareRepository;
	}
    
    @Transactional
	public void saveSquare(Square square) throws DataAccessException {
		squareRepository.save(square);
	}
	
	@Transactional
	public Square findByPosition(int boardId, int row, int column) {
		return this.squareRepository.findByPosition(boardId, row, column);
	}

}
