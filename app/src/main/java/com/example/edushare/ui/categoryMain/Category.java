package com.example.edushare.ui.categoryMain;

import android.graphics.drawable.Drawable;

public class Category {
    int image;
    String name;
    int image_num;

    public Category(int image, String name, int image_num) {
        this.image = image;
        this.name = name;
        this.image_num = image_num;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_num() {
        return image_num;
    }

    public void setImage_num(int image_num) {
        this.image_num = image_num;
    }
}
