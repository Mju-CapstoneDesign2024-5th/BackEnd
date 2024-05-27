/*
package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.LoginRequest;
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

    @PostMapping("/user/find")
    public ResponseEntity<?> userSearch(@RequestBody User user) {

        // 데이터베이스에서 Id로 Contents 객체를 찾습니다.
        Optional<User> foundUser = userRepository.findById(Long.parseLong(user.getId()));

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return foundUser
                .map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {

        // 데이터베이스에서 Id로 Contents 객체를 찾습니다.
        Optional<User> foundUser = userRepository.findByIdAndUserPasswd(loginRequest.getId(),loginRequest.getuserPasswd());

        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Login Failed!");
        }

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return foundUser
                .map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
*/
