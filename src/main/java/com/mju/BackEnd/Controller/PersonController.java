package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.TestRequest;
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

    @PostMapping("/users/favorites")
    public ResponseEntity<?> personSearch(@RequestBody TestRequest id) {

        ObjectMapper objectMapper = new ObjectMapper();
        String ret="";
        try {
            ret = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret);
    }
}

