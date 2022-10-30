package com.example.homeworkshop7.repository;

import com.example.homeworkshop7.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonByUsername(String username);
}
