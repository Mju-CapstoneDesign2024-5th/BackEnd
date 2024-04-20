package com.mju.BackEnd.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import com.mju.BackEnd.Dto.*;

import java.util.ArrayList;
import java.util.List;
@Service("NService")
public class NService {
    private final WebClient webClient;



    private static final String API_URL = "https://openapi.naver.com/v1";
    private static final String API_ID = "sZR0jmY7HJcPVfVtOPQ1";

    @Value("${N_API_KEY}")
    private String API_KEY ;
    @Autowired
    public NService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_URL)
                .build();

    }

    public List<String> searchKin(String query) {
        List<String> descriptions = new ArrayList<>();
        KinResponse response = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/search/kin.json")
                                .queryParam("query", query)
                                .queryParam("display", 5)
                                .queryParam("start", 1)
                                .queryParam("sort", "sim")
                                .build())
                .header("X-Naver-Client-Id", API_ID)
                .header("X-Naver-Client-Secret", API_KEY)
                .retrieve()
                .bodyToMono(KinResponse.class)
                .block();

        for(KinResponse.SearchItem item : response.getItems()){
            descriptions.add(item.getDescription());
        }
        return descriptions;
    }


}