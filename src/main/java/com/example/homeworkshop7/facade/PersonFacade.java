package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.PersonCreationDto;
import com.example.homeworkshop7.dto.PersonDto;

import java.util.List;

public interface PersonFacade {
    PersonDto createPerson(PersonCreationDto personCreationDto);

    PersonDto getPersonById(Integer id);

    List<PersonDto> getPersons();

    PersonDto updatePersonById(Integer id, PersonDto personDto);

    void deletePersonById(Integer id);
}
