package com.praise.Haneasy.Screens.Learning.Pinyin.Adapter;

public class PinyinModal {
    int id;
    String text;
    String sound;

    public PinyinModal(int id, String text, String sound) {
        this.id = id;
        this.text = text;
        this.sound = sound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
