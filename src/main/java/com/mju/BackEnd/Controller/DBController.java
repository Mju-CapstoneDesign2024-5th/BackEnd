
package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.ContentsRequest;
import com.mju.BackEnd.Dto.FavoritesDTO;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DBController {

    private final ContentsRepository contentsRepository;
    private final UserRepository userRepository;
    private final FavoritesRepository favoritesRepository;

    private final ObjectMapper objectMapper;

    // 글 내용 저장 및 찾기\
    @Autowired
    public DBController(ContentsRepository contentsRepository, UserRepository userRepository, FavoritesRepository favoritesRepository, ObjectMapper objectMapper) {
        this.contentsRepository = contentsRepository;
        this.userRepository = userRepository;
        this.favoritesRepository = favoritesRepository;
        this.objectMapper = objectMapper;
    }
    @PostMapping("/contents/save")
    public ResponseEntity<String> contentsSave(@RequestBody Contents contents) {
        contentsRepository.save(contents);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }

    @PostMapping("/contents/find")
    public ResponseEntity<?> contentsSearch(@RequestBody ContentsRequest contentsRequest) {

        String userId = contentsRequest.getUserId();

        if (userId == null) {
            return ResponseEntity.badRequest().body("failed");
        }

        List<Favorites> foundFavorites = favoritesRepository.findAllByUserId(userId);

        if (foundFavorites.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> contentsIds = foundFavorites.stream()
                .map(Favorites::getContentsId)
                .collect(Collectors.toList());

        List<Contents> foundContentsList = contentsIds.stream()
                .map(contentsId -> contentsRepository.findById(Long.parseLong(contentsId)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if (foundContentsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(foundContentsList);
    }

    // 즐겨찾기 저장 및 찾기

    @PostMapping("/favorites/save")
    public ResponseEntity<Favorites> saveFavorite(@RequestBody Favorites request) {
        return ResponseEntity.ok(favoritesRepository.save(request));
    }



    @PostMapping("/favorites/find")
    public ResponseEntity<?> favoritesSearch(@RequestBody ContentsRequest contentsRequest) {
        String userId = contentsRequest.getUserId();

        if (userId == null) {
            return ResponseEntity.badRequest().body("failed");
        }

        List<Favorites> foundFavorites = favoritesRepository.findAllByUserId(userId);

        if (foundFavorites.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> contentsIds = foundFavorites.stream()
                .map(Favorites::getContentsId)
                .collect(Collectors.toList());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(contentsIds);
    }
    // user 저장 및 찾기

    @PostMapping("/user/save")
    public ResponseEntity<String> userSave(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {

        // 데이터베이스에서 Id로 User 객체를 찾습니다.
        Optional<User> foundUser = userRepository.findByUserIdAndUserPasswd(loginRequest.getId(),loginRequest.getuserPasswd());

        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Login Failed!");
        }

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundUser.get()));
    }
}

