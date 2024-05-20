package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.*;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.atomic.AtomicInteger;;
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


    @PostMapping("/chat")
    public Mono<String> aiRequest(@RequestBody String prompt){
        return openAIService.generateResponse(prompt, 3);
    }

    @PostMapping("/search")
    public Mono<ResponseEntity<String>> iSearch(@RequestBody SearchRequest request) {
        String query = request.getQuery();
        String sort = request.getSort();
        AtomicInteger bufferIndex = new AtomicInteger(0);
        return Flux.fromIterable(nService.searchKin(query, sort))
                .buffer(5) // 5개씩 묶어서 처리
                .flatMap(batch -> {
                    int currentIndex = bufferIndex.getAndIncrement();
                    return Flux.fromIterable(batch)
                            .flatMap(description -> Mono.delay(Duration.ofMillis(10)) // 각 요청별로 1초 딜레이
                                    .then(openAIService.generateResponse(description, currentIndex))
                                    .flatMap(response -> openAIService.generateImage(response, currentIndex)))
                            .collectList();
                })
                .collectList()
                .map(seriesOfBatches -> {
                    try {
                        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(seriesOfBatches);
                        return ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(jsonResponse);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error processing JSON", e);
                    }
                });
    }


}