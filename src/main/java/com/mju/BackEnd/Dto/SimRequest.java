package com.mju.BackEnd.Dto;

public class SimRequest {


    private String contentsId;

    public SimRequest(String contentsId) {
        this.contentsId = contentsId;
    }


    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }
}
