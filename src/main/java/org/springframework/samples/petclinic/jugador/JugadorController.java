package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/jugador")
public class JugadorController {
    
    private static final String VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM = "jugadores/createOrUpdateJugadorForm";

	private final JugadorService jugadorService;

    @Autowired
	public JugadorController(JugadorService jugadorService, UserService userService, AuthoritiesService authoritiesService) {
		this.jugadorService = jugadorService;
	}

	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Jugador jugador = new Jugador(); //Objeto juagador
		model.put("jugador", jugador); //Meter en la vista el objeto jugador, y en la vista busco por el nombre entre ""
		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Jugador jugador, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}
		else if (jugadorService.validator(jugador) == true) {
			model.addAttribute("message", "Este usuario ya está escogido");
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.jugadorService.saveJugador(jugador);
			
			return "redirect:/";
		}
	}

//	@GetMapping(value = "/jugadores")
//	public String processFindForm(Jugador jugador, BindingResult result, Map<String, Object> model) {
//
//		// allow parameterless GET request for /owners to return all records
//		if (jugador.getUser() == null) {
//			jugador.setUser(null); // empty string signifies broadest possible search
//		}
//
//		// find owners by last name
//		Collection<Jugador> results = this.jugadorService.findAll();
//		
//			// multiple owners found
//			model.put("selections", results);
//			return "jugadores/jugadoresList";
//		
//	}
//
//
//	@GetMapping(value = "/jugadores/{jugadorId}/edit")
//	public String initUpdateJugadorForm(@PathVariable("jugadorId") int jugadorId, Model model) {
//		Jugador jugador = this.jugadorService.findJugadorById(jugadorId);
//		model.addAttribute(jugador);
//		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
//	}
//
//	@PostMapping(value = "/jugadores/{jugadorId}/edit")
//	public String processUpdateJugadorForm(@Valid Jugador jugador, BindingResult result,
//			@PathVariable("jugadorId") int jugadorId) {
//		if (result.hasErrors()) {
//			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//			jugador.setId(jugadorId);
//			this.jugadorService.saveJugador(jugador);
//			return "redirect:/jugadores/{jugadorId}";
//		}
//	}
//
//    @GetMapping("/jugadores/{jugadorId}")
//	public ModelAndView showJugador(@PathVariable("jugadorId") int jugadorId) {
//		ModelAndView mav = new ModelAndView("jugadores/jugadoresDetails");
//		mav.addObject(this.jugadorService.findJugadorById(jugadorId));
//		return mav;
//	}

	@GetMapping(value = "/list")
	public String processFindForm(Jugador jugador, BindingResult result, Map<String, Object> model) {

		List<Jugador> results = this.jugadorService.findAllJugador();
		
			// multiple owners found
			model.put("selections", results);
			return "jugadores/jugadoresList";
		
	}
}
