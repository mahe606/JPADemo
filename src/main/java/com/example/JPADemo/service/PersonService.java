package com.example.JPADemo.service;

import com.example.JPADemo.dto.PersonDTO;
import com.example.JPADemo.entity.Person;
import com.example.JPADemo.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    ModelMapper mapper = new ModelMapper();




    public boolean savePerson(PersonDTO personDTO){
        Person newPerson = personRepository.save(mapper.map(personDTO,Person.class));
        if(null != newPerson && newPerson.getId() > 0)
            return true;
        else
            return false;
    }

    public List<PersonDTO> getAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(person -> {
                    PersonDTO personDTO = mapper.map(person, PersonDTO.class);
                    // Perform additional operations on personDTO
                    personDTO.setFullName(person.getFirstName() + " " + person.getLastName()); // Example of setting additional field
                    return personDTO;
                })
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id).get();
        PersonDTO personDTO = mapper.map(person,PersonDTO.class);
        personDTO.setFullName(person.getFirstName() + " " + person.getLastName());
        return personDTO;
    }
}
