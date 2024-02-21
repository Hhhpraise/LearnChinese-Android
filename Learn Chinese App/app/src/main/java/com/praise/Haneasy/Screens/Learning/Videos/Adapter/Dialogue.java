package com.praise.Haneasy.Screens.Learning.Videos.Adapter;

public class Dialogue {
    int id;
    String speakerOne;
    String speakerTwo;

    public Dialogue(int id, String speakerOne, String speakerTwo) {
        this.id = id;
        this.speakerOne = speakerOne;
        this.speakerTwo = speakerTwo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeakerOne() {
        return speakerOne;
    }

    public void setSpeakerOne(String speakerOne) {
        this.speakerOne = speakerOne;
    }

    public String getSpeakerTwo() {
        return speakerTwo;
    }

    public void setSpeakerTwo(String speakerTwo) {
        this.speakerTwo = speakerTwo;
    }
}
