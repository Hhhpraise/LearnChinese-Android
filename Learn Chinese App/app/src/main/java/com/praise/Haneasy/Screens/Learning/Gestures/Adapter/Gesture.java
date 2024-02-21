package com.praise.Haneasy.Screens.Learning.Gestures.Adapter;

public class Gesture {
    String gesture;
    String info;
    String imgUrl;

    public Gesture(String gesture, String info, String imgUrl) {
        this.gesture = gesture;
        this.info = info;
        this.imgUrl = imgUrl;
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
