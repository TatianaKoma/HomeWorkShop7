package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.PersonCreationDto;
import com.example.homeworkshop7.dto.PersonDto;
import com.example.homeworkshop7.facade.impl.PersonFacadeImpl;
import com.example.homeworkshop7.mapper.PersonMapper;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PersonFacadeTest {
    private final Person PERSON = new Person(1, "Tom", "Ford", "tford@gmail.com",
            "tom", "123", "123", new ArrayList<>(), new HashSet<>());
    private final Cart CART = new Cart(1, PERSON, new BigDecimal(0), new ArrayList<>());
    private final Shop SHOP = new Shop(1, "Market", new ArrayList<>());
    private final PersonDto PERSON_DTO = new PersonDto(1, "Tom", "Ford",
            "tford@gmail.com", "tom", "111", "111");

    @InjectMocks
    private PersonFacadeImpl personFacade;

    @Mock
    private PersonService personService;

    @Mock
    private PersonMapper personMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPersonDtoWhenCreatePerson() {
        PersonCreationDto personCreationDto = new PersonCreationDto("Tom", "Ford",
                "tford@gmail.com", "tom", "111");
        when(personMapper.toPerson(personCreationDto)).thenReturn(PERSON);
        when(personMapper.toPersonDTO(PERSON)).thenReturn(PERSON_DTO);
        when(personService.createPerson(PERSON)).thenReturn(PERSON);
        PersonDto createdPersonDto = personFacade.createPerson(personCreationDto);
        assertEquals(PERSON_DTO, createdPersonDto);
    }

    @Test
    void shouldReturnListPersonDtoWhenGetPersons() {
        Person person = new Person(2, "Emma", "Grand", "egrand@gmail.com",
                "emma", "777", "777", new ArrayList<>(), new HashSet<>());
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(PERSON);

        when(personService.getPersons()).thenReturn(persons);
        when(personMapper.toPersonDTO(PERSON)).thenReturn(PERSON_DTO);
        List<PersonDto> actualListPersons = personFacade.getPersons();
        List<PersonDto> personDtos = persons.stream()
                .map(personMapper::toPersonDTO)
                .collect(Collectors.toList());
        assertEquals(personDtos, actualListPersons);
    }

    @Test
    void shouldReturnPersonDtoWhenUpdatePersonById() {
        PersonDto personDtoForUpdate = new PersonDto(1, "Emma", "Grand", "egrand@gmail.com",
                "emma", "777", "777");
        Person updatedPerson = new Person(1, "Emma", "Grand", "egrand@gmail.com",
                "emma", "777", "777", new ArrayList<>(), new HashSet<>());
        PersonDto expectedPersonDto = new PersonDto(1, "Emma", "Grand", "egrand@gmail.com",
                "emma", "777", "777");

        when(personMapper.toPerson(personDtoForUpdate)).thenReturn(updatedPerson);
        when(personMapper.toPersonDTO(updatedPerson)).thenReturn(expectedPersonDto);
        when(personService.updatePersonById(updatedPerson.getId(), updatedPerson)).thenReturn(updatedPerson);
        PersonDto actualUpdatedPersonDto = personFacade.updatePersonById(updatedPerson.getId(), personDtoForUpdate);
        assertEquals(expectedPersonDto, actualUpdatedPersonDto);
    }

    @Test
    void shouldDeleteWhenDeletePersonById() {
        personFacade.deletePersonById(PERSON.getId());
        verify(personService).deletePersonById(PERSON.getId());
    }
}
