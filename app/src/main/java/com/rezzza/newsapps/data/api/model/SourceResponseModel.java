package com.rezzza.newsapps.data.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SourceResponseModel implements Serializable {

    private String status = "";
    private String code = "";
    private String message = "";
    private ArrayList<SourceDataModel> sources = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<SourceDataModel> getSources() {
        return sources;
    }

    public void setSources(ArrayList<SourceDataModel> sources) {
        this.sources = sources;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
