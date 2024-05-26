package com.mju.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorites_table")
@Getter
@Setter
public class Favorites {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "favorites_id", nullable = false)
    private String favoritesId; // detail_id와 연결되어야함.
}
