package com.moretolearn.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moretolearn.model.Person;
import com.moretolearn.service.PersonService;

@WebMvcTest(PersonController.class)
class PersonControllerTest1 {

	@MockBean
	PersonService personService;

	@InjectMocks
	PersonController personController;

//	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	@Test
	public void test_savePerson() throws Exception {
		Person person = new Person(100, "Ram", 90);
		when(personService.savePerson(person)).thenReturn(person);
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(person))).andExpect(status().isOk());
	}

	@Test
	public void test_getPerson() throws Exception {
		Person person = new Person(100, "Ram", 90);
		when(personService.getPerson(100)).thenReturn(person);
		mockMvc.perform(get("/person/{id}", 100).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.persons[*].id").isNotEmpty())
				.andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.name").value("Ram"));
	}

	@Test
	public void test_getPersons() throws Exception {
		List<Person> personList = Arrays.asList(new Person(110, "Ram", 88), new Person(101, "Ram", 88));
		when(personService.getPersons()).thenReturn(personList);
		mockMvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Ram"));
	}

	@Test
	public void test_updatePerson() throws Exception {
		Person person = new Person(100, "Ram", 90);
		when(personService.updatePerson(100,person)).thenReturn(person);
		mockMvc.perform(put("/person/{id}",100).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(person))).andExpect(status().isOk());
	}
	
	@Test
	public void test_deletePerson() throws Exception {
		doNothing().when(personService).deletePerson(111);
		mockMvc.perform(delete("/person/{id}", 111).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(111))).andExpect(status().isOk());
	}
}
