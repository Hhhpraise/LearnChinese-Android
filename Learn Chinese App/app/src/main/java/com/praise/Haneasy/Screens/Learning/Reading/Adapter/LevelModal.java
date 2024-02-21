package com.praise.Haneasy.Screens.Learning.Reading.Adapter;

public class LevelModal {
    private int id;
    private String name;
    private  String image;

    public LevelModal(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
