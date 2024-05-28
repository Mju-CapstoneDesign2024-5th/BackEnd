package com.mju.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity //엔티티 정의
@Table(name="contents_table") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
@Getter //lombok getter
@Setter //lombok setter
public class Contents {

    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @Column(name = "contents_id", nullable = false)
    private String id;//본문 Id

    @Column(name = "title")
    private String title;

    @Column(name="date")
    private String date;

    @Column(name="view")
    private String view;

    @Column(name = "tag")
    private String description;//tag

    @Column(name="srcLink")
    private String srcLink;

    @Column(name="pic_url")
    private String url; //url로 예상.


    public Contents(){
    }
    public Contents(String id, String title, String date, String view, String description, String srcLink, String url) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.view = view;
        this.description = description;
        this.srcLink = srcLink;
        this.url = url;

    }


}