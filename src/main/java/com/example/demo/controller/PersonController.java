package com.example.demo.controller;


import com.example.demo.dto.PersonDTO;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("")
    public String savePerson(@RequestBody PersonDTO person){
        System.out.println("Entered Person Controller");

       if(personService.savePerson(person))
            return "Success";
        else
            return "Failed to Save";
    }

    @GetMapping("")
    public List<PersonDTO> getAll(){
        System.out.println("Entered Person Controller");
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getAById(@PathVariable Long id){
        System.out.println("Entered Person Controller");
        return personService.findById(id);
    }


}
