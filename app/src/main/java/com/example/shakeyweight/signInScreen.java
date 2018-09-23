package com.example.shakeyweight;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.widget.TextView;
import android.widget.Toast;

import com.example.shakeyweight.MainActivity;
import com.example.shakeyweight.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signInScreen extends AppCompatActivity {

    private EditText inputEmail, inputPassword;

    private FirebaseAuth auth;
    private TextView userInfo;
    private global_vars gv;

    private Button btnSignup, btnLogin, btnReset, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance



        // set the view now
        setContentView(R.layout.activity_login);





        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        logout = (Button) findViewById(R.id.logout);
        userInfo = (TextView) findViewById(R.id.userInfo);




        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        gv = (global_vars) getApplicationContext();
        auth = gv.getAuth();


        if (auth.getCurrentUser() != null) {

            btnLogin.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            inputEmail.setVisibility(View.GONE);
            inputPassword.setVisibility(View.GONE);
            btnSignup.setVisibility(View.GONE);
            btnReset.setVisibility(View.GONE);
            userInfo.setVisibility(View.VISIBLE);
            String email = auth.getCurrentUser().getEmail();

            int index = email.indexOf('@');
            userInfo.setText("Logged in as " + email.substring(0,index));


        }
        else{

            btnLogin.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            inputEmail.setVisibility(View.VISIBLE);
            inputPassword.setVisibility(View.VISIBLE);
            btnSignup.setVisibility(View.VISIBLE);
            btnReset.setVisibility(View.VISIBLE);
            userInfo.setVisibility(View.GONE);

        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(signInScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signInScreen.this, SignupActivity.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }



                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signInScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError("password too short");
                                    } else {
                                        Toast.makeText(signInScreen.this, "wrong", Toast.LENGTH_LONG).show();
                                    }
                                } else {


                                    Intent intent = new Intent(signInScreen.this, MainActivity.class);
                                    startActivity(intent);



                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
