package com.mju.BackEnd.Repository;

import com.mju.BackEnd.Entity.Favorites;
import com.mju.BackEnd.Entity.FavoritesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, FavoritesId> {
    Optional<Favorites> findByUserIdAndContentsId(String userId, String contentsId);

    // userId로 Favorites 찾기
    List<Favorites> findByUserId(String userId);

    // contentsId로 Favorites 찾기
    List<Favorites> findByContentsId(String contentsId);
}