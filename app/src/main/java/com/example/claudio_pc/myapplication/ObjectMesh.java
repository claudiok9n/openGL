package com.example.claudio_pc.myapplication;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class ObjectMesh {
    Bitmap bitmap = null;

    private FloatBuffer verticesBuffer;
    private ShortBuffer indicesBuffer;
    private int numOfIndices = -1;
    private float[] rgba = new float[] {1.0f, 1.0f, 1.0f, 1.0f};
    private FloatBuffer colorBuffer;
    private FloatBuffer mTextureBuffer;
    private int mTextureId = -1;
    private Bitmap mBitmap;
    private boolean mShouldLoadTexture = false;

    public float x = 0, y = 0, z = 0, rx = 0, ry = 0, rz = 0;

    public ObjectMesh() {

    }

    public void draw(GL10 gl)
    {
        //Log.d("Mesh", "About to render mesh");
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
        if (colorBuffer != null)
        {
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }

        if (mShouldLoadTexture)
        {
            loadGLTexture(gl);
            mShouldLoadTexture = false;
        }

        if (mTextureId != -1 && mTextureBuffer != null)
        {
            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
        }

        gl.glTranslatef(x, y, z);
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);
        gl.glDrawElements(GL10.GL_TRIANGLE_FAN, numOfIndices, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        if (mTextureId != -1 && mTextureBuffer != null)
        {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    public void setTexture(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void createBuffers(float[] vertices, short[] indices, float[] colors, float[] textureCoords, float[] normals)
    {
        Log.d("MeshCreateBuffers", "Vertices: " + floatArrayToString(vertices));
        setVertices(vertices);
        Log.d("MeshCreateBuffers", "Indices: " + shortArrayToString(indices));
        setIndices(indices);
        if (colors != null)
            setColors(colors);
        setTextureCoordinates(textureCoords);
        Log.d("MeshCreateBuffers", "Texture Coors: " + floatArrayToString(textureCoords));
    }

    public String floatArrayToString(float[] array)
    {
        String returnString = "";
        for (int i = 0; i < array.length; i++)
        {
            returnString += array[i];
        }
        return returnString;
    }

    public String shortArrayToString(short[] array)
    {
        String returnString = "";
        for (int i = 0; i < array.length; i++)
        {
            returnString += array[i];
        }
        return returnString;
    }

    protected void setVertices(float[] vertices)
    {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffer = vbb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);
    }

    protected void setIndices(short[] indices)
    {
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indicesBuffer = ibb.asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);
        numOfIndices = indices.length;
    }

    protected void setColor(float red, float green, float blue, float alpha)
    {
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
    }

    protected void setColors(float[] colors)
    {
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    protected void setTextureCoordinates(float[] textureCoords)
    {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mTextureBuffer = byteBuf.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);
    }

    public void loadBitmap(Bitmap bitmap)
    {
        this.mBitmap = bitmap;
        mShouldLoadTexture = true;
    }

    private void loadGLTexture(GL10 gl)
    {
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        mTextureId = textures[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
    }
}