package com.mju.BackEnd.Repository;

import com.mju.BackEnd.Entity.Favorites;
import com.mju.BackEnd.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Optional<Favorites> findByUser(User user);
}
