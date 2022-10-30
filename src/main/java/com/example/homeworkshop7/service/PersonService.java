package com.example.homeworkshop7.service;


import com.example.homeworkshop7.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonService extends UserDetailsService {

    Person createPerson(Person person);

    Person getPersonById(Integer id);

    List<Person> getPersons();

    Person updatePersonById(Integer id, Person person);

    void deletePersonById(Integer id);

    Person getPersonByUsername(String username);

    boolean savePerson(Person person);
}
