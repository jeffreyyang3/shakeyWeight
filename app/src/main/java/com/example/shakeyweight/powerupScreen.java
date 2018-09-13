package com.example.shakeyweight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class powerupScreen extends AppCompatActivity {

    global_vars gv;
    final int shakerModDefaultCost = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerup_screen);
        gv = (global_vars) getApplicationContext();
    }


    protected void onButtonClick(View v){
        switch(v.getId()){
            case R.id.button0:
                addShakerMod();
                break;
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }

    private void addShakerMod(){
        if(gv.getShakes()>shakerModDefaultCost+gv.getAmountOfShakerMod()){
            gv.subtractFromShakes(shakerModDefaultCost+gv.getAmountOfShakerMod());
            gv.addToAmountOfShakerMod(5);//TODO change this value later
            gv.addToRate(1);
            Toast.makeText(this, "Dean Rocks :D", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "You must have at least " + shakerModDefaultCost+gv.getAmountOfShakerMod(), Toast.LENGTH_SHORT).show();
        }

    }
}
