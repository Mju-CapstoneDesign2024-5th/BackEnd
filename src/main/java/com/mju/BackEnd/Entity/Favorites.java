package com.mju.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorites_table")
@Getter
@Setter
@NoArgsConstructor
public class Favorites {

    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//본문 Id

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "contents_id", nullable = false)
    private String contentsId;

}
