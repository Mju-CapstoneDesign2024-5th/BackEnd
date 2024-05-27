/*
package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Entity.Contents;
import com.mju.BackEnd.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ContentsController {

    private final ContentsRepository contentsRepository;

    @PostMapping("/contents/save")
    public ResponseEntity<String> contentsSave(@RequestBody Contents contents) {
        contentsRepository.save(contents);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }


    @PostMapping("/contents/find")
    public ResponseEntity<?> contentsSearch(@RequestBody Contents contents) {
        // 데이터베이스에서 id로 Contents 객체를 찾습니다.
        Optional<Contents> foundContents = contentsRepository.findById(Long.parseLong(contents.getId()));

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return foundContents
                .map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

*/
