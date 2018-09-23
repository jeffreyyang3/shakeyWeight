package com.example.shakeyweight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.SystemClock;

import org.w3c.dom.Text;

import safety.com.br.android_shake_detector.core.ShakeCallback;
import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //FIXME There is a problem with turning the screen sideways, the count gets restarted and the counter gets screwed up and counts to a multiple of the amount of times you turned it sideways.

    private ShakeDetector shakeDetector;// for reference on where i found this code for all the shake detector stuff its here https://github.com/safetysystemtechnology/android-shake-detector
    private int timesShook;
    private TextView counterText;
    private String counterString;
    private long timeElapsedStart;
    private FirebaseAuth auth;
    private MenuItem signin, logout;
    private global_vars gv;


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signIn:
                startActivity(new Intent(this, signInScreen.class));
                return true;

            case R.id.powerups:
                startActivity(new Intent(this, powerupScreen.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("ONCREATE");



        setContentView(R.layout.activity_main);



        gv = (global_vars) getApplicationContext();
        gv.startAuth();
        gv.startDataBase();
        auth = gv.getAuth();

        resetTimeElapsed();//this may not be smart to do on onCreate





        counterText = (TextView) findViewById(R.id.shakeWeightCounterText);
        counterString = Integer.toString(gv.getShakes());
        //counterString = getString(R.string.times_shook) + timesShook;
        counterText.setText(counterString);


        ShakeOptions options = new ShakeOptions()//Fixme when doing about 10 shakes it often records it as 7-9 shakes, it may be a problem if we cheat the user out of shakes, it would be better if the inverse was true because the user wouldn't feel like they were cheated.
                .background(true)//not sure what this does
                .interval(0)//this is for the amount of time before another shake is counted in milliseconds(i think) //important - note that it waits till a force in the opposite direction goes or for the force to go below the sensibility level (i think) to wait for another shake
                .shakeCount(1)//amount of "shakes"(forces i believe) to be considered a shake
                .sensibility(2.5f);//i believe that this is the force of a shake needed to be considered a "force" //i believe there are two ways to go about this, to either have it low so that it can register shakes faster thus not cheating out the player, or for it to be a harder shake sothat the phone absolutely knows when it is being shaked as it would also take more time for it to be detected

        shakeDetector = new ShakeDetector(options).start(this, new ShakeCallback() {
            @Override
            public void onShake() {
                resetTimeElapsed();
                gv.addToShakes(1);
                //counterString = getString(R.string.times_shook) + timesShook;
                counterText.setText(Integer.toString(gv.getShakes()));
                String email = gv.getAuth().getCurrentUser().getEmail();

                int index;
                if(email != null) {
                    index = email.indexOf('@');
                    User user = new User(gv.getShakes());
                    //gv.getDatabaseInfo().child("users").child(email.substring(0,index)).
                      //      child("shakes").setValue(gv.getShakes());
                    gv.getDatabaseInfo().child("users").child(email.substring(0,index)).setValue(user);

                }
                //counterText.setText("bing bong");
                Log.d("event", "onShake");
            }
        });
        //IF YOU WANT JUST IN BACKGROUND
        //this.shakeDetector = new ShakeDetector(options).start(this);
        //maybe important


    }

    private void resetTimeElapsed(){//How do you make listeners
        timeElapsedStart = System.currentTimeMillis();
    }

    private boolean isHotStreakEnded(int amountOfTime){//in millis
        return true;
    }

    /*private void update(){ //I am almost 100% sure that this is wrong, future me watch https://www.youtube.com/watch?v=OojQitoAEXs for game loop
        while(true) {
            isHotStreakEnded(1000);
            SystemClock.sleep(100);
        }
    }*/

}
