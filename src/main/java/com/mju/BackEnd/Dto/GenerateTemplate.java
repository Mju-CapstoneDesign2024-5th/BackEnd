package com.mju.BackEnd.Dto;

import java.util.List;

public class GenerateTemplate {

    private String id;
    private String title;
    private String description;
    private String srcLink;
    private String url;
    private List<String> questionDetails;
    private List<String> answerDetails;

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for srcLink
    public String getSrcLink() {
        return srcLink;
    }

    // Setter for srcLink
    public void setSrcLink(String srcLink) {
        this.srcLink = srcLink;
    }

    // Getter for url
    public String getUrl() {
        return url;
    }

    // Setter for url
    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getQuestionDetails() {
        return questionDetails;
    }

    public void setQuestionDetails(List<String> questionDetails) {
        this.questionDetails = questionDetails;
    }

    public List<String> getAnswerDetails() {
        return answerDetails;
    }

    public void setAnswerDetails(List<String> answerDetails) {
        this.answerDetails = answerDetails;
    }

    // Getter for id
    public String getID(){
        return id;
    }

    // Setter for id
    public void setID(String id){
        this.id = id;
    }

    public GenerateTemplate(KinDescription description, String url) {
        this.title = description.getTitle();
        this.description = description.getDescription();
        this.srcLink = description.getLink();
        this.url = url;
        this.id = extractID(this.srcLink);
    }

    public static String extractID(String url) {
        String d1id = "";
        String dirId = "";
        String docId = "";

        String[] params = url.split("[?&]");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                switch (keyValue[0]) {
                    case "d1id":
                        d1id = keyValue[1];
                        break;
                    case "dirId":
                        dirId = keyValue[1];
                        break;
                    case "docId":
                        docId = keyValue[1];
                        break;
                }
            }
        }

        return d1id + dirId + docId;
    }
}
