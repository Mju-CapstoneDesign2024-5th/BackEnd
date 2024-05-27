package com.mju.BackEnd.Repository;

import com.mju.BackEnd.Entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, String> {
    List<Favorites> findAllByUserId(String userId);
}
