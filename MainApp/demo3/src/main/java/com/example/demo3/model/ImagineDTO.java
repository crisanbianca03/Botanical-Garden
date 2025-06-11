package com.example.demo3.model;

public class ImagineDTO {
    private int id;
    private String url;
    private int exemplarId;

    public ImagineDTO(int id, String url, int exemplarId) {
        this.id = id;
        this.url = "http://localhost:8080/" + url;
        this.exemplarId = exemplarId;
    }
    public ImagineDTO() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setUrl(String url) {
        this.url = url;
    }

    public int getExemplarId() {
        return exemplarId;
    }

    public void setExemplarId(int exemplarId) {
        this.exemplarId = exemplarId;
    }
    public String getUrl() {
        return url;
    }

}