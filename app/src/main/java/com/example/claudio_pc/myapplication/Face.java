package com.example.claudio_pc.myapplication;

import java.util.ArrayList;

public class Face {
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();

    public Face()
    {

    }

    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }
}