package org.springframework.samples.petclinic.achievements;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;


@Controller
@RequestMapping("/achievements")
public class AchievementsController {
    
    
	private final AchievementsService achievementsService;

	private static final String VIEWS_ACHIEVEMENT_CREATE_FORM = "achievements/createAchievementsForm";
    private static final String VIEWS_ACHIEVEMENT_UPDATE_FORM = "achievements/updateAchievementsForm";
    private static final String VIEWS_ACHIEVEMENT_LIST = "achievements/achievementsList";
    private static final String VIEWS_ACHIEVEMENT_LIST_PLAYER = "achievements/playerAchievements";
    private static final String VIEWS_ACHIEVEMENTS_DELETE_ADMIN = "achievements/achievementsDelete";

	@Autowired
	private StatisticsService statisticsService;

    @Autowired
	public AchievementsController(AchievementsService achievementsService, UserService userService, AuthoritiesService authoritiesService) {
		this.achievementsService = achievementsService;
	}
	

	//El admin ve el listado de logros
	@GetMapping(value = "/list")
	public String processFindForm(Achievement achievement, BindingResult result, Map<String, Object> model, 
		@PageableDefault(page = 0, size = 6) @SortDefault.SortDefaults({
		@SortDefault(sort = "title", direction = Sort.Direction.ASC),
		@SortDefault(sort = "id", direction = Sort.Direction.ASC), }) Pageable pageable) {

		Integer numResults = this.achievementsService.countAllAchievements();
		Integer page = 0;
		List<Achievement> results = this.achievementsService.findAllAchievements(page, pageable);
		// multiple players found
		model.put("pageNumber", pageable.getPageNumber());
		model.put("hasPrevious", pageable.hasPrevious());
		Double totalPages = Math.ceil(numResults / (pageable.getPageSize()));
		model.put("totalPages", totalPages);
		model.put("selections", results);

		return VIEWS_ACHIEVEMENT_LIST;
	}
	
	@GetMapping(value = "/myprofile")
	public String showPlayerAchievements(Player player, BindingResult result, Map<String, Object> model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();

		List<Achievement> results = this.achievementsService.findAllAchievements();
		List<Achievement> list = new ArrayList<Achievement>();
		List<Achievement> list2 = this.achievementsService.findAllAchievements();

		Integer i=0;
		while(i!=results.size()-1){
		
			Boolean res= false;
			if(results.get(i).getAchievementType().getId()==1){
				Integer query = (int) this.statisticsService.findnTotalGamesPlayerWon(currentUser.getUsername());
				Integer number=results.get(i).getNumber();
				res= query>=number;
				if(res==true){
					list.add(results.get(i));
					list2.remove(results.get(i));
				}
			}else if(results.get(i).getAchievementType().getId()==2){
				Integer query = (int) this.statisticsService.findnTotalGamesPlayer(currentUser.getUsername());
				Integer number=results.get(i).getNumber();
				res= query>=number;
				if(res==true){
					list.add(results.get(i));
					list2.remove(results.get(i));
				}
			}else if(results.get(i).getAchievementType().getId()==4){
				Integer query = (int) this.statisticsService.numGamesWinEasyPlayer(currentUser.getUsername());
				Integer number=results.get(i).getNumber();
				res= query>=number;
				if(res==true){
					list.add(results.get(i));
					list2.remove(results.get(i));
				}
			}else if(results.get(i).getAchievementType().getId()==5){
				Integer query = (int) this.statisticsService.numGamesWinDifficultPlayer(currentUser.getUsername());
				Integer number=results.get(i).getNumber();
				res= query>=number;
				if(res==true){
					list.add(results.get(i));
					list2.remove(results.get(i));
				}
			}else if(results.get(i).getAchievementType().getId()==6){
				Integer query = (int) this.statisticsService.numGamesWinMediumPlayer(currentUser.getUsername());
				Integer number=results.get(i).getNumber();
				res= query>=number;
				if(res==true){
					list.add(results.get(i));
					list2.remove(results.get(i));
				}
			}else if(results.get(i).getAchievementType().getId()==11){
				Integer query = (int) this.statisticsService.totalDurationGamesPlayer(currentUser.getUsername());
				Integer number=results.get(i).getNumber();
				res= query>=number;
				if(res==true){
					list.add(results.get(i));
					list2.remove(results.get(i));
				}
			}
			i++;
		}
		// multiple players found
			model.put("selections", list);
			model.put("selections2", list2);
			model.put("player", currentUser.getUsername());
		return VIEWS_ACHIEVEMENT_LIST_PLAYER;
	}

	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Achievement achievement = new Achievement(); 
		model.put("achievement", achievement); 
		return VIEWS_ACHIEVEMENT_CREATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Achievement achievement, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_ACHIEVEMENT_CREATE_FORM;
		}
		else {
			this.achievementsService.saveAchievement(achievement);
			
			return "redirect:/achievements/list";
		}
	}

	@ModelAttribute("achievementtypes")
    public Collection<AchievementType> populateAchievementTypes(){
        return this.achievementsService.findAllAchievementTypes();
    }
	
	//El admin edita un logro
	@GetMapping(value = "/{id}/edit")
	public String initUpdateAchievementForm(@PathVariable("id") int id, Model model) {
		Achievement achievement = this.achievementsService.getAchievementById(id).get();
		model.addAttribute(achievement);
		return VIEWS_ACHIEVEMENT_UPDATE_FORM;
	}
	
	@PostMapping(value = "/{id}/edit")
	public String processUpdateAchievementForm(@Valid Achievement achievement, BindingResult result, @PathVariable("id") int id, ModelMap model) {
		if (result.hasErrors()) {
			model.put("achievement", achievement);
			return VIEWS_ACHIEVEMENT_UPDATE_FORM;
		} else {
			Achievement achievementToUpdate = this.achievementsService.getAchievementById(id).get();
			BeanUtils.copyProperties(achievement, achievementToUpdate, "id", "creator", "createdDate");
			this.achievementsService.saveAchievement(achievementToUpdate);	
			return "redirect:/achievements/list";
		}
	}
	
	//El admin elimina un logro
	@GetMapping(value = "/{id}/delete")
	public String redirectDelete(@PathVariable("id") Integer id, ModelMap model) {
		Achievement achievement = this.achievementsService.getAchievementById(id).get();
		model.addAttribute(achievement);
		return VIEWS_ACHIEVEMENTS_DELETE_ADMIN;
	}
		
	//Confirmación de eliminar para un admin
	@GetMapping(value = "/{id}/deleteConfirm")
	public String deleteAchievementAdmin(@PathVariable("id") Integer id) {
		this.achievementsService.deleteAchievement(id);	
		return "redirect:/achievements/list";
	}

}
