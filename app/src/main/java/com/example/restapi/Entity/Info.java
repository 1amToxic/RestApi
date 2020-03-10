package com.example.restapi.Entity;

public class Info {
    private String content;
    private byte[] image;

    public Info(String content, byte[] image) {
        this.content = content;
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public byte[] getImage() {
        return image;
    }
}
