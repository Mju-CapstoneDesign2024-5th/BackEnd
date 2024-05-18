package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.*;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

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
    private final ObjectMapper objectMapper;


    @Autowired
    public OpenAIController(OpenAIService openAIService, NService nService,ObjectMapper objectMapper) {
        this.openAIService = openAIService;
        this.nService = nService;
        this.objectMapper = objectMapper;
    }

/*
    @PostMapping("/chat")
    public String generateResponse(@RequestBody String prompt) {
        return openAIService.generateResponse(prompt);
    }

    @PostMapping("/image")
    public  GenerateTemplate generateImage(@RequestBody String prompt) {
        return openAIService.generateImage(prompt);
    }
*/

    @GetMapping("/kin")
    public List<String> searchKin(@RequestBody SearchRequest request) {
        String query = request.getQuery();
        String sort = request.getSort();
        return nService.searchKin(query, sort);
    }
    @PostMapping("/search")
    public Mono<ResponseEntity<String>> iSearch(@RequestBody SearchRequest request) {
        String query = request.getQuery();
        String sort = request.getSort();

        return Flux.fromIterable(nService.searchKin(query, sort))
                .flatMap(description -> openAIService.generateResponse(description)
                        .flatMap(response -> openAIService.generateImage(response)))
                .collectList()
                .map(series -> {
                    try {
                        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(series);
                        return ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(jsonResponse);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error processing JSON", e);
                    }
                });
    }


}