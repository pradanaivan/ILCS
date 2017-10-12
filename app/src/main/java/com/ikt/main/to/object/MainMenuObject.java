package com.ikt.main.to.object;

/**
 * Created by Arifin on 2/8/16.
 */
public class MainMenuObject {

    private String mName;
    private int mThumbnail;
    private int mTextColor;

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.mThumbnail = thumbnail;
    }
}
