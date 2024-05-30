package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.GenerateTemplate;
import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import com.mju.BackEnd.Repository.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class frontController {

    private final DBService dbService;
    private final WebCrawlService webCrawlService;

    private final ObjectMapper objectMapper;

    @Autowired
    public frontController(DBService dbService, WebCrawlService webcrawlservice, ObjectMapper objectMapper){
        this.dbService = dbService;
        this.webCrawlService = webcrawlservice;
        this.objectMapper = objectMapper;
    }
    @RequestMapping("/main")
    public ResponseEntity<List<GenerateTemplate>> root() throws JsonProcessingException {
        List<GenerateTemplate> ret = dbService.printAllContents().stream()
                .map(content -> {
                    try {
                        return webCrawlService.getData(content);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(ret);
    }

}
