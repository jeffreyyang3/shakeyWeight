package com.example.shakeyweight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class powerupScreen extends AppCompatActivity {

    global_vars gv;
    final int shakerModDefaultCost = 5;
    final int amountOfButtons = 5;//TODO I feel that this is not the most optimal way to do this, it feels hard coded
    private TextView[] allOfTheTextViews;
    private Button[] allOfTheButtons;
    private String buyForText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerup_screen);
        gv = (global_vars) getApplicationContext();

        buyForText = this.getString(R.string.buy_for);

        initializeButtons(); //sets the buttons in the xml to their respective place in the array
        initializeTextViews(); // sets the text views in the xml to their respective place in the array

        String stringForSetText = buyForText.concat(String.valueOf(gv.getAmountOfShakerMod()+shakerModDefaultCost));//TODO Create a listener to check for changes in the amount
        allOfTheButtons[0].setText(stringForSetText);//^
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
            gv.addToAmountOfShakerMod(5);//FIXME it literally adds a 5 to the amount
            gv.addToRate(1);
            Toast.makeText(this, "Dean Rocks :D", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "You must have at least " + (shakerModDefaultCost+gv.getAmountOfShakerMod()), Toast.LENGTH_SHORT).show();
        }

    }

    private void initializeTextViews(){
        allOfTheTextViews = new TextView[amountOfButtons];
        allOfTheTextViews[0] = findViewById(R.id.textView0);
        allOfTheTextViews[1] = findViewById(R.id.textView1);
        allOfTheTextViews[2] = findViewById(R.id.textView2);
        allOfTheTextViews[3] = findViewById(R.id.textView3);
        allOfTheTextViews[4] = findViewById(R.id.textView4);
    }

    private void initializeButtons(){
        allOfTheButtons = new Button[amountOfButtons];
        allOfTheButtons[0] = findViewById(R.id.button0);
        allOfTheButtons[1] = findViewById(R.id.button1);
        allOfTheButtons[2] = findViewById(R.id.button2);
        allOfTheButtons[3] = findViewById(R.id.button3);
        allOfTheButtons[4] = findViewById(R.id.button4);


    }
}
