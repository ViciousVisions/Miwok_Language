package com.example.android.miwok;

/**
 * Created by Code__000 on 9/30/2016.
 */

public class Word {
    private String englishWord;
    private String miwokWord;
    private String image;

    public Word(String english, String miwok) {
        englishWord = english;
        miwokWord = miwok;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
