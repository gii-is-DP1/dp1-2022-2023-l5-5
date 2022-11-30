package org.springframework.samples.petclinic.achievements;


import java.util.List;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;


@Controller
public class AchievementsController {
    
    
	private final AchievementsService achievementsService;

    @Autowired
	public AchievementsController(AchievementsService achievementsService, UserService userService, AuthoritiesService authoritiesService) {
		this.achievementsService = achievementsService;
	}
	

	//El admin ve el listado de logros
	@GetMapping(value = "/achievements/list")
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

	@GetMapping(value = "/achievements/list/")
	public String processFindFormPlayer(Achievement achievement, BindingResult result, Map<String, Object> model, 
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



}
