package net.infobosccoma.multimedia.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;

/**
 * Created by Maxi on 19/03/2015.
 */
public class User implements Serializable{

    private String name;
    private String surName;
    private String imagePath;

    public User(String name, String surName, String imagePath){
        this.name=name;
        this.surName=surName;
        this.imagePath = imagePath;
    }

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

    public Bundle makeBundle(String key){
        Bundle b = new Bundle();
        b.putSerializable(key,this);
        return b;
    }

    public Bitmap makeBitmap(){

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath,bmOptions);

        return bitmap;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
