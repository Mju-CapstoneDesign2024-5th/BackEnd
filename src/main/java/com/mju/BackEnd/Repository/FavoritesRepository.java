package com.mju.BackEnd.Repository;

import com.mju.BackEnd.Entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorites, String> {
    List<Favorites> findAllByUserId(String userId);

    Optional<Favorites> findByUserIdAndContentsId(String userId, String contentsId);
}
