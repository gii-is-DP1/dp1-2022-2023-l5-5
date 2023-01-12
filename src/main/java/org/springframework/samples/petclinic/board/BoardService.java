package org.springframework.samples.petclinic.board;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.square.Square;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    
	@Autowired
    private BoardRepository boardRepository;	
	
    @Transactional(readOnly = true)
	public Optional<Board> findBoardById(int id) throws DataAccessException {
		return boardRepository.findById(id);
	}  
    
	@Transactional(readOnly = true)
	public List<Board> findAllBoards(){
		return boardRepository.findAll();
	}
	
	@Transactional
	public void saveBoard(Board board) throws DataAccessException {
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
    public int boardCount() {
        return (int) boardRepository.count();
    }
	
	@Transactional(readOnly = true)
    public List<Board> findAllGamesByPlayerAndStatus(String username, GameStatus gameStatus) throws DataAccessException{
    	return boardRepository.findAllGamesByPlayerAndStatus(username, gameStatus);
    }
	
	@Transactional(readOnly = true)
    public List<Board> findGamesInProgressByPlayer(String username){
		List<Board> list = boardRepository.findAllGamesByPlayer(username);
		List<Board> res = list.stream().filter(x -> x.gameStatus == GameStatus.IN_PROGRESS).collect(Collectors.toList());
		return res;
    }
	
	@Transactional(readOnly = true)
	public List<Board> findAllWonAndLostGames(Integer page, Pageable pageable){
		List<Board> list = boardRepository.findAll(pageable);
		List<Board> list_filtrada = list.stream().filter(x -> x.gameStatus == GameStatus.WON || x.gameStatus == GameStatus.LOST).collect(Collectors.toList());
		return list_filtrada;
	}
	
	@Transactional(readOnly = true)
	public List<Board> findAllWonAndLostGames(){
		List<Board> list = boardRepository.findAll();
		List<Board> list_filtrada = list.stream().filter(x -> x.gameStatus == GameStatus.WON || x.gameStatus == GameStatus.LOST).collect(Collectors.toList());
		return list_filtrada;
	}

	@Transactional(readOnly = true)
	public List<Board> findAllGamesByStatus(GameStatus status){
		return boardRepository.findAllGamesByStatus(status);
	}

	@Transactional(readOnly = true)
	public List<Board> findAllGamesByPlayer(String username){
		return this.boardRepository.findAllGamesByPlayer(username);
		
	}
	
	@Transactional(readOnly = true)
	public List<Board> findAllGamesByPlayerNotByStatus(String username, GameStatus status){
		return this.boardRepository.findAllGamesByPlayerNotByStatus(username, status);
		
	}
	
	public Board click(int row, int column, Board board) {
		 	if(board.getGameStatus()==GameStatus.NONE) {
		 		board.setGameStatus(GameStatus.IN_PROGRESS);
		 		board.setStartTime(LocalDateTime.now());;
		 		board.putMines(row, column);
		 	}
		 	
	    	Square square = board.squares.get(column+row*board.columnsNumber);
	    	if(square.isMine()) {
		    	square.setCovered(false);
		    	board.squares.set(column+row*board.columnsNumber, square);
		    	for (int i = 0; i < board.rowsNumber; i++) {
		    		for (int j = 0; j < board.columnsNumber; j++) {
	    		    	Square c1 = board.squares.get(j+i*board.columnsNumber);
		    			if(c1.isMine( ) && c1.isCovered) {
		    				c1.setCovered(false);
		    		    	board.squares.set(j+i*board.columnsNumber, c1);
		    			}else if(c1.isFlag && !c1.isMine()) {
		    				c1.setWrong(true);
		    			}
		    		}
		    	}
		    	return board;

	    	}else if(square.value !=0 && square.isCovered){
				square.setCovered(false);
				board.squares.set(column+row*board.columnsNumber, square);
				return board;
	    	
	    	}else if (square.value == 0 && square.isCovered){
	    		square.setCovered(false);
				board.squares.set(column+row*board.columnsNumber, square);
	    		List<Square> squares = board.surroundingSquares(row, column);
	    		for(int sq = 0; sq<squares.size(); sq++) {
	    			Square s = squares.get(sq);
	    			if(s.row>=0 && s.row<board.rowsNumber&& s.column>=0 && s.column<board.columnsNumber) {
	    				board = click(s.row,s.column, board);
	    			}
	    		}
	    		return board;
	    	}
	    	return board;

	 }
	 
	 public Board rightClick(int row, int column, Board board) {
		 if(board.getGameStatus()==GameStatus.NONE) {
		 		board.setGameStatus(GameStatus.IN_PROGRESS);
		 		board.setStartTime(LocalDateTime.now());
		 }
		 Square c = board.squares.get(column+row*board.columnsNumber);
		 if (c.isCovered && board.flagsNumber>0) {
			 c.setFlag(true);
			 c.setCovered(false);
			 board.setFlagsNumber(board.flagsNumber-1);
		 }else if (!c.isCovered && c.isFlag) {
			 c.setCovered(true);
			 c.setFlag(false);
			 board.setFlagsNumber(board.flagsNumber+1);
		 }
		 return board;
	 }
	 
	 public static Board hasWon(Board board) {
		 List<Square> lista = board.getSquares();
		 Integer k = 0;
		 for(int i = 0; i<lista.size();i++) {
			 if((!lista.get(i).isMine && !lista.get(i).isCovered)) {
				 k = k+1;
			 }
		 }
		 if(k==(board.getRowsNumber()*board.getColumnsNumber())-board.getMinesNumber())
			 board.setGameStatus(GameStatus.WON);
		 	 board.setFinishTime(LocalDateTime.now());
		 	 board.setDuration(Duration.between(board.startTime, board.finishTime));
		 return board;
	 }
	 
	 public static Board hasLost(Board t) {
		 List<Square> lista = t.getSquares();
		 for(int i = 0; i<lista.size();i++) {
			 if(lista.get(i).isMine && !lista.get(i).isCovered && !lista.get(i).isFlag) {
			 t.setGameStatus(GameStatus.LOST);
			 t.setFinishTime(LocalDateTime.now());
			 t.setDuration(Duration.between(t.startTime, t.finishTime));
			 }
		 }
		 return t;
	 }
	 
}
