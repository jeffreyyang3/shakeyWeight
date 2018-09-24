package com.example.shakeyweight;
import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class global_vars extends Application{
    static int shakes;
    static int rate;
    static int amountOfShakerMod;
    private TextView counterText;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseInstance;
    public void setCounterText(TextView t){
        this.counterText = t;

    }
    public TextView getCounterText(){
        return this.counterText;
    }

    public void startAuth(){
        this.auth = FirebaseAuth.getInstance();
    }
    public FirebaseAuth getAuth(){
        return this.auth;
    }
    public void startDataBase(){
        this.firebaseInstance = FirebaseDatabase.getInstance();
    }
    public DatabaseReference getDatabaseInfo(){
        return this.firebaseInstance.getReference();
    }

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
