package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> persons= new ArrayList<Person>();
		Person person1= new Person();
		Person person2= new Person();
		Person person3= new Person();
		Person person4= new Person();
		Person person5= new Person();
		Person person6= new Person();
		person1.setFirstName("Paola");
		person2.setFirstName("Ángela");
		person3.setFirstName("Mercedes");
		person4.setFirstName("Pablo");
		person5.setFirstName("Andres");
		person6.setFirstName("Santiago");
		person1.setLastName(" Martin");
		person2.setLastName(" Bernal");		
		person3.setLastName(" Iglesias");
		person4.setLastName(" Quindos");
		person5.setLastName(" Garcia");
		person6.setLastName(" Zuleta");
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		persons.add(person5);
		persons.add(person6);
		model.put("persons", persons);
		model.put("tittle", "Buscaminas");
		model.put("group", "L5-5");
	    return "welcome";
	  }
}
