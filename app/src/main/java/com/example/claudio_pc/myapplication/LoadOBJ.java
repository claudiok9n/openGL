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
    private InputStream inputStream = null;
    private static List<Float> vertex = new ArrayList<Float>();

    public LoadOBJ(InputStream _inputStream) {
        inputStream = _inputStream;
        IniObject();
    }

    private void IniObject(){
        openFile();
    }

    private void openFile() {

        try{
            String str="";
            //StringBuffer buf = new StringBuffer();
            InputStream is = inputStream;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is!=null) {
                    while ((str = reader.readLine()) != null) {
                        //buf.append(str + "\n" );

                        if(str.substring(0, 2).equals("v "))
                            getVertex(str);
                    }
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getVertex(String _vertex){
        String vertexTemp = _vertex.replace("v ", "");
        String[] vertexArray = vertexTemp.replace(" ", ", ").split(", ");
        vertex.add(Float.valueOf(vertexArray[0]));
        vertex.add(Float.valueOf(vertexArray[1]));
        vertex.add(Float.valueOf(vertexArray[2]));
    }

    public static List<Float> getObjectVertex(){
        return vertex;
    }

}
