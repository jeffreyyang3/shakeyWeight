package com.example.shakeyweight;
import android.app.Application;
import android.content.Context;

public class global_vars extends Application{
    int shakes;
    double rate;
    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }
    public static Context getContext() {
        return getApplication().getApplicationContext();
    }


}
