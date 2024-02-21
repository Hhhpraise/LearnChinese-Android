package com.praise.Haneasy.Screens.Learning.Videos.Adapter;

public class VideoModal {
    int id;
    String name;
    String minutes;
    String link;

    public VideoModal(int id, String name, String minutes, String link) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
