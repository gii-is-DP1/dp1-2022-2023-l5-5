package org.springframework.samples.petclinic.board;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final String VIEWS_BOARD = "boards/board";
	private static final String VIEWS_NEW_BOARD = "boards/setDifficulty";

	@Autowired
	private BoardService boardService;

	@Autowired
	private PlayerService playerService;
	
	@GetMapping(path = "/game/**")
	public String board(ModelMap modelMap) {
		return VIEWS_BOARD;
	}
	
	@GetMapping(value = "/listinprogress")
	public String processFindFormProgress(ModelMap modelMap) {
		String vista = "boards/gamesListInProgress";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null)
			if (authentication.isAuthenticated()) {
				List<Board> board = boardService.findAllGamesInProgress(GameStatus.IN_PROGRESS);
				modelMap.addAttribute("board", board);
				return vista;
			} else {
				System.out.println("User not authenticated");
		}
		return "welcome";
		
	}
    
	@GetMapping(path="/list")
	public String processFindForm(ModelMap modelMap) {
		List<Board> board = boardService.findAllGamesNotInProgress(GameStatus.NONE);
		modelMap.addAttribute("board", board);
		return "boards/gamesList";
	}
	
	@GetMapping(value= "/listplayer")
	public String processFindFormPlayer(Board board, BindingResult result, Map<String, Object> model) {

		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		
	    List<Board> results = this.boardService.findAllGamesPlayer(currentUser.getUsername());
		model.put("board", results);
		return "boards/gamesListPlayer";
		
	}
	
	@GetMapping(value= "/statistics")
	public String statistics(Board board, BindingResult result, Map<String, Object> model) {
		
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User) authentication.getPrincipal();
		Integer nTotal = this.boardService.findnTotalGames();
		Integer gamesPlayerTotal = this.boardService.findnTotalGamesPlayer(currentUser.getUsername());
		Integer gamesPlayerTotalWon= this.boardService.findnTotalGamesPlayerWon(currentUser.getUsername(), GameStatus.WON);
		Integer gamesActivatedMines= this.boardService.findnTotalActivatedMines(currentUser.getUsername(), GameStatus.LOST);
		//no funciona bien
		Integer gamesPlacedFlags= this.boardService.findnTotalPlacedFlags(currentUser.getUsername());

		long timeTotalPlayed = this.boardService.totalDurationGamesPlayed();
		long minutes = timeTotalPlayed/60;
		long seconds = timeTotalPlayed%60;
		long timeTotalPlayer = this.boardService.totalDurationGamesPlayer(currentUser.getUsername());
		long minut = timeTotalPlayer/60;
		long secon = timeTotalPlayer%60;
		long averageTimePlayed = this.boardService.averageDurationGamesPlayed();
		long minu = averageTimePlayed/60;
		long seco = averageTimePlayed%60;
		long averageTimePlayer = this.boardService.averageDurationGamesPlayer(currentUser.getUsername());
		long min = averageTimePlayer/60;
		long sec = averageTimePlayer%60;
		long maxTimePlayed = this.boardService.maxDurationGamesPlayed();
		long mi = maxTimePlayed/60;
		long se = maxTimePlayed%60;
		long maxTimePlayer = this.boardService.maxDurationGamesPlayer(currentUser.getUsername());
		long m = maxTimePlayer/60;
		long s = maxTimePlayer%60;
		long minTimePlayed = this.boardService.minDurationGamesPlayed();
		long mm = minTimePlayed/60;
		long ss = minTimePlayed%60;
		long minTimePlayer = this.boardService.minDurationGamesPlayer(currentUser.getUsername());
		long mmm = minTimePlayer/60;
		long sss = minTimePlayer%60;
		long numWonPlayed = this.boardService.numGamesWonPlayed();
		long numLostPlayer = this.boardService.numGamesLostPlayer(currentUser.getUsername());
		long numLostPlayed = this.boardService.numGamesLostPlayed();
		long numGamesWinEasyPlayer = this.boardService.numGamesWinEasyPlayer(currentUser.getUsername());
		long numGamesWinMediumPlayer = this.boardService.numGamesWinMediumPlayer(currentUser.getUsername());
		long numGamesWinDifficultPlayer = this.boardService.numGamesWinDifficultPlayer(currentUser.getUsername());
		long numGamesWinEasyPlayed = this.boardService.numGamesWinEasyPlayed();
		long numGamesWinMediumPlayed = this.boardService.numGamesWinMediumPlayed();
		long numGamesWinDifficultPlayed = this.boardService.numGamesWinDifficultPlayed();

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
		return "boards/statistics";
		
	}
	
	@GetMapping("/rankings")
    public String showRanking(Map<String, Object> model){
		
			
			List<Player> players =  playerService.findAll();
            List<Board> games = boardService.gamesWonPlayer();

            List<Map.Entry<String, Integer>> list = boardService.ranking(players, games);
            List<Map.Entry<String, Integer>> listEasy = boardService.rankingEasy(players, games);
            List<Map.Entry<String, Integer>> listMed = boardService.rankingMedium(players, games);
            List<Map.Entry<String, Integer>> listDiff = boardService.rankingDifficult(players, games);
            
            model.put("player1", list.get(0).getKey()); model.put("gameswon1",list.get(0).getValue());
            model.put("player2", list.get(1).getKey()); model.put("gameswon2",list.get(1).getValue());
            model.put("player3", list.get(2).getKey()); model.put("gameswon3",list.get(2).getValue());
            
            model.put("playerEasy1", listEasy.get(0).getKey()); model.put("gameswonEasy1",listEasy.get(0).getValue());
            model.put("playerEasy2", listEasy.get(1).getKey()); model.put("gameswonEasy2",listEasy.get(1).getValue());
            model.put("playerEasy3", listEasy.get(2).getKey()); model.put("gameswonEasy3",listEasy.get(2).getValue());
            
            model.put("playerMed1", listMed.get(0).getKey()); model.put("gameswonMed1",listMed.get(0).getValue());
            model.put("playerMed2", listMed.get(1).getKey()); model.put("gameswonMed2",listMed.get(1).getValue());
            model.put("playerMed3", listMed.get(2).getKey()); model.put("gameswonMed3",listMed.get(2).getValue());
            
            model.put("playerDiff1", listDiff.get(0).getKey()); model.put("gameswonDiff1",listDiff.get(0).getValue());
            model.put("playerDiff2", listDiff.get(1).getKey()); model.put("gameswonDiff2",listDiff.get(1).getValue());
            model.put("playerDiff3", listDiff.get(2).getKey()); model.put("gameswonDiff3",listDiff.get(2).getValue());
            return "boards/ranking";
    }
	
	@GetMapping(value = "setDifficulty")
	public String setDifficulty() {
		return VIEWS_NEW_BOARD;
	}

}
