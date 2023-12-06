package com.moretolearn.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.moretolearn.model.Person;
import com.moretolearn.repository.PersonRepository;

@SpringBootTest
class PersonServiceTest1 {

	@InjectMocks
	PersonService personService;

	@MockBean
	PersonRepository personRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_savePerson() {
		Person person = new Person(116, "Ram", 88);
		when(personRepository.save(person)).thenReturn(person);
		assertEquals(person, personService.savePerson(person));
	}

	@Test
	public void test_getPerson() {
		Person person = new Person(114, "Ram", 88);
		when(personRepository.findById(114)).thenReturn(Optional.of(person));
		assertEquals(88, personService.getPerson(114).getAge());
	}

	@Test
	public void test_getPersons() {
		List<Person> personList = Arrays.asList(new Person(110, "Ram", 88), new Person(101, "Ram", 88));
		when(personRepository.findAll()).thenReturn(personList);
		assertEquals(2, personService.getPersons().size());
	}
	
	@Test
	public void test_updatePerson() {
		Person person = new Person(114, "Ram", 88);
		when(personRepository.findById(114)).thenReturn(Optional.of(person));
		Person personById = personRepository.findById(114).get();
		personById.setName("Bhagi");
		personById.setAge(100);
		when(personRepository.save(personById)).thenReturn(personById);
		assertEquals(personById, personService.updatePerson(114, personById));
	}

	@Test
	public void test_deletePerson() {
		personService.deletePerson(111);
		verify(personRepository, times(1)).deleteById(111);
	}

}
