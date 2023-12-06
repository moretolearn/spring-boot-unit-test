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
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.moretolearn.model.Person;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataMongoTest
//@AutoConfigureDataMongo
class PersonRepositoryTest1 {

	@Autowired
//	@MockBean
	private PersonRepository personRepository;

//	@Autowired
//	private TestEntityManager entityManager;
	
//	@BeforeEach
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}

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
	public void test_updatePerson() {
		Person personById = personRepository.findById(120).get();
		personById.setName("Bhagi");
		personById.setAge(100);
		Person updatePerson = personRepository.save(personById);
		assertEquals(personById, updatePerson);
	}

	@Test
	public void test_deletePerson() {
		personRepository.deleteById(111);
	}

}
