package com.mju.BackEnd.Dto;

public class UserNameChangeRequest {
    private String userId;
    private String newUserName;

    // 기본 생성자
    public UserNameChangeRequest() {
    }

    // 매개변수를 받는 생성자
    public UserNameChangeRequest(String userId, String newUserName) {
        this.userId = userId;
        this.newUserName = newUserName;
    }

    // 게터와 세터
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }
}

