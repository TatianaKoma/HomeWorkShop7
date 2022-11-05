package com.example.homeworkshop7.facade.impl;

import com.example.homeworkshop7.dto.PersonCreationDto;
import com.example.homeworkshop7.dto.PersonDto;
import com.example.homeworkshop7.facade.PersonFacade;
import com.example.homeworkshop7.mapper.PersonMapper;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonFacadeImpl implements PersonFacade {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Override
    public PersonDto createPerson(PersonCreationDto personCreationDto) {
        Person person = personMapper.toPerson(personCreationDto);
        Person createdPerson = personService.createPerson(person);
        return personMapper.toPersonDTO(createdPerson);
    }

    @Override
    public PersonDto getPersonById(Integer id) {

        return null;
    }

    @Override
    public List<PersonDto> getPersons() {
        List<Person> persons = personService.getPersons();
        List<PersonDto> personDto = persons.stream()
                .map(personMapper::toPersonDTO)
                .collect(Collectors.toList());
        return personDto;
    }

    @Override
    public PersonDto updatePersonById(Integer id, PersonDto personDto) {
        Person person = personMapper.toPerson(personDto);
        Person personUpdated = personService.updatePersonById(person.getId(), person);
        return personMapper.toPersonDTO(personUpdated);
    }

    @Override
    public void deletePersonById(Integer id) {
        personService.deletePersonById(id);
    }
}
