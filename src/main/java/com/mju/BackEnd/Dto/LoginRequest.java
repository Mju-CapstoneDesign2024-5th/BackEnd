package com.mju.BackEnd.Dto;

public class LoginRequest {

    private String userId;
    private String userPasswd;

    // 기본 생성자
    public LoginRequest() {
    }

    // 매개변수가 있는 생성자
    public LoginRequest(String userId, String userPasswd) {
        this.userId = userId;
        this.userPasswd = userPasswd;
    }

    // Getter와 Setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    // toString 메서드 (옵션)
    @Override
    public String toString() {
        return "LoginRequest{" +
                "userId='" + userId + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                '}';
    }
}
