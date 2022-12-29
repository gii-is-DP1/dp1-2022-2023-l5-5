package org.springframework.samples.petclinic.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.square.Square;
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
	
	
	@Transactional
	 public Board click(int row, int column, Board board) {
		 	if(board.getGameStatus()==GameStatus.NONE) {
		 		board.setGameStatus(GameStatus.IN_PROGRESS);
		 		board.setStartTime(LocalDateTime.now());
		 		board.putMines(row, column);
		 	}
		 	
	    	Square s = board.squares.get(column+row*board.columnsNumber);
	    	if(s.isMine()) {
		    	s.setCovered(false);
		    	board.squares.set(column+row*board.columnsNumber, s);
		    	for (int i = 0; i < board.rowsNumber; i++) {
		    		for (int j = 0; j < board.columnsNumber; j++) {
	    		    	Square s1 = board.squares.get(j+i*board.columnsNumber);
		    			if(s1.isMine( ) && s1.isCovered) {
		    				s1.setCovered(false);
		    				board.squares.set(j+i*board.columnsNumber, s1);
		    			}else if(s1.isFlag && !s1.isMine()) {
		    				s1.setWrong(true);
		    			}
		    		}
		    	}
		    	return board;
	    	
	    	}else if(s.value !=0 && s.isCovered){
				s.setCovered(false);
				board.squares.set(column+row*board.columnsNumber, s);
				return board;
	    	
	    	}else if (s.value == 0 && s.isCovered){
	    		s.setCovered(false);
				board.squares.set(column+row*board.columnsNumber, s);
	    		List<Square> lista = board.surroundingSquares(row, column);
	    		for(int ca = 0; ca<lista.size(); ca++) {
	    			Square square = lista.get(ca);
	    			if(square.row>=0 && square.row<board.rowsNumber&& square.column>=0 && square.column<board.columnsNumber) {
	    				board = click(square.row,square.column, board);
	    			}
	    		}
	    		return board;
	    	}
	    	return board;
	}
	
	public Board clickDerecho(int row, int column, Board board) {
		 if(board.getGameStatus()==GameStatus.NONE) {
			 	board.setGameStatus(GameStatus.IN_PROGRESS);
			 	board.setStartTime(LocalDateTime.now());		 	}
		 Square s = board.squares.get(column+row*board.columnsNumber);
		 if (s.isCovered && board.flagsNumber>0) {
			 s.setFlag(true);
			 s.setCovered(false);
			 board.setFlagsNumber(board.flagsNumber-1);
		 }else if (!s.isCovered && s.isFlag) {
			 s.setCovered(true);
			 s.setFlag(false);
			 board.setFlagsNumber(board.flagsNumber+1);
		 }
		 
		 return board;
	 }



}
