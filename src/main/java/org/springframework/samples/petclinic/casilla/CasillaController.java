package org.springframework.samples.petclinic.casilla;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CasillaController {



    @Autowired
	private final CasillaService casillaService;

    @Autowired
	public CasillaController(CasillaService casillaService, AuthoritiesService authoritiesService) {
		this.casillaService = casillaService;
	}

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


    @GetMapping(value = "/casillas")
	public String processFindForm(Casilla casilla, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (casilla.getNumMinasAdyacentes()==null) {
			casilla.setNumMinasAdyacentes(null); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Casilla> results = this.casillaService.findAll();
		
			// multiple owners found
			model.put("selections", results);
			return "casillas/casillasList";
		
	}



    @GetMapping("/casillas/{casillaId}")
	public ModelAndView showCasilla(@PathVariable("casillaId") int casillaId) {
		ModelAndView mav = new ModelAndView("casillas/casillasDetails");
		mav.addObject(this.casillaService.findCasillaById(casillaId));
		return mav;
	}



    @GetMapping("/casillas/{coordenadaX}/{coordenadaY}")
    public ModelAndView initUpdateCasillaForm(@PathVariable("coordenadaX") int coordenadaX, @PathVariable("coordenadaY") int coordenadaY){
       ModelAndView mav = new ModelAndView("casillas/casillasDetails");
		mav.addObject(this.casillaService.findCasillaByPosition(coordenadaX, coordenadaY));
		return mav;
    }




}
