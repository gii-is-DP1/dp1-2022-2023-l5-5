package org.springframework.samples.petclinic.juego;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.juego.Juego;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/juego")
public class JuegoController {
	
	@Autowired 
	private JuegoService juegoService;
	
	private static final String VIEWS_JUEGO_CREATE_OR_UPDATE_FORM = "juegos/createOrUpdateJuegoForm";


	public JuegoController(JuegoService juegoService) {
		this.juegoService = juegoService;
	}
	
	@GetMapping(path = "/create")
	public String initCreationForm(ModelMap modelMap) {
		String view = VIEWS_JUEGO_CREATE_OR_UPDATE_FORM;
		modelMap.addAttribute("juego", new Juego()); //Pongo addAttribute o put?
		return view;
	}
	
	@PostMapping(path="/create")
	public String processCreationForm(@Valid Juego juego, BindingResult result, ModelMap modelMap) {
		String view ="welcome";
		if(result.hasErrors()) {
			modelMap.addAttribute("juego", juego);
			return VIEWS_JUEGO_CREATE_OR_UPDATE_FORM;
		}else {
			//creating card
			juegoService.save(juego);
			modelMap.addAttribute("message", "Juego sucessfully saved!");
		}
		return view;
	}

}
