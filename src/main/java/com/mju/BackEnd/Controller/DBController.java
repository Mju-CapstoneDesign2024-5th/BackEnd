package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.ContentsRequest;
import com.mju.BackEnd.Dto.LoginRequest;
import com.mju.BackEnd.Entity.Favorites;
import com.mju.BackEnd.Entity.User;
import com.mju.BackEnd.Entity.Contents;
import com.mju.BackEnd.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DBController {

    private final ContentsRepository contentsRepository;
    private final UserRepository userRepository;
    private final FavoritesRepository favoritesRepository;

    // 글 내용 저장 및 찾기\
    @Autowired
    public DBController(ContentsRepository contentsRepository, UserRepository userRepository, FavoritesRepository favoritesRepository) {
        this.contentsRepository = contentsRepository;
        this.userRepository = userRepository;
        this.favoritesRepository = favoritesRepository;
    }
    @PostMapping("/contents/save")
    public ResponseEntity<String> contentsSave(@RequestBody Contents contents) {
        contentsRepository.save(contents);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }

    @PostMapping("/contents/find")
    public ResponseEntity<?> contentsSearch(@RequestBody ContentsRequest contentsRequest) {
        // 데이터베이스에서 id로 Contents 객체를 찾습니다.
        Optional<Contents> foundContents = contentsRepository.findById(Long.parseLong(contentsRequest.getContentsId()));

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return foundContents
                .map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 즐겨찾기 저장 및 찾기

    @PostMapping("/favorites/save")
    public ResponseEntity<Favorites> saveFavorite(@RequestBody Favorites request) {
        return ResponseEntity.ok(favoritesRepository.save(request));
    }



    @PostMapping("/favorites/find")
    public ResponseEntity<?> favoritesSearch(@RequestBody ContentsRequest contentsRequest) {

        Optional<User> user = userRepository.findById(Long.parseLong(contentsRequest.getUserId()));
        if (user.isPresent()) {
            Optional<Favorites> foundFavorites = favoritesRepository.findByUser(user.get());

            if (foundFavorites.isEmpty()) {
                return ResponseEntity.badRequest().body("Search failed");
            }

            return foundFavorites.map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    // user 저장 및 찾기

    @PostMapping("/user/save")
    public ResponseEntity<String> userSave(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {

        // 데이터베이스에서 Id로 User 객체를 찾습니다.
        Optional<User> foundUser = userRepository.findByUserIdAndUserPasswd(loginRequest.getId(),loginRequest.getuserPasswd());

        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Login Failed!");
        }

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return foundUser
                .map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
