package com.mju.BackEnd.Dto;

public class LoginRequest {
    // UserDTO.java

    private String id;
    private String userPasswd;

    // 기본 생성자
    public LoginRequest() {
    }

    // 매개변수가 있는 생성자
    public LoginRequest(String id, String userPasswd) {
        this.id = id;
        this.userPasswd = userPasswd;
    }

    // Getter와 Setter 메서드
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuserPasswd() {
        return userPasswd;
    }

    public void setuserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    // toString 메서드 (옵션)
    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                '}';
    }

}
