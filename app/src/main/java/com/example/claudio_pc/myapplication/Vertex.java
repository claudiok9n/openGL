package com.example.claudio_pc.myapplication;

public class Vertex {
    public Vector3f position, normal;
    public Vector2f textureCoord;

    public Vertex(Vector3f pos, Vector3f norm, Vector2f textCoord)
    {
        position = pos;
        normal = norm;
        textureCoord = textCoord;
    }
}