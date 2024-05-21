package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.*;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class questionController {
        @CrossOrigin(origins = "http://localhost:3000")
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
