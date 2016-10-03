package com.example.android.miwok;

import static android.R.attr.resource;

/**
 * Created by Code__000 on 9/30/2016.
 */

public class Word {
    private String englishWord;
    private String miwokWord;
    private int imageResourceId;
    private int mAudioResourceId;
    private boolean mHasImage;

    public Word(String english, String miwok) {
        englishWord = english;
        miwokWord = miwok;
        mHasImage = false;
    }

    public Word(String english, String miwok, int imageResourceId) {
        englishWord = english;
        miwokWord = miwok;
        this.imageResourceId = imageResourceId;
        mHasImage = true;
    }

    /**
    * @param imageResourceId image resource ID (Ex: R.image.pic1)
    * @param audioResourceId Raw resource ID (Ex: R.raw.song1)
    **/
    public Word(String english, String miwok, int imageResourceId, int audioResourceId) {
        englishWord = english;
        miwokWord = miwok;
        this.imageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
        mHasImage = true;
    }

    public Word(String english, String miwok, int audioResourceId, boolean hasImage) {
        englishWord = english;
        miwokWord = miwok;
        mAudioResourceId = audioResourceId;
        mHasImage = hasImage;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getImageResourceId(){
        return this.imageResourceId;
    }

    public boolean hasImage(){
        return mHasImage;
    }

    public int getAudioResourceId() {return mAudioResourceId; }

    @Override
    public String toString() {
        return "Word{" +
                "englishWord='" + englishWord + '\'' +
                ", miwokWord='" + miwokWord + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                ", mHasImage=" + mHasImage +
                '}';
    }
}
