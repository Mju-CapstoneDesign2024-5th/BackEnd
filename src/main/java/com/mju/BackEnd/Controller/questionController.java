package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.*;
import com.mju.BackEnd.Config.*;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.mju.BackEnd.Config.*;
@RestController
public class questionController {
        @RequestMapping("/main")
    public ResponseEntity<?> root() {
                List<GenerateTemplate> templates = IntStream.range(0, 60)
                        .mapToObj(i -> {
                                GenerateTemplate template = new GenerateTemplate(
                                        new KinDescription(
                                                "maroon5노래 중 payphone에 대해 질문입니다...",
                                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0",
                                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee"
                                        ),
                                        "https://as1.ftcdn.net/v2/jpg/03/16/39/46/1000_F_316394603_1OkqvRq5PUguqzEJgsjBETLljTmgjbIb.jpg"
                                );
                                template.setAnswerDetails(Arrays.asList("안녕하세요.. 제가 마룬5 그룹 노래를 좋아해서 글을 올립니다.. 제가 카페에서 친구들과 같이 즐겁게 얘기하면서 지내고 있다가 마룬5의 payphone 노래를 우연히 듣게 되었어요.. 그런데 남자가수가 부른게 아니라 자세히 들어보니 여자가수가 마룬5의 payphone 노래를 부르고 있더라고요.. 자세히 들어보니 r&b버젼으로 부르는 것 같았어요.. 혹시 아시는 분이 계신다면 여자가수가 누구인지 구할 수 있는 음악인지 가르쳐주십시요.. 정말 제가 좋아하는 가수, 노래입니다 부탁해요..."));
                                template.setQuestionDetails(Arrays.asList(
                                        "Payphone - Maroon 5 (Jayesslee Cover)",
                                        "MAROON5 4집 디럭스버젼에 PAYPHONE2가지 버전 더있든데 그건가.... 아니 여성보컬이 하지는 않았던거같은데"));
                                template.setID(String.valueOf(i));
                                return template;
                        })
                        .collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        String ret="";
        try {
            ret = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(templates);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret);

    }
}
