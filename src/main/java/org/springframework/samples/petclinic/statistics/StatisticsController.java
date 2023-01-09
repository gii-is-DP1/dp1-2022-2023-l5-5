package org.springframework.samples.petclinic.statistics;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.GameStatus;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistic")
public class StatisticsController {

	//private final StatisticsService statisticsService;
	//private final PlayerService playerService;

	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private PlayerService playerService;

//	@Autowired
//	public StatisticsController(PlayerService playerService, StatisticsService statisticsService) {
//		this.playerService = playerService;
//		this.statisticsService = statisticsService;
//	}

//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}
	@GetMapping(value= "/statistics")
	public String statistics(Board board, BindingResult result, Map<String, Object> model) {
		
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		long nTotal = this.statisticsService.findnTotalGames();
		long gamesPlayerTotal = this.statisticsService.findnTotalGamesPlayer(currentUser.getUsername());
		long gamesPlayerTotalWon= this.statisticsService.findnTotalGamesPlayerWon(currentUser.getUsername());
		long gamesActivatedMines= this.statisticsService.findnTotalActivatedMines(currentUser.getUsername());
		//no funciona bien
		long gamesPlacedFlags= this.statisticsService.findnTotalPlacedFlags(currentUser.getUsername());

		long timeTotalPlayed = this.statisticsService.totalDurationGamesPlayed();
		long minutes = timeTotalPlayed/60;
		long seconds = timeTotalPlayed%60;
		long timeTotalPlayer = this.statisticsService.totalDurationGamesPlayer(currentUser.getUsername());
		long minut = timeTotalPlayer/60;
		long secon = timeTotalPlayer%60;
		long averageTimePlayed = this.statisticsService.averageDurationGamesPlayed();
		long minu = averageTimePlayed/60;
		long seco = averageTimePlayed%60;
		long averageTimePlayer = this.statisticsService.averageDurationGamesPlayer(currentUser.getUsername());
		long min = averageTimePlayer/60;
		long sec = averageTimePlayer%60;
		long maxTimePlayed = this.statisticsService.maxDurationGamesPlayed();
		long mi = maxTimePlayed/60;
		long se = maxTimePlayed%60;
		long maxTimePlayer = this.statisticsService.maxDurationGamesPlayer(currentUser.getUsername());
		long m = maxTimePlayer/60;
		long s = maxTimePlayer%60;
		long minTimePlayed = this.statisticsService.minDurationGamesPlayed();
		long mm = minTimePlayed/60;
		long ss = minTimePlayed%60;
		long minTimePlayer = this.statisticsService.minDurationGamesPlayer(currentUser.getUsername());
		long mmm = minTimePlayer/60;
		long sss = minTimePlayer%60;
		long numWonPlayed = this.statisticsService.numGamesWonPlayed();
		long numLostPlayer = this.statisticsService.numGamesLostPlayer(currentUser.getUsername());
		long numLostPlayed = this.statisticsService.numGamesLostPlayed();
		long numGamesWinEasyPlayer = this.statisticsService.numGamesWinEasyPlayer(currentUser.getUsername());
		long numGamesWinMediumPlayer = this.statisticsService.numGamesWinMediumPlayer(currentUser.getUsername());
		long numGamesWinDifficultPlayer = this.statisticsService.numGamesWinDifficultPlayer(currentUser.getUsername());
		long numGamesWinEasyPlayed = this.statisticsService.numGamesWinEasyPlayed();
		long numGamesWinMediumPlayed = this.statisticsService.numGamesWinMediumPlayed();
		long numGamesWinDifficultPlayed = this.statisticsService.numGamesWinDifficultPlayed();

		model.put("nTotal", nTotal);
		model.put("gamesPlayerTotal", gamesPlayerTotal);
		model.put("gamesPlayerTotalWon", gamesPlayerTotalWon);
		model.put("minesActivated", gamesActivatedMines);
		model.put("gamesPlacedFlags", gamesPlacedFlags);
		model.put("minutesTotalPlayed", minutes);
		model.put("secondsTotalPlayed", seconds);
		model.put("minutesTotalPlayer", minut);
		model.put("secondsTotalPlayer", secon);
		model.put("avgminTimePlayed", minu);
		model.put("avgsecTimePlayed", seco);
		model.put("avgminTimePlayer", min);
		model.put("avgsecTimePlayer", sec);
		model.put("maxminTimePlayed", mi);
		model.put("maxsecTimePlayed", se);
		model.put("maxminTimePlayer", m);
		model.put("maxsecTimePlayer", s);
		model.put("minminTimePlayed", mm);
		model.put("minsecTimePlayed", ss);
		model.put("minminTimePlayer", mmm);
		model.put("minsecTimePlayer", sss);
		model.put("numWonPlayed", numWonPlayed);
		model.put("numLostPlayer", numLostPlayer);
		model.put("numLostPlayed", numLostPlayed);
		model.put("numGamesWinEasyPlayer", numGamesWinEasyPlayer);
		model.put("numGamesWinMediumPlayer", numGamesWinMediumPlayer);
		model.put("numGamesWinDifficultPlayer", numGamesWinDifficultPlayer);
		model.put("numGamesWinEasyPlayed", numGamesWinEasyPlayed);
		model.put("numGamesWinMediumPlayed", numGamesWinMediumPlayed);
		model.put("numGamesWinDifficultPlayed", numGamesWinDifficultPlayed);
		return "statistics/statis";
		
	}
	
	private void addToModel(Map<String, Object> model, List<Map.Entry<String, Integer>> list, String prefix) {
	    String msg = "No data available yet";
	    if (list.size() == 0) {
	        model.put(prefix + "1", msg);
	        model.put(prefix + "gameswon1", msg);
	    } else if (list.size() == 1) {
	        model.put(prefix + "1", list.get(0).getKey());
	        model.put(prefix + "gameswon1", list.get(0).getValue());
	    } else if (list.size() == 2) {
	        model.put(prefix + "1", list.get(0).getKey());
	        model.put(prefix + "gameswon1", list.get(0).getValue());
	        model.put(prefix + "2", list.get(1).getKey());
	        model.put(prefix + "gameswon2", list.get(1).getValue());
	    } else {
	        model.put(prefix + "1", list.get(0).getKey());
	        model.put(prefix + "gameswon1", list.get(0).getValue());
	        model.put(prefix + "2", list.get(1).getKey());
	        model.put(prefix + "gameswon2", list.get(1).getValue());
	        model.put(prefix + "3", list.get(2).getKey());
	        model.put(prefix + "gameswon3", list.get(2).getValue());
	    }
	}

	
	@GetMapping("/rankings")
    public String showRanking(Map<String, Object> model){
		
			
			List<Player> players =  playerService.findAll();
            List<Board> games = statisticsService.gamesWonPlayer();

            List<Map.Entry<String, Integer>> list = statisticsService.ranking(players, games);
            List<Map.Entry<String, Integer>> listEasy = statisticsService.rankingEasy(players, games);
            List<Map.Entry<String, Integer>> listMed = statisticsService.rankingMedium(players, games);
            List<Map.Entry<String, Integer>> listDiff = statisticsService.rankingDifficult(players, games);
            addToModel(model, list, "player");
            addToModel(model, listEasy, "playerEasy");
            addToModel(model, listMed, "playerMed");
            addToModel(model, listDiff, "playerDiff");

            return "statistics/ranking";
    }
	
}
