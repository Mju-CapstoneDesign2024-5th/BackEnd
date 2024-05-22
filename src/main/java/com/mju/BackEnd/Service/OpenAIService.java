package com.mju.BackEnd.Service;

import com.mju.BackEnd.Dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

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
        OpenAIImageRequest requestImageDTO = new OpenAIImageRequest("dall-e-3", question.getDescription()+" safety system에 위배되지 않게", 1, "1024x1024");
        String[] keys = OPENAI_API_KEY.split(",");
        String API_KEY = keys[currentIndex];
        System.out.println(question.getDescription());

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
                .onErrorResume(throwable -> {
                    if (throwable instanceof WebClientResponseException) {
                        WebClientResponseException e = (WebClientResponseException) throwable;
                        if (e.getStatusCode().is4xxClientError()) {

                            System.out.println("Request failed after multiple retries: " + currentIndex);
                            System.out.println(question.getDescription());
                            System.out.println("Error response: " + e.getResponseBodyAsString());
                        }
                    }
                    return Mono.just(new GenerateTemplate(question, "https://media.istockphoto.com/id/674612468/ko/%EB%B2%A1%ED%84%B0/%ED%88%AC%EB%AA%85-%EC%97%86%EC%9D%8C-%EA%B8%B0%ED%98%B8-%EC%95%84%EC%9D%B4%EC%BD%98-%EB%B2%A1%ED%84%B0.jpg?s=1024x1024&w=is&k=20&c=nX_-5-q41tDe5jrrW-9m8jh6KQSsiOx3H-XqhlAPJKI="));
                });
    }
    }

