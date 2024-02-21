package com.praise.Haneasy.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.praise.Haneasy.R;

import java.net.InetAddress;

/**
 * Welcome page...
 * User chooses Login or Sign up button...
 */
public class Welcome extends AppCompatActivity {
    CardView login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initialize();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Sign_Up.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
    }

    private void initialize() {
        login = findViewById(R.id.to_login);
        signup = findViewById(R.id.signup);
    }

    @Override
    public void onBackPressed() {

    }


}