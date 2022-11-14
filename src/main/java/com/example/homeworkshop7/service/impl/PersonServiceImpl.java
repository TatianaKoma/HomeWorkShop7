package com.example.homeworkshop7.service.impl;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Role;
import com.example.homeworkshop7.repository.PersonRepository;
import com.example.homeworkshop7.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.example.homeworkshop7.ResponseMessages.CONTENT;
import static com.example.homeworkshop7.ResponseMessages.PERSON_NOT_FOUND;
import static com.example.homeworkshop7.ResponseMessages.PERSON_NOT_FOUND_BY_USERNAME;
import static com.example.homeworkshop7.ResponseMessages.SUBJECT;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailServiceImpl mailService;

    @Override
    public Person createPerson(Person person) {
        log.debug("Get person {}", person);
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public boolean savePerson(Person person) {
        Person personFromDB = personRepository.findPersonByUsername(person.getUsername());
        if (personFromDB != null) {
            return false;
        }
        person.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        personRepository.save(person);
        if (personRepository.findById(person.getId()).isPresent()) {
            mailService.sendRegistrationEmail(person.getEmail(), SUBJECT, person.getName(), CONTENT);
        }
        return true;
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to get. Person with id {} was not found", id);
                    return new NotFoundException(String.format(PERSON_NOT_FOUND, id));
                });
    }

    @Override
    public Person updatePersonById(Integer id, Person person) {
        log.info("Updating Person with id {}", id);
        Person personForUpdate = personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to update. Person with id {} was not found", id);
                    return new NotFoundException(String.format(PERSON_NOT_FOUND, id));
                });
        personForUpdate.setName(person.getName());
        personForUpdate.setSurname(person.getSurname());
        personForUpdate.setEmail(person.getEmail());
        personRepository.save(personForUpdate);
        return personForUpdate;
    }

    @Override
    public void deletePersonById(Integer id) {
        log.info("Deleting Person with id {}", id);
        Person personForDelete = personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to delete. Person with id {} was not found", id);
                    return new NotFoundException(String.format(PERSON_NOT_FOUND, id));
                });
        personRepository.delete(personForDelete);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Person person = personRepository.findPersonByUsername(username);
        if (person == null) {
            log.error("Person with username {} was not found", username);
            throw new UsernameNotFoundException(String.format(PERSON_NOT_FOUND_BY_USERNAME, username));
        }
        return person;
    }
}
