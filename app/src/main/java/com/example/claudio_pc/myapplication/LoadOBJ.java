package com.example.claudio_pc.myapplication;

import android.content.res.Resources;
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
    private InputStream inputStream_Obj = null;
    private InputStream inputStream_Mtl = null;
    private static List<Float> vertex = new ArrayList<Float>();

    public LoadOBJ(InputStream _obj, InputStream _mtl) {
        inputStream_Obj = _obj;
        inputStream_Mtl = _mtl;
        IniObject();
    }

    private void IniObject(){
        if(inputStream_Obj != null)
            openFileObj();
        if(inputStream_Mtl != null)
            openFileMtl();
    }

    private void openFileObj() {
        try{
            String str="";
            //StringBuffer buf = new StringBuffer();
            InputStream is = inputStream_Obj;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is!=null) {
                    while ((str = reader.readLine()) != null) {
                        //buf.append(str + "\n" );

                        if(str.startsWith("v "))
                            getVertex(str);
                    }
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFileMtl(){
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
    }

    private void getVertex(String _vertex){
        String vertexTemp = _vertex.replace("v ", "").trim();
        String[] vertexArray = vertexTemp.replace(" ", ", ").split(", ");
        vertex.add(Float.valueOf(vertexArray[0]));
        vertex.add(Float.valueOf(vertexArray[1]));
        vertex.add(Float.valueOf(vertexArray[2]));
    }

    public static List<Float> getObjectVertex(){
        return vertex;
    }

}
