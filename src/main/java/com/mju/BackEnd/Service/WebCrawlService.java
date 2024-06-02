package com.mju.BackEnd.Service;

import com.mju.BackEnd.Dto.GenerateTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service("WebCrawlService")
public class WebCrawlService {

    private final WebClient webClient;

    @Value("${serverAddr}")
    private String serverAddress;

    public WebCrawlService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://kin.naver.com").build();
    }

    public Mono<GenerateTemplate> getDataMono(GenerateTemplate source) {
        return webClient.get()
                .uri(source.getSrcLink())
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(body -> {
                    try {
                        return Mono.just(parseHtml(body, source));
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
                })
                .retryWhen(Retry.backoff(Long.MAX_VALUE, Duration.ofSeconds(1))
                        .filter(throwable -> throwable instanceof WebClientResponseException &&
                                ((WebClientResponseException) throwable).getStatusCode().value() == 429));
    }

    private GenerateTemplate parseHtml(String html, GenerateTemplate source) throws IOException {
        Document doc = Jsoup.parse(html);

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
}
