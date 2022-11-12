package org.springframework.samples.petclinic.board;



import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    
	@Autowired
    private BoardRepository boardRepository;	




	

	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}	

    @Transactional(readOnly = true)
	public Board findboardById(int id) throws DataAccessException {
		return boardRepository.findById(id);
	}

	@Transactional
	public List<Board> findAll(){
		List<Board> allBoards= new ArrayList<Board>();
		boardRepository.findAll().forEach(allBoards::add);
		return allBoards;
	}

	// @Transactional(readOnly = true)
	// public Collection<Jugador> findJugadorByNombreUsuario(String nombreUsuario) throws DataAccessException {
	// 	return jugadorRepository.findByNombreUsuario(nombreUsuario);
	// }



}
