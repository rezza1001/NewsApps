package com.rezzza.newsapps.data.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsResponseModel implements Serializable {

    private String status;
    private int totalResults = 0;
    private String code = "";
    private String message = "";

    private ArrayList<ArticlesModel> articles = new ArrayList<>();

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setArticles(ArrayList<ArticlesModel> articles) {
        this.articles = articles;
    }

    public ArrayList<ArticlesModel> getArticles() {
        return articles;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
