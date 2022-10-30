package com.example.homeworkshop7.mapper;
import com.example.homeworkshop7.dto.PersonCreationDto;
import com.example.homeworkshop7.dto.PersonDto;
import com.example.homeworkshop7.dto.PersonRegistrationDto;
import com.example.homeworkshop7.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonMapper {

    public Person toPerson(PersonCreationDto personCreationDTO) {
        Person person = new Person();
        person.setName(personCreationDTO.getName());
        person.setSurname(personCreationDTO.getSurname());
        person.setEmail(personCreationDTO.getEmail());
        person.setUsername(personCreationDTO.getUsername());
        person.setPassword(personCreationDTO.getPassword());
        return person;
    }

    public Person toPerson(PersonRegistrationDto personRegistrationDto) {
        Person person = new Person();
        person.setName(personRegistrationDto.getName());
        person.setSurname(personRegistrationDto.getSurname());
        person.setEmail(personRegistrationDto.getEmail());
        person.setUsername(personRegistrationDto.getUsername());
        person.setPassword(personRegistrationDto.getPassword());
        person.setPasswordConfirm(personRegistrationDto.getPasswordConfirm());
        return person;
    }

    public Person toPerson(PersonDto personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setEmail(personDTO.getEmail());
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());
        person.setPasswordConfirm(person.getPasswordConfirm());
        return person;
    }

    public PersonDto toPersonDTO(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getSurname(), person.getEmail(),
                person.getUsername(), person.getPassword(), person.getPasswordConfirm());
    }
}
