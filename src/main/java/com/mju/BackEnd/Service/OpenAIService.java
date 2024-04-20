package com.mju.BackEnd.Service;

import com.mju.BackEnd.Dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

@Service("OpenAIService")
public class OpenAIService {
    private final WebClient webClient;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1";

    @Value("${OPENAI_API_KEY}")
    private String OPENAI_API_KEY;
    @Autowired
    public OpenAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(OPENAI_API_URL).build();
    }

    public String generateResponse(String prompt) {
        String option = " summarize as tags";
        OpenAIChatRequest requestDTO = new OpenAIChatRequest(
                "gpt-4-turbo-2024-04-09",
                List.of(
                        new OpenAIChatRequest.Message("system", "You are a helpful assistant."),
                        new OpenAIChatRequest.Message("user", prompt+option)
                )
        );

        OpenAIChatResponse response =  webClient.post()
                .uri("/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .body(BodyInserters.fromValue(requestDTO))
                .retrieve()
                .bodyToMono(OpenAIChatResponse.class)
                .block();

        return response.getChoices().get(0).getMessage().getContent();

    }
    public GenerateTemplate generateImage(String prompt) {
        OpenAIImageRequest requestImageDTO = new OpenAIImageRequest("dall-e-3", prompt, 1, "1024x1024");

        // WebClient 요청을 설정합니다.
        OpenAIImageResponse response = webClient.post()
                .uri("/images/generations")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .body(BodyInserters.fromValue(requestImageDTO))
                .retrieve()
                .bodyToMono(OpenAIImageResponse.class)
                .block(); // Blocking call to get the response synchronously

        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
            return new GenerateTemplate(response.getData().get(0).getRevised_prompt(),response.getData().get(0).getUrl());
        } else {
            return null; // Handle the case where no URL is available
        }
    }
    }

