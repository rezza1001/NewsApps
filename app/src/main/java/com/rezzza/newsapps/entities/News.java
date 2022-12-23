package com.rezzza.newsapps.entities;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

    private String author;
    private String title;
    private String description;
    private String publishedAt;
    private Date publishDate;
    private String url;
    private String urlToImage;
    private String category;
    private boolean isLoading = false;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
