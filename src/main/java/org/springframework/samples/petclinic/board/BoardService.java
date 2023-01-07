package org.springframework.samples.petclinic.board;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.square.Square;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
	
	private static final int EASY_BOARD_SIZE = 8;
    private static final int MEDIUM_BOARD_SIZE = 14;
    private static final int DIFFICULT_BOARD_SIZE  = 24;
    
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
	public void saveBoard(Board board) throws DataAccessException {
		boardRepository.save(board);
	}
	
	@Transactional
    public int boardCount() {
        return (int) boardRepository.count();
    }
	
//	@Transactional
//	public List<Board> findAllBoardsWithoutState(GameStatus gameStatus, Pageable pageable){
//		return boardRepository.findAllBoardsWithoutState(gameStatus, pageable);
//	}
	
	@Transactional
    public List<Board> findBoardByUsername(String username, GameStatus gameStatus) throws DataAccessException{
    	return boardRepository.findBoardByUsername(username, gameStatus);
    }
	
	@Transactional
	public List<Board> findAllGamesNotInProgress(GameStatus status){
		return boardRepository.findAllGamesNotInProgress(status);
	}

	@Transactional
	public List<Board> findAllGamesInProgress(GameStatus status){
		return boardRepository.findAllGamesInProgress(status);
	}

	@Transactional(readOnly = true)
	public List<Board> findAllGamesPlayer(String username){
		return this.boardRepository.findAllGamesPlayer(username);
		
	}

	@Transactional(readOnly = true)
	public Integer findnTotalGames(){
		return this.boardRepository.nTotalGames();
	}
	
	@Transactional(readOnly = true)
	public Integer findnTotalGamesPlayer(String username){
		return this.boardRepository.nTotalGamesPlayer(username);
	}

	@Transactional(readOnly = true)
	public Integer findnTotalGamesPlayerWon(String username, GameStatus gameStatus){
		return this.boardRepository.nTotalGamesPlayerWon(username,gameStatus);
	}

	@Transactional(readOnly = true)
	public Integer findnTotalActivatedMines(String username, GameStatus gameStatus){
		return this.boardRepository.nTotalActivatedMines(username,gameStatus);
	}
	
	@Transactional(readOnly = true)
	public Integer findnTotalPlacedFlags(String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		return list.stream().mapToInt(x -> x.getFlagsNumber()).sum();
	}
	
	@Transactional(readOnly = true)
	public long totalDurationGamesPlayed(){
		List<Board> list = boardRepository.findAll();
		long res = list.stream().mapToInt(x -> (int) x.getDurationGame()).sum();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long averageDurationGamesPlayed(){
		List<Board> list = boardRepository.findAll();
		Double res = list.stream().mapToInt(x -> (int) x.getDurationGame()).average().orElse(0.0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long maxDurationGamesPlayed(){
		List<Board> list = boardRepository.findAll();
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).max().orElse(0);
		long res1 =  res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long minDurationGamesPlayed(){
		List<Board> list = boardRepository.findAll();
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).min().orElse(0);
		long res1 =  res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWonPlayed (){
		List<Board> list = boardRepository.findAll();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesLostPlayed (){
		List<Board> list = boardRepository.findAll();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.LOST).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinEasyPlayed (){
		List<Board> list = boardRepository.findAll();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == EASY_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinMediumPlayed (){
		List<Board> list = boardRepository.findAll();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == MEDIUM_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinDifficultPlayed (){
		List<Board> list = boardRepository.findAll();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == DIFFICULT_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long totalDurationGamesPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		long res = list.stream().mapToInt(x -> (int) x.getDurationGame()).sum();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long averageDurationGamesPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		Double res = list.stream().mapToInt(x -> (int) x.getDurationGame()).average().orElse(0.0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long maxDurationGamesPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).max().orElse(0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long minDurationGamesPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).min().orElse(0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWonPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public List<Board> gamesWonPlayer (){
		List<Board> list = boardRepository.findAll();
		List<Board> res = list.stream().filter(x -> x.gameStatus == GameStatus.WON).collect(Collectors.toList());
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesLostPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.LOST).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinEasyPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == EASY_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinMediumPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == MEDIUM_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinDifficultPlayer (String username){
		List<Board> list = boardRepository.findAllGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == DIFFICULT_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public List<Map.Entry<String, Integer>> ranking (List<Player> players, List<Board> games) {
		
		Map<String, Integer> sortedMap =  new TreeMap<>();
		for (Player player : players) {
			for (Board game : games) {
				if (player.equals(game.getPlayer())) {
					if (sortedMap.containsKey(player.getUser().getUsername())) {
						Integer res = sortedMap.get(player.getUser().getUsername());
						res++;
						sortedMap.put(player.getUser().getUsername(), res);
					} else {
						sortedMap.put(player.getUser().getUsername(), 1);
					}
				}
			}
		}
		List<Map.Entry<String, Integer>> list = sortedMap.entrySet().stream()
				.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.collect(Collectors.toList());
		return list;
	}


	
	@Transactional
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
