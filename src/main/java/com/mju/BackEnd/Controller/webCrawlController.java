package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.GenerateTemplate;
import com.mju.BackEnd.Dto.KinDescription;
import com.mju.BackEnd.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.mju.BackEnd.Config.*;
import java.io.IOException;

@RestController
public class webCrawlController {

    private final WebCrawlService webCrawlService;
    private final ObjectMapper objectMapper;
    @Autowired
    public webCrawlController(WebCrawlService webCrawlService, ObjectMapper objectMapper){
        this.webCrawlService = webCrawlService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/crawl")
    public Mono<ResponseEntity<String>> crawlPage(@RequestBody String url) {
        KinDescription kk = new KinDescription("", url, "");
        GenerateTemplate gg = new GenerateTemplate(kk, "");

        return webCrawlService.getData(gg)
                .flatMap(result -> {
                    try {
                        String ret = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
                        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret));
                    } catch (JsonProcessingException e) {
                        return Mono.error(new RuntimeException("Error processing JSON", e));
                    }
                });
    }

}
