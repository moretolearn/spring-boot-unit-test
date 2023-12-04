package com.moretolearn.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.moretolearn.model.Person;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataMongoTest
//@AutoConfigureDataMongo
class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

//	@Autowired
//	private TestEntityManager entityManager;

	@Test
	public void test_savePerson() {
		Person person = new Person(120, "Ram", 90);
		Person savePerson = personRepository.save(person);
		assertEquals(person, savePerson);
	}

	@Test
	public void test_getPerson() {
		Person person = personRepository.findById(120).get();
		assertEquals("Ram", person.getName());
	}

	@Test
	public void test_getPersons() {
//		List<Person> personList = Arrays.asList(new Person(110, "Ram", 88), new Person(101, "Ram", 88));
		int size = personRepository.findAll().size();
		assertEquals(5, size);

	}

	@Test
	public void test_deletePerson() {
		personRepository.deleteById(111);
	}

}
