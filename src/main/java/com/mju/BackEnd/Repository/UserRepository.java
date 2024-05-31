package com.mju.BackEnd.Repository;

import com.mju.BackEnd.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdAndUserPasswd(String id, String userPasswd);

    Optional<User> findByUserId(String userId);
}
