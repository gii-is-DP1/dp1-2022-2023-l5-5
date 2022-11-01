package org.springframework.samples.petclinic.admin;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final String ADMIN_FORM = "/admin/createOrUpdateAdminForm";

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

//	@Transactional(readOnly = true)
//	@GetMapping("/admin/{id}")
//	public ModelAndView showAdmin() {
//		ModelAndView mav = new ModelAndView("admin/adminDetails");
//		mav.addObject(this.adminService.findAdmin());
//		return mav;
//	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Admin admin = new Admin(); //Objeto administrador
		model.put("admin", admin); //Meter en la vista el objeto admin, y en la vista busco por el nombre entre ""
		return ADMIN_FORM;
	}
	
	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Admin admin, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return ADMIN_FORM;
		}
		else if (adminService.validator(admin) == true) {
			model.addAttribute("message", "Este usuario ya está escogido");
			return ADMIN_FORM;
		}
		else {
			//creating owner, user and authorities
			this.adminService.saveAdmin(admin);
			
			return "redirect:/";
		}
	}
//
//	
//	@Transactional(readOnly = true)
//	@GetMapping(value = "/edit/{id}")
//	public String initUpdateAdminForm(Model model) {
//		Admin admin = this.adminService.findAdmin();
//		model.addAttribute(admin);
//		return ADMIN_FORM;
//	}
//	
//	@Transactional
//	@PostMapping(value = "/edit/{id}")
//	public String processUpdateAdminForm(@Valid Admin admin, BindingResult result,
//			@PathVariable("adminId") int adminId) {
//		if (result.hasErrors()) {
//			return ADMIN_FORM;
//		} else {
//			admin.setId(adminId);
//			this.adminService.saveAdmin(admin);
//	
//			return "redirect:/admin";
//		}
//	}
	



}
