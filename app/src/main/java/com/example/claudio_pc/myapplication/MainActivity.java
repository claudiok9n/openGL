package com.example.claudio_pc.myapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    GLSurfaceView glView;
    private static Screen screen = new Screen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        glView = new MyGlSurfaceView(this);


        Display display = getWindowManager().getDefaultDisplay();
        //String displayName = display.getName();  // minSdkVersion=17+
        // Tamaño en píxeles
        Point size = new Point();
        display.getSize(size);
        screen.setHeight(size.y);
        screen.setWidth(size.x);

        setContentView(glView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        glView.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        glView.onPause();
    }

}
