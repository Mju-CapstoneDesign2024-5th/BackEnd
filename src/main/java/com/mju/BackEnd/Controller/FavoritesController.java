/*
package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.ContentsRequest;
import com.mju.BackEnd.Entity.Contents;
import com.mju.BackEnd.Entity.Favorites;
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
public class FavoritesController {

    private final FavoritesRepository favoritesRepository;

    @PostMapping("/favorites/save")
    public ResponseEntity<String> saveFavorite(@RequestBody Favorites favorites) {
        favoritesRepository.save(favorites);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
    }
    @PostMapping("/favorites/find")
    public ResponseEntity<?> favoritesSearch(@RequestBody ContentsRequest contentsRequest) {
        String userId = contentsRequest.getId();

        Optional<Favorites> foundFavorites = favoritesRepository.findById(Long.parseLong(contentsRequest.getId()));

        if (foundFavorites.isEmpty()) {
            return ResponseEntity.badRequest().body("Search failed");
        }


        return foundFavorites.map(value -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
*/
