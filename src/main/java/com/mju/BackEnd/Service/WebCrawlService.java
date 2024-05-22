package com.mju.BackEnd.Service;

import com.mju.BackEnd.Dto.*;
import com.mju.BackEnd.Dto.GenerateTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
@Service("WebCrawlService")
public class WebCrawlService {

    public WebCrawlService(){

    }

    public Mono<GenerateTemplate> getData(GenerateTemplate source) {
        return Mono.fromCallable(() -> {
            Document doc = Jsoup.connect(source.getSrcLink()).get();
            Elements questionElements = doc.select(".questionDetail");
            Elements answerElements = doc.select(".answerDetail");

            List<String> questionDetails = new ArrayList<>();
            List<String> answerDetails = new ArrayList<>();

            questionElements.forEach(element -> questionDetails.add(element.text()));
            answerElements.forEach(element -> answerDetails.add(element.text()));

            source.setQuestionDetails(questionDetails);
            source.setAnswerDetails(answerDetails);
            return source;
        });
    }
}
