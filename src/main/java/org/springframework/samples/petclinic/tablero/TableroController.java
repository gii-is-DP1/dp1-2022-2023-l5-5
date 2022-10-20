package org.springframework.samples.petclinic.tablero;


import java.util.Map;


import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TableroController {
    

	@Autowired
	private final TableroService tableroService;

    @Autowired
	public TableroController(TableroService tableroService, AuthoritiesService authoritiesService) {
		this.tableroService = tableroService;
	}

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	


    

	@GetMapping(value = "/tableros")
	public String processFindForm(Tablero tablero, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (tablero.getNumeroMinas()==null) {
			tablero.setNumeroMinas(0); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Tablero> results = this.tableroService.findAll();
		
			// multiple owners found
			model.put("selections", results);
			return "tableros/tablerosList";
		
	}

	@GetMapping("/tableros/{tableroId}")
	public ModelAndView showTablero(@PathVariable("tableroId") int tableroId) {
		ModelAndView mav = new ModelAndView("tableros/tablerosDetails");
		mav.addObject(this.tableroService.findJugadorById(tableroId));
		return mav;
	}

	
	

}
