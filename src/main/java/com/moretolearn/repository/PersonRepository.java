package com.moretolearn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moretolearn.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, Integer>{

}
