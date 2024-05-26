package com.mju.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id//기본키를 의미. 반드시 기본키를 가져야함.
    @Column(name = "user_id", nullable = false, unique = true)
    private String id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "user_gender", nullable = false)
    private String userGender;

    @Column(name = "user_passwd", nullable = false)
    private String userPasswd;

    @Column(name = "user_email", nullable = false)
    private String userEmail;
}
