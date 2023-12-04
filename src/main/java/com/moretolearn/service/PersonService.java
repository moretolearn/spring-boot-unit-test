package com.moretolearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moretolearn.model.Person;
import com.moretolearn.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> getPersons() {
		return personRepository.findAll();
	}

	public Person getPerson(Integer id) {
		return personRepository.findById(id).get();
	}

	public Person updatePerson(Integer id, Person person) {
		return personRepository.save(person);
	}

	public void deletePerson(Integer id) {
		personRepository.deleteById(id);
	}
}
