package com.example.claudio_pc.myapplication;

import android.opengl.GLSurfaceView;
import android.util.Log;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class SimleRenderer implements GLSurfaceView.Renderer {
    Random rnd = new Random();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d("ar", "surface created");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d("ar", "Surface Changed");
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(rnd.nextFloat(), rnd.nextFloat(),rnd.nextFloat(),1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }
}
