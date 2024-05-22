package com.mju.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity //엔티티 정의
@Table(name="Favorites") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
@Getter //lombok getter
@Setter //lombok setter
public class Favorites {
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @NonNull
    private Long User_id;

    @Id
    @Column(unique = true)
    private String Kin_id;
}

