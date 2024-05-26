package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Entity.Favorites;
import com.mju.BackEnd.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesRepository favoritesRepository;

    @PostMapping("/favorites/save")
    public ResponseEntity<String> saveFavorite(@RequestBody Favorites favorites) {
        favoritesRepository.save(favorites);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }
}
