package com.mju.BackEnd.Dto;

public class SearchRequest {
    private String query;
    private String sort;

    // getters and setters
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
