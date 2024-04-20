package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.*;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class OpenAIController {
    private final OpenAIService openAIService;
    private final NService nService;


    @Autowired
    public OpenAIController(OpenAIService openAIService, NService nService) {
        this.openAIService = openAIService;
        this.nService = nService;
    }

    @PostMapping("/chat")
    public String generateResponse(@RequestBody String prompt) {
        return openAIService.generateResponse(prompt);
    }

    @PostMapping("/image")
    public  GenerateTemplate generateImage(@RequestBody String prompt) {
        return openAIService.generateImage(prompt);
    }

    @GetMapping("/kin")
    public List<String> searchKin(@RequestBody String query) { return nService.searchKin(query);}

    @PostMapping("/one")
    public String iSearch(@RequestBody String query) throws JsonProcessingException {
        List<String> descriptions = nService.searchKin(query);
        List<GenerateTemplate> series = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for(String item : descriptions){
           series.add(openAIService.generateImage(openAIService.generateResponse(item)));
        }
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(series);

    }

}