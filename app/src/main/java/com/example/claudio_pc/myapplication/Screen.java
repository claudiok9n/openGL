package com.example.claudio_pc.myapplication;

/**
 * Created by csantamaria on 29/06/2016.
 */
public class Screen {
    public static int height;
    public static int width;

    public Screen(){}

    public static void setHeight(int _height){
        height = _height;
    }
    public static int getHeight(){ return height; }

    public static void setWidth(int _width){
        width = _width;
    }
    public static int getWidth() { return width; }
}
