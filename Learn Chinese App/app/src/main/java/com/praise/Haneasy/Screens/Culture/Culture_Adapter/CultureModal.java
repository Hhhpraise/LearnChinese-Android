package com.praise.Haneasy.Screens.Culture.Culture_Adapter;

public class CultureModal {
    int id;
    String name;
    String img;


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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CultureModal(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }
}
