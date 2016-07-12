package com.example.claudio_pc.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class GlRenderer implements GLSurfaceView.Renderer {
    private static Context context;
    private Cube cube;
    private Vehicle vehicle;
    private ObjectMesh map;
    private float rotationAngle;

    public GlRenderer(Context c){
        context = c;
        //cube = new Cube(c);
        //vehicle = new Vehicle();
        try {
            map = LoadOBJ.load(c.getResources().openRawResource(R.raw.map), c.getResources().openRawResource(R.raw.cube_mtl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig configuracionEGL) {
        // Load the texture for the square
        //vehicle.loadGLTexture(gl, this.context);
        //mapGenerator.loadGLTexture(gl, this.context);

        gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
        gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
        gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
        gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
        gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do

        //Really Nice Perspective Calculations
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        float fovy = 50.0f; // Field of view angle, in degrees, in the Y direction.
        float aspect = (float)width / (float)height;
        float zNear = 0.1f;
        float zFar = 100.0f;
        // Set up a perspective projection matrix
        GLU.gluPerspective(gl, fovy, aspect, zNear, zFar);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl){
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Replace the current matrix with the identity matrix.
        /*gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(rotationAngle, 1.0f, 1.0f, 1.0f);
        cube.draw(gl);
        rotationAngle -= 0.4f;*/

        /*gl.glLoadIdentity();
        gl.glTranslatef(vehicle.x, getY(), -6.0f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(getAngleRotate(), 0.0f, 0.0f, 1.0f);
        vehicle.draw(gl);
        */

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glRotatef(0, 50.0f, 1.0f, 1.0f);
        map.draw(gl);
        mAngle += 0.4f;
    }

    public volatile float mAngle;

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    public String rotate;
    public float angleRotate;

    public String getRotate(){
        return rotate;
    }
    public void setRotate(String _rotate){
        rotate = _rotate;
    }
    public float getAngleRotate(){
        if (rotate == null)
            return 0;

        if(rotate.equals("LEFT")){
            angleRotate += 10;
        }else if(rotate.equals("RIGHT")){
            angleRotate += -10;
        }
        return angleRotate;
    }

    public String move;
    public float posX;
    public float posY;
    public void setX(float x){ posX = x; }
    public float getX(){ return posX; }
    public void setY(float y){ posY = y; }
    public float getY(){
        if(!vehicle.validateColition()) {
            vehicle.y -= 0.05;
            posY = vehicle.y - 0.05f;
            return posY;
        }
        return vehicle.y;
    }

}
