package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.TestRequest;
import com.mju.BackEnd.Dto.TestResponse;
import com.mju.BackEnd.Entity.Person;
import com.mju.BackEnd.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    @PostMapping("/users/save")
    public ResponseEntity<String> personSave(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("");
    }

    @PostMapping("/users/find")
    public ResponseEntity<?> personSearch(@RequestBody TestRequest testRequest) {
        Optional<Person> optionalPerson;

        if (testRequest.getId()>0) {
            optionalPerson = personRepository.findById(testRequest.getId());
        } else if (testRequest.getPersonName() != null) {
            optionalPerson = personRepository.findByPersonName(testRequest.getPersonName());
        } else if (testRequest.getAge() >0 ) {
            optionalPerson = personRepository.findByAge(testRequest.getAge());
        } else {
            return ResponseEntity.badRequest().body("Invalid request. Provide id, personName, or age.");
        }

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            TestResponse response = new TestResponse();
            response.setId(person.getId());
            response.setPersonName(person.getPersonName());
            response.setAge(person.getAge());

            ObjectMapper objectMapper = new ObjectMapper();
            String ret = "";
            try {
                ret = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

