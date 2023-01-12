package org.springframework.samples.petclinic.statistics;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.BoardRepository;
import org.springframework.samples.petclinic.board.GameStatus;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatisticsService {
	
	private static final int EASY_BOARD_SIZE = 8;
    private static final int MEDIUM_BOARD_SIZE = 14;
    private static final int DIFFICULT_BOARD_SIZE  = 24;
    
	@Autowired
    private BoardRepository boardRepository;	
	
	@Transactional(readOnly = true)
	public List<Board> findAllWonAndLostGamesGlobal (){
		List<Board> list = boardRepository.findAll();
		List<Board> res = list.stream().filter(x -> x.gameStatus == GameStatus.WON || x.gameStatus == GameStatus.LOST).collect(Collectors.toList());
		return res;
	}
	
	@Transactional(readOnly = true)
	public List<Board> findAllWonAndlostGamesPlayer(String username){
		List<Board> list = boardRepository.findAllGamesByPlayer(username);
		List<Board> res = list.stream().filter(x -> x.gameStatus == GameStatus.WON || x.gameStatus == GameStatus.LOST).collect(Collectors.toList());
		return res;
	}

	@Transactional(readOnly = true)
	public long findnTotalGames(){
		List<Board> list = findAllWonAndLostGamesGlobal();
		long res = list.stream().count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long findnTotalGamesPlayer(String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().count();
		return res;
	}

	@Transactional(readOnly = true)
	public long findnTotalGamesPlayerWon(String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON).count();
		return res;
	}

	@Transactional(readOnly = true)
	public long findnTotalActivatedMines(String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.LOST).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long findnTotalPlacedFlags(String username) {
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		Long banderasTotal = (long) 0;
		for(Board board : list) {
			Integer columns_num=board.columnsNumber;
			if(columns_num == 8) {
				banderasTotal+= 9- board.getFlagsNumber();
			}else if(columns_num == 14) {
				banderasTotal+= 30- board.getFlagsNumber();
			}else { //24
				banderasTotal+= 86- board.getFlagsNumber();
			}	
		}
		return banderasTotal;
	}
	@Transactional(readOnly = true)
	public long totalDurationGamesPlayed(){
		List<Board> list = findAllWonAndLostGamesGlobal();
		long res = list.stream().mapToInt(x -> (int) x.getDurationGame()).sum();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long averageDurationGamesPlayed(){
		List<Board> list = findAllWonAndLostGamesGlobal();
		Double res = list.stream().mapToInt(x -> (int) x.getDurationGame()).average().orElse(0.0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long maxDurationGamesPlayed(){
		List<Board> list = findAllWonAndLostGamesGlobal();
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).max().orElse(0);
		long res1 =  res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long minDurationGamesPlayed(){
		List<Board> list = findAllWonAndLostGamesGlobal();
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).min().orElse(0);
		long res1 =  res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWonPlayed (){
		List<Board> list = findAllWonAndLostGamesGlobal();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesLostPlayed (){
		List<Board> list = findAllWonAndLostGamesGlobal();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.LOST).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinEasyPlayed (){
		List<Board> list = findAllWonAndLostGamesGlobal();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == EASY_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinMediumPlayed (){
		List<Board> list = findAllWonAndLostGamesGlobal();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == MEDIUM_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinDifficultPlayed (){
		List<Board> list = findAllWonAndLostGamesGlobal();
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == DIFFICULT_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long totalDurationGamesPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().mapToInt(x -> (int) x.getDurationGame()).sum();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long averageDurationGamesPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		Double res = list.stream().mapToInt(x -> (int) x.getDurationGame()).average().orElse(0.0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long maxDurationGamesPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).max().orElse(0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public long minDurationGamesPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		Integer res = list.stream().mapToInt(x -> (int) x.getDurationGame()).min().orElse(0);
		long res1 = res.longValue();
		return res1;
	}
	
	@Transactional(readOnly = true)
	public List<Board> gamesWonPlayer (){
		List<Board> list = findAllWonAndLostGamesGlobal();
		List<Board> res = list.stream().filter(x -> x.gameStatus == GameStatus.WON).collect(Collectors.toList());
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesLostPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.LOST).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinEasyPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == EASY_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinMediumPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == MEDIUM_BOARD_SIZE).count();
		return res;
	}
	
	@Transactional(readOnly = true)
	public long numGamesWinDifficultPlayer (String username){
		List<Board> list = findAllWonAndlostGamesPlayer(username);
		long res = list.stream().filter(x -> x.gameStatus == GameStatus.WON && x.getColumnsNumber() == DIFFICULT_BOARD_SIZE).count();
		return res;
	}
	
	//ranking en general, más partidas ganadas 
	public List<Map.Entry<String, Integer>> ranking(List<Player> players, List<Board> games) {

		Map<String, Integer> sortedMap = new TreeMap<>();
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

	// ranking nivel facil, más partidas ganadas por nivel dificultad facil
	public List<Map.Entry<String, Integer>> rankingEasy(List<Player> players, List<Board> games) {

		Map<String, Integer> sortedMap = new TreeMap<>();
		for (Player player : players) {
			for (Board game : games) {
				if (game.getColumnsNumber() == EASY_BOARD_SIZE && player.equals(game.getPlayer())) {
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

	// ranking nivel medio, más partidas ganadas por nivel dificultad medio
	public List<Map.Entry<String, Integer>> rankingMedium(List<Player> players, List<Board> games) {

		Map<String, Integer> sortedMap = new TreeMap<>();
		for (Player player : players) {
			for (Board game : games) {
				if (game.getColumnsNumber() == MEDIUM_BOARD_SIZE && player.equals(game.getPlayer())) {
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

	// ranking nivel dificil, más partidas ganadas por nivel dificultad dificil
	public List<Map.Entry<String, Integer>> rankingDifficult(List<Player> players, List<Board> games) {

		Map<String, Integer> sortedMap = new TreeMap<>();
		for (Player player : players) {
			for (Board game : games) {
				if (game.getColumnsNumber() == DIFFICULT_BOARD_SIZE && player.equals(game.getPlayer())) {
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

}
