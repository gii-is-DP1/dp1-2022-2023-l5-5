package org.springframework.samples.petclinic.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    
	@Autowired
    private BoardRepository boardRepository;	
	
//	@Autowired
//    private SquareService squareService;

    @Transactional(readOnly = true)
	public Optional<Board> findBoardById(int id) throws DataAccessException {
		return boardRepository.findById(id);
	}  
    
	@Transactional
	public List<Board> findAllBoards(){
		return boardRepository.findAll();
	}
	
	@Transactional
	public void saveBoard(Board t) throws DataAccessException {
		boardRepository.save(t);
	}
	
	
	@Transactional
    public List<Board> findBoardByUsername(String username, GameStatus gameStatus) throws DataAccessException{
    	return boardRepository.findBoardByUsername(username, gameStatus);
    }
	
	@Transactional
	public List<Board> findAllGamesNotInProgress(GameStatus status){
		return boardRepository.findAllGamesNotInProgress(status);
	}

	@Transactional(readOnly = true)
	public List<Board> findAllGamesPlayer(String username){
		return this.boardRepository.findAllGamesPlayer(username);
		
	}
//Estos métodos habría que hacerlos con Board y NO con games, que se ha borrado
//	@Transactional(readOnly = true)
//	public Integer findnTotalGames(){
//		return this.boardRepository.nTotalGames();
//	}
//	
//	@Transactional(readOnly = true)
//	public Integer findnTotalGamesPlayer(String username){
//		return this.boardRepository.nTotalGamesPlayer(username);
//	}



}
