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

    public Mono<String> generateResponse(String prompt) {
        String option = "태그의 형태로 요약해줘 태그 앞에는 무조건 #을 붙여야해";
        OpenAIChatRequest requestDTO = new OpenAIChatRequest(
                "gpt-4-turbo-2024-04-09",
                List.of(
                        new OpenAIChatRequest.Message("system", option),
                        new OpenAIChatRequest.Message("user", prompt)
                )
        );

        return webClient.post()
                .uri("/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .body(BodyInserters.fromValue(requestDTO))
                .retrieve()
                .bodyToMono(OpenAIChatResponse.class)
                .map(response -> response.getChoices().get(0).getMessage().getContent());
    }
    public Mono<GenerateTemplate> generateImage(String prompt) {
        OpenAIImageRequest requestImageDTO = new OpenAIImageRequest("dall-e-3", prompt, 1, "1024x1024");

        // WebClient 요청을 설정합니다.
        return webClient.post()
                .uri("/images/generations")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .body(BodyInserters.fromValue(requestImageDTO))
                .retrieve()
                .bodyToMono(OpenAIImageResponse.class)
                .map(response -> {
                    if (response != null && response.getData() != null && !response.getData().isEmpty()) {
                        return new GenerateTemplate(prompt, response.getData().get(0).getUrl());
                    } else {
                        return null; // Handle the case where no URL is available
                    }
                });
    }
    }

