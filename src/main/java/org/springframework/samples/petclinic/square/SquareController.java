package org.springframework.samples.petclinic.square;

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
public class SquareController {



    @Autowired
	private final SquareService squareService;

    @Autowired
	public SquareController(SquareService squareService, AuthoritiesService authoritiesService) {
		this.squareService = squareService;
	}

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


    @GetMapping(value = "/squares")
	public String processFindForm(Square square, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (square.getNumMinasAdyacentes()==null) {
			square.setNumMinasAdyacentes(null); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Square> results = this.squareService.findAll();
		
			// multiple owners found
			model.put("selections", results);
			return "squares/squaresList";
		
	}



    @GetMapping("/squares/{squareId}")
	public ModelAndView showsquare(@PathVariable("squareId") int squareId) {
		ModelAndView mav = new ModelAndView("squares/squaresDetails");
		mav.addObject(this.squareService.findSquareById(squareId));
		return mav;
	}



    @GetMapping("/squares/{coordenadaX}/{coordenadaY}")
    public ModelAndView initUpdateSquareForm(@PathVariable("coordenadaX") int coordenadaX, @PathVariable("coordenadaY") int coordenadaY){
       ModelAndView mav = new ModelAndView("squares/squaresDetails");
		mav.addObject(this.squareService.findSquareByPosition(coordenadaX, coordenadaY));
		return mav;
    }




}
