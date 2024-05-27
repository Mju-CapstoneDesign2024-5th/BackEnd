package com.mju.BackEnd.Dto;

public class ContentsRequest {

    private String userId;
    private String contentsId;

    public ContentsRequest() {
    }

    public ContentsRequest(String userId){
        this.userId = userId;
    }
    public ContentsRequest(String userId, String contentsId) {
        this.userId = userId;
        this.contentsId = contentsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }
}
