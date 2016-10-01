package com.example.android.miwok;

/**
 * Created by Code__000 on 9/30/2016.
 */

public class Word {
    private String englishWord;
    private String miwokWord;
    private int imageResourceID;
    private boolean mHasImage;

    public Word(String english, String miwok) {
        englishWord = english;
        miwokWord = miwok;
        mHasImage = false;
    }

    public Word(String english, String miwok, int imageResourceID) {
        englishWord = english;
        miwokWord = miwok;
        this.imageResourceID = imageResourceID;
        mHasImage = true;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public int getImageResourceID(){
        return this.imageResourceID;
    }

    public boolean hasImage(){
        return mHasImage;
    }

}
