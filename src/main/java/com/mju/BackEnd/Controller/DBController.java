
package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.ContentsRequest;
import com.mju.BackEnd.Dto.FavoritesDTO;
import com.mju.BackEnd.Dto.LoginRequest;
import com.mju.BackEnd.Dto.UserNameChangeRequest;
import com.mju.BackEnd.Entity.Favorites;
import com.mju.BackEnd.Entity.User;
import com.mju.BackEnd.Entity.Contents;
import com.mju.BackEnd.Repository.*;
import com.mju.BackEnd.Service.WebCrawlService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DBController {

    private final ContentsRepository contentsRepository;
    private final UserRepository userRepository;
    private final FavoritesRepository favoritesRepository;

    private final WebCrawlService webCrawlService;

    private final ObjectMapper objectMapper;

    // 글 내용 저장 및 찾기\
    @Autowired
    public DBController(ContentsRepository contentsRepository, UserRepository userRepository, FavoritesRepository favoritesRepository, ObjectMapper objectMapper, WebCrawlService webCrawlService) {
        this.contentsRepository = contentsRepository;
        this.userRepository = userRepository;
        this.favoritesRepository = favoritesRepository;
        this.objectMapper = objectMapper;
        this.webCrawlService = webCrawlService;
    }
    @PostMapping("/contents/save")
    public ResponseEntity<String> contentsSave(@RequestBody Contents contents) {
        contentsRepository.save(contents);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }

 /*   @PostMapping("/contents/find")
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
                .map(contentsId -> contentsRepository.findById(contentsId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if (foundContentsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(foundContentsList);
    }*/

    // 즐겨찾기 저장 및 찾기

    @PostMapping("/favorites/save")
    public ResponseEntity<Favorites> saveFavorite(@RequestBody Favorites request) {
        return ResponseEntity.ok(favoritesRepository.save(request));
    }

    // 즐겨찾기 삭제
    @PostMapping("/favorites/delete")
    public ResponseEntity<String> deleteFavorite(@RequestBody FavoritesDTO request) {
        Optional<Favorites> favorite = favoritesRepository.findByUserIdAndContentsId(request.getUserId(), request.getContentsId());

        if (favorite.isPresent()) {
            favoritesRepository.delete(favorite.get());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/favorites/find")
    public ResponseEntity<?> favoritesSearch(@RequestBody ContentsRequest contentsRequest) {
        String userId = contentsRequest.getUserId();

        if (userId == null) {
            return ResponseEntity.badRequest().body("failed");
        }

        List<Favorites> foundFavorites = favoritesRepository.findByUserId(userId);

        if (foundFavorites.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> contentsIds = foundFavorites.stream()
                .map(Favorites::getContentsId)
                .collect(Collectors.toList());

        List<Contents> contentsList = contentsRepository.findAllById(contentsIds);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(contentsList);
    }
    // user 저장 및 찾기

    @PostMapping("/user/save")
    public ResponseEntity<String> userSave(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {
        //System.out.println(loginRequest.toString());
        // 데이터베이스에서 Id로 User 객체를 찾습니다.
        Optional<User> foundUser = userRepository.findByUserIdAndUserPasswd(loginRequest.getUserId(),loginRequest.getUserPasswd());

        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Login Failed!");
        }

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundUser.get()));
    }

    @PostMapping("/user/changeName")
    public ResponseEntity<String> userNameChange(@RequestBody UserNameChangeRequest request) {
        String userId = request.getUserId();
        String newUserName = request.getNewUserName();

        // 요청된 userId로 사용자를 찾습니다.
        Optional<User> userOptional = userRepository.findByUserId(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // 사용자를 찾은 경우 새로운 사용자 이름을 설정하고 저장합니다.
        User user = userOptional.get();
        user.setUserName(newUserName);
        userRepository.save(user);

        return ResponseEntity.ok("User name changed successfully");
    }
}
