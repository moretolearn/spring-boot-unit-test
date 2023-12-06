package com.moretolearn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.moretolearn.model.Person;
import com.moretolearn.repository.PersonRepository;
import com.moretolearn.service.PersonService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringBootUnitTestApplicationTests {
	
	@LocalServerPort
	private int localPort;
	
	@InjectMocks
	PersonService personService;
	
	@MockBean
	PersonRepository personRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Get-Persons")
	void getPersons() {
		when(personRepository.findAll()).thenReturn(Stream.of(new Person(103, "Narendra", 65)).collect(Collectors.toList()));
		assertEquals(1, personService.getPersons().size());
		assertEquals(Stream.of(new Person(103, "Narendra", 65)).collect(Collectors.toList()), personService.getPersons());
	}

}
