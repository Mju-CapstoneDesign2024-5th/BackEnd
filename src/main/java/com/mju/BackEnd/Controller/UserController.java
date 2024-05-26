package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Entity.User;
import com.mju.BackEnd.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/user/save")
    public ResponseEntity<String> userSave(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }

//    @PostMapping("/users/find")
//    public ResponseEntity<?> personSearch(@RequestBody User user) {
//
//        // 데이터베이스에서 Detail_Id로 Contents 객체를 찾습니다.
//        Optional<User> foundContents = userRepository.findById(user.getUserId());
//
//        // 객체가 존재하는 경우 JSON으로 반환합니다.
//        return foundContents
//                .map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}


/*


import com.mju.BackEnd.Entity.User;
import com.mju.BackEnd.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/users/save")
    public ResponseEntity<String> userSave(@RequestBody User user) {
        // 클라이언트로부터 전달받은 User 객체의 UserName 필드가 NULL인지 확인
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            // UserName이 NULL이거나 빈 문자열인 경우, 클라이언트에게 오류 메시지를 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserName cannot be null or empty");
        }

        // UserName이 NULL이 아닌 경우, UserRepository를 통해 저장
        userRepository.save(user);

        // 저장 성공 메시지 반환
        return ResponseEntity.ok("User saved successfully");
    }
}
*/


