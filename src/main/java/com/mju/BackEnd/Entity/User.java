package com.mju.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity //엔티티 정의
@Table(name="User") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
@Getter //lombok getter
@Setter //lombok setter
public class User {
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    @Column(unique = true, length=10) //유일하고 최대 길이가 10.
    private String UserName;

    @Column(name="User_gender")
    private String User_gender;

    @Column(name="User_passwd")
    private String User_passwd;

    @Column(name="User_email")
    private String User_email;
}

