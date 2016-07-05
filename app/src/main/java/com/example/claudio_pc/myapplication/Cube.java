package com.example.claudio_pc.myapplication;

import android.content.Context;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Cube {
    private FloatBuffer vertexBuffer;
    private ByteBuffer drawListBuffer;
    private FloatBuffer colorBuffer;
    private LoadOBJ loadOBJ;

    // Coordinates for vertices
    static float[] cubeCoords = null;
    // Color definition
    private float colors[] = {
            1.0f, 0.0f, 0.0f, 1.0f, // red for vertex 0
            0.0f, 1.0f, 0.0f, 1.0f, // gree for vertex 1
            0.0f, 0.0f, 1.0f, 1.0f, // blue for vertex 2
            1.0f, 1.0f, 0.0f, 1.0f, // yellow for vertex 3
            1.0f, 1.0f, 0.0f, 1.0f, // and so on...
            0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f
    };
    // Drawing order of cubeCoords[]
    private byte drawOrder[] = {
            0, 1, 3, 1, 3, 2,
            1, 2, 6, 1, 6, 5,
            0, 3, 7, 0, 7, 4,
            4, 7, 6, 4, 6, 5,
            3, 7, 2, 7, 2, 6,
            0, 4, 1, 4, 1, 5
    };

    public Cube(Context c) {
        loadOBJ = new LoadOBJ(c.getResources().openRawResource(R.raw.iron_man));
       List<Float> list = LoadOBJ.getObjectVertex();
        cubeCoords = new float[list.size()];
        for (int i = 0; i < list.size(); i++)
            cubeCoords[i] = list.get(i).floatValue();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(cubeCoords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());

        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(cubeCoords);
        vertexBuffer.position(0);

        byteBuffer = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        colorBuffer = byteBuffer.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        drawListBuffer = ByteBuffer.allocateDirect(drawOrder.length);
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }

    public void draw(GL10 gl) {
        // Enable vertex array buffer to be used during rendering
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Tell openGL where the vertex array buffer is
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);


        // Enable color array buffer to be used during rendering
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        // Tell openGL where the color array buffer is
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        // Draw each plane as a pair of triangles, based on the drawListBuffer information
        gl.glDrawElements(GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_BYTE, drawListBuffer);
    }
}
