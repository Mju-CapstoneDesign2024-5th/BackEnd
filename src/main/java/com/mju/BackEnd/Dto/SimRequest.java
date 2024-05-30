package com.mju.BackEnd.Dto;

public class SimRequest {

    private String contentsId;

    // 디폴트 생성자
    public SimRequest() {
    }

    // 매개변수가 있는 생성자
    public SimRequest(String contentsId) {
        this.contentsId = contentsId;
    }

    // getter 메소드
    public String getContentsId() {
        return contentsId;
    }

    // setter 메소드
    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }
}
