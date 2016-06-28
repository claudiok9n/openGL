package com.example.claudio_pc.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import javax.microedition.khronos.opengles.GL10;

public class Vehicle extends Sprite {
    private Context context;
    private static GL10 gl = null;
    private int type = 0;
    private int x = 20;
    private int y = 20;
    private int height = 100;
    private int width = 100;
    private int angle = 0;

    public void Vehicle(Context c, GL10 _gl, int _type){
        context = c;
        gl = _gl;
        type = _type;
        setSprite();
    }

    private Bitmap getResource(){
        return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher) ;
    }

    public void setSprite() {
        super.setSprite(gl, getResource());
    }

    public void draw(int x, int y, int ancho, int alto, float angle) {
        super.draw(gl, x, y, width, height, angle);
    }

}
