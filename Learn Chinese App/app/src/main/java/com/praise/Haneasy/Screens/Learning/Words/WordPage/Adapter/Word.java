package com.praise.Haneasy.Screens.Learning.Words.WordPage.Adapter;

public class Word {
    int id;
    String englishText;
    String chineseCharacter;
    String pinyin;

    public Word(int id, String englishText, String chineseCharacter, String pinyin) {
        this.id = id;
        this.englishText = englishText;
        this.chineseCharacter = chineseCharacter;
        this.pinyin = pinyin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public String getChineseCharacter() {
        return chineseCharacter;
    }

    public void setChineseCharacter(String chineseCharacter) {
        this.chineseCharacter = chineseCharacter;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
