package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.*;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.Duration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;;
@RestController
public class searchController {
    private final OpenAIService openAIService;
    private final NService nService;
    private final ObjectMapper objectMapper;

    private final WebCrawlService webCrawlService;


    private final ImageService imageService;

    @Autowired
    public searchController(ImageService imageService, OpenAIService openAIService, NService nService, ObjectMapper objectMapper, WebCrawlService webCrawlService) {
        this.imageService = imageService;
        this.openAIService = openAIService;
        this.nService = nService;
        this.objectMapper = objectMapper;
        this.webCrawlService = webCrawlService;
    }

    @PostMapping("/test")
    public String justTest(@RequestBody SearchRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String query = request.getQuery();
            String sort = request.getSort();
            List<KinDescription> temp = nService.searchKin(query, sort);
            GenerateTemplate ret = new GenerateTemplate(temp.get(0), "");
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ret);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"error\": \"JSON processing error\"}";
        }
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
                            .flatMap(description -> Mono.delay(Duration.ofMillis(10))
                                    .then(openAIService.generateResponse(description, currentIndex))
                                    .flatMap(response -> openAIService.generateImage(response, currentIndex))
                                    .flatMap(imageResponse -> webCrawlService.getData(imageResponse))
                                    .flatMap(imageDownload-> imageService.downloadImage(imageDownload)))
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

    @PostMapping("/chat")
    public Mono<ResponseEntity<String>> dSearch(@RequestBody SearchRequest request) {
        String query = request.getQuery();
        String sort = request.getSort();
        AtomicInteger bufferIndex = new AtomicInteger(0);
        return Flux.fromIterable(nService.searchKin(query, sort))
                .buffer(5) // 5개씩 묶어서 처리
                .flatMap(batch -> {
                    int currentIndex = bufferIndex.getAndIncrement();
                    return Flux.fromIterable(batch)
                            .flatMap(description -> Mono.delay(Duration.ofMillis(10))
                                    .then(openAIService.generateResponse(description, currentIndex)))
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