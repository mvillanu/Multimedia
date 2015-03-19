package net.infobosccoma.multimedia.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Maxi on 19/03/2015.
 */
public class User implements Serializable{

    private String name;
    private String surName;
    private Bitmap image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
