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


    public Mono<KinDescription> generateResponse(KinDescription description, int currentIndex) {
        String[] keys = OPENAI_API_KEY.split(",");
        String API_KEY = keys[currentIndex];
        String prompt = description.getDescription();
        String option = "태그의 형태로 요약해줘 태그 앞에는 무조건 #을 붙여야해";
    	String option2 = "죄송합니다를 출력금지";
	    String option3 = "#뒤에 붙는 각 태그당  10글자로 제한하고 태그는 총 5개";

	OpenAIChatRequest requestDTO = new OpenAIChatRequest(
                "gpt-4o",
                List.of(
                        new OpenAIChatRequest.Message("system", option),
                        new OpenAIChatRequest.Message("system", option2),
                        new OpenAIChatRequest.Message("system", option3),
                        new OpenAIChatRequest.Message("user", prompt)
                )
        );

        return webClient.post()
                .uri("/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .body(BodyInserters.fromValue(requestDTO))
                .retrieve()
                .bodyToMono(OpenAIChatResponse.class)
                .map(response -> {
                    if (response != null) {
                        return new KinDescription(description.getTitle(), description.getLink(), response.getChoices().get(0).getMessage().getContent());
                    } else {
                        return null;
                    }
                });

    }


    public Mono<GenerateTemplate> generateImage(KinDescription question, int currentIndex) {
        OpenAIImageRequest requestImageDTO = new OpenAIImageRequest("dall-e-3", question.getDescription(), 1, "1024x1024");
        String[] keys = OPENAI_API_KEY.split(",");
        String API_KEY = keys[currentIndex];
        // WebClient 요청을 설정합니다.
        return webClient.post()
                .uri("/images/generations")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .body(BodyInserters.fromValue(requestImageDTO))
                .retrieve()
                .bodyToMono(OpenAIImageResponse.class)
                .map(response -> {
                    if (response != null && response.getData() != null && !response.getData().isEmpty()) {
                        return new GenerateTemplate(question, response.getData().get(0).getUrl());
                    } else {
                        return null;
                    }
                });
    }
    }

