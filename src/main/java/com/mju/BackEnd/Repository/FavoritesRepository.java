package com.mju.BackEnd.Repository;

import com.mju.BackEnd.Entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Optional<Favorites> findByUserIdAndFavoritesId(String userId, String favoritesId);
}
