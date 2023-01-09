package org.springframework.samples.petclinic.achievements;


import java.util.Collection;
import java.util.List;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.board.GameStatus;
import org.springframework.samples.petclinic.player.Player;
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
import org.springframework.data.web.SortDefault.SortDefaults;


@Controller
@RequestMapping("/achievements")
public class AchievementsController {
    
    
	private final AchievementsService achievementsService;

	private static final String VIEWS_ACHIEVEMENT_CREATE_FORM = "achievements/createAchievementsForm";

	@Autowired
	private BoardService boardService;

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

			return "/achievements/achievementsList";
	}
	
	@GetMapping(value = "/myprofile")
	public String showPlayerAchievements(Player player, BindingResult result, Map<String, Object> model,
	@PageableDefault(page = 0, size = 6) @SortDefault.SortDefaults({
		@SortDefault(sort = "title", direction = Sort.Direction.ASC),
		@SortDefault(sort = "id", direction = Sort.Direction.ASC), }) Pageable pageable) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();

		
		Integer numResults = this.achievementsService.countAllAchievements();
		Integer page = 0;
		List<Achievement> results = this.achievementsService.findAllAchievements(page, pageable);


		// multiple players found

		    model.put("pageNumber", pageable.getPageNumber());
		    model.put("hasPrevious", pageable.hasPrevious());
			Double totalPages = Math.ceil(numResults / (pageable.getPageSize()));
			model.put("totalPages", totalPages);
			model.put("selections", results);
		return "achievements/playerAchievements";
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



}
