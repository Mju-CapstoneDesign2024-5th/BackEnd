package com.mju.BackEnd.Service;

import com.mju.BackEnd.Dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.time.Duration;
import java.util.List;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

@Service("OpenAIService")
public class OpenAIService {
    @Value("${serverAddr}")
    private String serverAddress;
    private final WebClient webClient;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1";

    @Value("${OPENAI_API_KEY}")
    private String OPENAI_API_KEY;


    @Autowired
    public OpenAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(OPENAI_API_URL)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().responseTimeout(Duration.ofSeconds(300)) // 타임아웃 설정
                ))
                .build();

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
                })
                .doOnError(throwable -> {
                    if (throwable instanceof WebClientResponseException) {
                        WebClientResponseException e = (WebClientResponseException) throwable;
                        //System.out.println("Request failed: " + currentIndex);
                        //System.out.println(description.getDescription());
                        //System.out.println("Error response: " + e.getResponseBodyAsString());
                    } else {
                        //System.out.println("An unexpected error occurred: " + throwable.getMessage());
                    }
                })
                .onErrorResume(throwable -> Mono.just(new KinDescription(description.getTitle(), description.getLink(), "An error occurred while processing the request.")));

    }


    public Mono<GenerateTemplate> generateImage(KinDescription question, int currentIndex) {
        OpenAIImageRequest requestImageDTO = new OpenAIImageRequest("dall-e-3", question.getDescription()+" safety system에 위배되지 않게", 1, "1024x1024");
        String[] keys = OPENAI_API_KEY.split(",");
        String API_KEY = keys[currentIndex];
        //System.out.println(question.getDescription());

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
                })
                .doOnError(throwable -> {
                    if (throwable instanceof WebClientResponseException) {
                        WebClientResponseException e = (WebClientResponseException) throwable;
                        //System.out.println("Request failed: " + currentIndex);
                        //System.out.println(question.getDescription());
                        //System.out.println("Error response: " + e.getResponseBodyAsString());
                    } else {
                        //System.out.println("An unexpected error occurred: " + throwable.getMessage());
                    }
                })
                .onErrorResume(throwable -> {
                    return Mono.just(new GenerateTemplate(question, serverAddress +"/images/Failed"));
                });
    }
    }

