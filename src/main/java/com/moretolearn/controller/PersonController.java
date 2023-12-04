package com.moretolearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moretolearn.model.Person;
import com.moretolearn.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@PostMapping
	public Person savePerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}

	@GetMapping
	public List<Person> getPersons() {
		return personService.getPersons();
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable Integer id) {
		return personService.getPerson(id);
	}

	@PutMapping("/{id}")
	public Person updatePerson(@PathVariable Integer id, @RequestBody Person person) {
		return personService.updatePerson(id, person);
	}

	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable Integer id) {
		personService.deletePerson(id);
		return "Deleted";
	}

	@GetMapping("/save")
	public String saveData() {
		Person pm = new Person(106, "Narendra", 65);
		personService.savePerson(pm);
		return "";
	}

	@GetMapping("/data")
	public List<Person> getPersons1() {
		return personService.getPersons();
	}

	@GetMapping({ "/test", "/test/{data}", "/test/{data}/{d}" })
	public String getPersons1(@PathVariable(required = false) String data, @PathVariable(required = false) String d,
			@RequestParam(name = "id", required = false) Integer id) {
		System.err.println(data + " " + d + " " + id);
		return "";
	}

}
