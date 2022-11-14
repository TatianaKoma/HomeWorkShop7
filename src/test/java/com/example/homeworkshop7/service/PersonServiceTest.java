package com.example.homeworkshop7.service;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.repository.PersonRepository;
import com.example.homeworkshop7.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PersonServiceTest {
    private final Person PERSON = new Person(1, "Tom", "Ford", "tford@gmail.com",
            "tom", "123", "123", new ArrayList<>(), new HashSet<>());

    @InjectMocks
    private PersonServiceImpl service;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPersonWhenCreate() {
        when(personRepository.save(PERSON)).thenReturn(PERSON);
        Person createdPerson = service.createPerson(PERSON);

        assertNotNull(createdPerson);
        assertEquals(PERSON, createdPerson);
    }

    @Test
    void shouldReturnListPersonsWhenGetPersons() {
        Person person1 = new Person(2, "Anna", "Grand", "agrand@gmail.com",
                "anna", "456", "456", new ArrayList<>(), new HashSet<>());
        Person person2 = new Person(3, "Elena", "Stone", "estone@gmail.com",
                "elena", "789", "789", new ArrayList<>(), new HashSet<>());
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(PERSON);

        when(personRepository.findAll()).thenReturn(persons);
        List<Person> actualPersons = service.getPersons();

        assertNotNull(actualPersons);
        assertEquals(persons, actualPersons);
    }

    @Test
    void shouldReturnPersonWhenGetById() {
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(PERSON));
        Person actualPerson = service.getPersonById(PERSON.getId());

        assertNotNull(actualPerson);
        assertThat(actualPerson).isSameAs(PERSON);
        verify(personRepository).findById(PERSON.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetByIdNotFoundPerson() {
        when(personRepository.findById(PERSON.getId())).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.getPersonById(PERSON.getId()));
        assertNotNull(thrown);
        verify(personRepository).findById(PERSON.getId());
    }

    @Test
    void ShouldReturnUpdatedPersonWhenUpdate() {
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(PERSON));
        when(personRepository.save(PERSON)).thenReturn(PERSON);
        Person actualPerson = service.updatePersonById(PERSON.getId(), PERSON);

        assertThat(actualPerson).isSameAs(PERSON);
        verify(personRepository).findById(PERSON.getId());
        verify(personRepository).save(PERSON);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdateNotFoundPerson() {
        when(personRepository.findById(PERSON.getId())).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.updatePersonById(PERSON.getId(), PERSON));
        assertNotNull(thrown);
        verify(personRepository).findById(PERSON.getId());
    }

    @Test
    void shouldDeleteWhenDeleteById() {
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(PERSON));
        service.deletePersonById(PERSON.getId());

        verify(personRepository).delete(PERSON);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenDeleteByIdNotFoundPerson() {
        when(personRepository.findById(PERSON.getId())).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.deletePersonById(PERSON.getId()));
        assertNotNull(thrown);
        verify(personRepository).findById(PERSON.getId());
    }

    @Test
    void shouldReturnUserDetailsWhenLoadUserByUsername() {
        when(personRepository.findPersonByUsername(PERSON.getUsername())).thenReturn(PERSON);

        UserDetails userDetails = service.loadUserByUsername(PERSON.getUsername());
        assertNotNull(userDetails);
        assertThat(userDetails).isSameAs(PERSON);
        verify(personRepository).findPersonByUsername(PERSON.getUsername());
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionWhenLoadUserByUsernameIsNull() {
        when(personRepository.findPersonByUsername(PERSON.getUsername())).thenReturn(null);
        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername(PERSON.getUsername()));
        assertNotNull(thrown);
    }
}
