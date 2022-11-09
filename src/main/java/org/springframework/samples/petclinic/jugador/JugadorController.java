package org.springframework.samples.petclinic.jugador;

import java.util.Map;

import javax.validation.Valid;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JugadorController {
    
    private static final String VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM = "jugadores/createOrUpdateJugadoresForm";

	@Autowired
	private final JugadorService jugadorService;

    @Autowired
	public JugadorController(JugadorService jugadorService, UserService userService, AuthoritiesService authoritiesService) {
		this.jugadorService = jugadorService;
	}

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	


    

	@GetMapping(value = "/jugadores")
	public String processFindForm(Jugador jugador, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (jugador.getNombreUsuario() == null) {
			jugador.setNombreUsuario("");; // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Jugador> results = this.jugadorService.findAll();
		
			// multiple owners found
			model.put("selections", results);
			return "jugadores/jugadoresList";
		
	}


	@GetMapping(value = "/jugadores/{jugadorId}/edit")
	public String initUpdateJugadorForm(@PathVariable("jugadorId") int jugadorId, Model model) {
		Jugador jugador = this.jugadorService.findJugadorById(jugadorId);
		model.addAttribute(jugador);
		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/jugadores/{jugadorId}/edit")
	public String processUpdateJugadorForm(@Valid Jugador jugador, BindingResult result,
			@PathVariable("jugadorId") int jugadorId) {
		if (result.hasErrors()) {
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			jugador.setId(jugadorId);
			this.jugadorService.saveJugador(jugador);
			return "redirect:/jugadores/{jugadorId}";
		}
	}

	



    @GetMapping("/jugadores/{jugadorId}")
	public ModelAndView showJugador(@PathVariable("jugadorId") int jugadorId) {
		ModelAndView mav = new ModelAndView("jugadores/jugadoresDetails");
		mav.addObject(this.jugadorService.findJugadorById(jugadorId));
		return mav;
	}
}
