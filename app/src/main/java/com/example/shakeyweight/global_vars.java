package com.example.shakeyweight;
import android.app.Application;
import android.content.Context;

public class global_vars extends Application{
    static int shakes;
    static int rate;
    static int amountOfShakerMod;
    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }


    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public int getShakes(){
        return shakes;
    }

    public void setShakes(int amount){
        shakes = amount;
    }

    public int getRate(){
        return rate;
    }

    public void addToShakes(int amount){
        shakes += amount;
    }

    public void subtractFromShakes(int amount){
        shakes -= amount;
    }

    public void setRate(int amount){
        rate = amount;
    }

    public void addToRate(int amount){
        rate += amount;
    }

    public int getAmountOfShakerMod() {
        return amountOfShakerMod;
    }

    public void setAmountOfShakerMod(int amountOfShakerMod) {
        global_vars.amountOfShakerMod = amountOfShakerMod;
    }
    public void addToAmountOfShakerMod(int amount){
        amountOfShakerMod+=amount;
    }

}
