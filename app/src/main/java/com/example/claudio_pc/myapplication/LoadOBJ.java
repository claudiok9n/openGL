package com.example.claudio_pc.myapplication;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio_Note on 04/07/2016.
 */
public class LoadOBJ {
    private Resources resource = null;

    public static ObjectMesh load(InputStream _obj, InputStream _mtl) throws IOException {
        InputStream inputStream_Obj = _obj;
        InputStream inputStream_Mtl = _mtl;
        ArrayList<Vector3f> vertex = new ArrayList<Vector3f>();
        ArrayList<Vector2f> TextureCoords = new ArrayList<Vector2f>();
        ArrayList<Vector3f> Normals = new ArrayList<Vector3f>();
        ArrayList<Face> faces = new ArrayList<Face>();

        ObjectMesh objectMesh = new ObjectMesh();

        ArrayList<String> lines = parseOBJ(_obj, _mtl);

        for (String str : lines) {
            if (str == null)
                break;
            if (str.startsWith("v "))
                vertex.add(new Vector3f(Float.valueOf(str.split(" ")[1]), Float.valueOf(str.split(" ")[2]), Float.valueOf(str.split(" ")[3])));
            if (str.startsWith("vt "))
                TextureCoords.add(new Vector2f(Float.valueOf(str.split(" ")[1]),Float.valueOf(str.split(" ")[2])));
            if (str.startsWith("vn "))
                Normals.add(new Vector3f(Float.valueOf(str.split(" ")[1]), Float.valueOf(str.split(" ")[2]), Float.valueOf(str.split(" ")[3])));
            if (str.startsWith("f ")) {
                //Log.d("OBJToolkit", line);
                Face f = new Face();
                String[] faceVertexArray = str.split(" ");

                for (int index = 1; index < faceVertexArray.length; index++) {
                    String[] valueArray = faceVertexArray[index].split("/");

                    if (TextureCoords.size() > 0)
                        f.addVertex(new Vertex(vertex.get(Integer.valueOf(valueArray[0]) - 1), Normals.get(Integer.valueOf(valueArray[2]) - 1), TextureCoords.get(Integer.valueOf(valueArray[1]) - 1)));
                    else
                        f.addVertex(new Vertex(vertex.get(Integer.valueOf(valueArray[0]) - 1), Normals.get(Integer.valueOf(valueArray[2]) - 1), new Vector2f(0, 0)));
                }
                faces.add(f);
            }
        }

        vertex = null;
        Normals = null;
        TextureCoords = null;

        ArrayList<Vector3f> VBOVertices = new ArrayList<Vector3f>();
        ArrayList<Vector2f> VBOTextureCoords = new ArrayList<Vector2f>();
        ArrayList<Vector3f> VBONormals = new ArrayList<Vector3f>();
        ArrayList<Integer> VBOIndices = new ArrayList<Integer>();

        Log.d("OBJToolkit", "About to reorganize each point of data");
        int counter = 0;
        for (Face f : faces)
        {
            for (Vertex v : f.vertices)
            {
                VBOVertices.add(v.position);
                VBONormals.add(v.normal);
                VBOTextureCoords.add(v.textureCoord);
                VBOIndices.add(counter);
                counter++;
            }
        }

        faces = null;

        objectMesh.createBuffers(vector3fListToFloatArray(VBOVertices), integerListToShortArray(VBOIndices), null,
                                    vector2fListToFloatArray(VBOTextureCoords), vector3fListToFloatArray(VBONormals));

        VBOVertices = null;
        VBONormals = null;
        VBOTextureCoords = null;
        VBOIndices = null;

        return objectMesh;
    }

    private static ArrayList<String> parseOBJ(InputStream _obj, InputStream _mtln) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(_obj));
        ArrayList<String> lines = new ArrayList<String>();
        while(reader.ready())
        {
            lines.add(reader.readLine());
        }
        reader.close();
        reader = null;
        return lines;
    }

    /*private void openFileMtl(){
        try{
            String str="";
            //StringBuffer buf = new StringBuffer();
            InputStream is = inputStream_Mtl;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is!=null) {
                while ((str = reader.readLine()) != null) {
                    //buf.append(str + "\n" );

                    //if(str.startsWith("v "))
                    //    getVertex(str);
                }
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*private void setVertex(String _vertex){
        String vertexTemp = _vertex.replace("v ", "").trim();
        String[] vertexArray = vertexTemp.replace(" ", ", ").split(", ");
        vertex.add(Float.valueOf(vertexArray[0]));
        vertex.add(Float.valueOf(vertexArray[1]));
        vertex.add(Float.valueOf(vertexArray[2]));
    }*/

    /*public static List<Float> getObjectVertex(){
        return vertex;
    }*/

    public static float[] vector3fListToFloatArray(ArrayList<Vector3f> list)
    {
        Log.d("OBJToolkit", "Converting ArrayList Vector3f");
        float[] returnArray = new float[list.size() * 3];
        int counter = 0;
        for (Vector3f v : list)
        {
            returnArray[counter] = v.x;
            counter++;
            returnArray[counter] = v.y;
            counter++;
            returnArray[counter] = v.z;
            counter++;
        }

        return returnArray;
    }

    public static short[] integerListToShortArray(ArrayList<Integer> list)
    {
        Log.d("OBJToolkit", "Converting ArrayList Integer");
        short[] returnArray = new short[list.size()];
        int counter = 0;
        for (int i : list)
        {
            returnArray[counter] = (short)i;
            counter++;
        }
        return returnArray;
    }

    public static float[] vector2fListToFloatArray(ArrayList<Vector2f> list)
    {
        Log.d("OBJToolkit", "Converting ArrayList Vector2f");
        float[] returnArray = new float[list.size() * 2];
        int counter = 0;
        for (Vector2f v : list)
        {
            returnArray[counter] = v.x;
            counter++;
            returnArray[counter] = v.y;
            counter++;
        }

        return returnArray;
    }

}
