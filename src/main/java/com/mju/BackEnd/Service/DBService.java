package com.mju.BackEnd.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.ContentsRequest;
import com.mju.BackEnd.Dto.FavoritesDTO;
import com.mju.BackEnd.Dto.GenerateTemplate;
import com.mju.BackEnd.Dto.LoginRequest;
import com.mju.BackEnd.Entity.Favorites;
import com.mju.BackEnd.Entity.User;
import com.mju.BackEnd.Entity.Contents;
import com.mju.BackEnd.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DBService {

    private final ContentsRepository contentsRepository;
    private final UserRepository userRepository;
    private final FavoritesRepository favoritesRepository;
    private final ObjectMapper objectMapper;

    // 글 내용 저장 및 찾기\
    @Autowired
    public DBService(ContentsRepository contentsRepository, UserRepository userRepository, FavoritesRepository favoritesRepository, ObjectMapper objectMapper) {
        this.contentsRepository = contentsRepository;
        this.userRepository = userRepository;
        this.favoritesRepository = favoritesRepository;
        this.objectMapper = objectMapper;
    }

    public void contentsSave(Contents contents) {
        contentsRepository.save(contents);
    }

    public List<GenerateTemplate> contentsFind(List<String> ids){

        List<Contents> contentList = contentsRepository.findAllById(ids);


        List<GenerateTemplate> templateList = contentList.stream()
                .map(content -> new GenerateTemplate(
                        content.getId(),
                        content.getTitle(),
                        content.getDate(),
                        content.getView(),
                        content.getDescription(),
                        content.getSrcLink(),
                        content.getUrl(),
                        null, // Assuming no questionDetails field in Contents, set null or appropriate value
                        null  // Assuming no answerDetails field in Contents, set null or appropriate value
                ))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.shuffle(list, new Random());
                            return list.stream();
                        }
                ))
                .collect(Collectors.toList());

        if (!templateList.isEmpty()) {
            //System.out.println(templateList.get(0));
        }

        return templateList;
    }

    public List<GenerateTemplate> contentsSearch(ContentsRequest contentsRequest) {

        String userId = contentsRequest.getUserId();

        if (userId == null) {
            return null;
        }

        List<Favorites> foundFavorites = favoritesRepository.findAllByUserId(userId);

        if (foundFavorites.isEmpty()) {
            return null;
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
            return null;
        }

        List<GenerateTemplate> templateList = foundContentsList.stream()
                .map(content -> new GenerateTemplate(
                        content.getId(),
                        content.getTitle(),
                        content.getDate(),
                        content.getView(),
                        content.getDescription(),
                        content.getSrcLink(),
                        content.getUrl(),
                        null, // Assuming no questionDetails field in Contents, set null or appropriate value
                        null  // Assuming no answerDetails field in Contents, set null or appropriate value
                ))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.shuffle(list, new Random());
                            return list.stream();
                        }
                ))
                .collect(Collectors.toList());

        //System.out.println(templateList.getFirst());
        return templateList;
    }

    // 즐겨찾기 저장 및 찾기


    public Favorites saveFavorite(Favorites request) {
        return favoritesRepository.save(request);
    }



    public ResponseEntity<?> favoritesSearch(ContentsRequest contentsRequest) {
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

    public void userSave(User user) {
        userRepository.save(user);
    }

    public String userLogin(LoginRequest loginRequest) throws JsonProcessingException {

        // 데이터베이스에서 Id로 User 객체를 찾습니다.
        Optional<User> foundUser = userRepository.findByUserIdAndUserPasswd(loginRequest.getUserId(),loginRequest.getUserPasswd());

        if (foundUser.isEmpty()) {
            return "Login Failed!";
        }

        // 객체가 존재하는 경우 JSON으로 반환합니다.
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundUser.get());

    }

    public Mono<GenerateTemplate> addQuestion(GenerateTemplate source){
        Contents content = new Contents(source.getID(), source.getTitle(), source.getDate(),source.getView(), source.getDescription(), source.getSrcLink(), source.getUrl());
        contentsSave(content);
        return Mono.fromCallable(() -> {
            contentsRepository.save(content);
            return source;
        });
    }

    public List<GenerateTemplate> printAllContents(int limiter) throws JsonProcessingException {
        List<Contents> contentList = contentsRepository.findAll();
        List<GenerateTemplate> templateList = contentList.stream()
                .map(content -> new GenerateTemplate(
                        content.getId(),
                        content.getTitle(),
                        content.getDate(),
                        content.getView(),
                        content.getDescription(),
                        content.getSrcLink(),
                        content.getUrl(),
                        null, // Assuming no questionDetails field in Contents, set null or appropriate value
                        null  // Assuming no answerDetails field in Contents, set null or appropriate value
                ))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.shuffle(list, new Random());
                            return list.stream();
                        }
                ))
                .limit(limiter)
                .collect(Collectors.toList());

        //System.out.println(templateList.getFirst());
        return templateList;

    }




}
