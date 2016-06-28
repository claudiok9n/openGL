package com.example.claudio_pc.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;

class MyGlSurfaceView extends GLSurfaceView {

    private final GlRenderer mRenderer;

    public MyGlSurfaceView(Context context){
        super(context);

        // Create an OpenGL ES 2.0 context
        //setEGLContextClientVersion(2);

        mRenderer = new GlRenderer();
        mRenderer.GlRenderer(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }
}