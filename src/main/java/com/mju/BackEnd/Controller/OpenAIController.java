package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<String> generateResponse(@RequestBody String prompt) {
        return openAIService.generateResponse(prompt);
    }

    @PostMapping("/image")
    public Mono<String> generateImage(@RequestBody String prompt) {
        return openAIService.generateImage(prompt);
    }

    @GetMapping("/kin")
    public Mono<String> searchKin(@RequestBody String query) { return nService.searchKin(query);}

}