package com.mju.BackEnd.Service;

import com.mju.BackEnd.Dto.*;
import com.mju.BackEnd.Dto.GenerateTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
@Service("WebCrawlService")
public class WebCrawlService {
    @Value("${serverAddr}")
    private String serverAddress;
    public WebCrawlService(){

    }

    public GenerateTemplate getData(GenerateTemplate source) throws IOException {
        int maxRetries = 3;
        int attempt = 0;
        boolean success = false;
        Document doc = null;

        while (attempt < maxRetries && !success) {
            try {
                doc = Jsoup.connect(source.getSrcLink()).timeout(10000000).get();
                success = true; // 성공 시 루프 종료
            } catch (SocketTimeoutException e) {
                attempt++;
                if (attempt == maxRetries) {
                    throw e; // 최대 재시도 횟수 초과 시 예외 발생
                }
            }
        }

        Elements questionElements = doc.select(".questionDetail");
        Elements answerElements = doc.select(".answerDetail");
        Elements infoElement = doc.select(".userInfo .infoItem");

        List<String> questionDetails = new ArrayList<>();
        List<String> answerDetails = new ArrayList<>();
        String view = "";
        String date = "";

        for (Element element : infoElement) {
            String text = element.text();
            if (text.contains("작성일")) {
                date = text.replace("작성일", "").trim();
            } else if (text.contains("조회수")) {
                view = text.replace("조회수", "").trim();
            }
        }

        questionElements.forEach(element -> questionDetails.add(element.text()));
        answerElements.forEach(element -> answerDetails.add(element.text()));

        source.setQuestionDetails(questionDetails);
        source.setAnswerDetails(answerDetails);
        source.setView(view);
        source.setDate(date);
        return source;
    }

    public Mono<GenerateTemplate> getDataMono(GenerateTemplate source) {
        return Mono.fromCallable(() -> {
            return getData(source);
        });
    }
}
