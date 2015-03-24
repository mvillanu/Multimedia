package net.infobosccoma.multimedia.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by Maxi on 19/03/2015.
 */
public class User implements Serializable{

    private String name;
    private String surName;
    private byte[] byteImage;

    public User(String name, String surName/*, Bitmap image*/){
        this.name=name;
        this.surName=surName;
        //this.byteImage = makeByteArray(image);
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

    public Bitmap makeBitMap(byte[] arrayBytes){

        Bitmap bitmap = BitmapFactory.decodeByteArray(arrayBytes,0,arrayBytes.length);
        return bitmap;

    }

    public byte[] makeByteArray(Bitmap image){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;

    }


}
