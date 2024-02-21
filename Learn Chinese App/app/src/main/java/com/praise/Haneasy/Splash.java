package com.praise.Haneasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.praise.Haneasy.Screens.Welcome;

import org.opencv.android.OpenCVLoader;

/**
 * This is the first Screen in the app...
 * Make sure the server is running
 * Replace the APP URL to the corresponding running server..
 */

public class Splash extends AppCompatActivity {
    private static String IS_LOGGED_IN = "isLogIn";
    public static String APP_URL = "http://192.168.1.12/ChineseMadeEasy/";
    CardView start;
    static {
        if(!OpenCVLoader.initDebug())
        {
            Log.d("SKK","Praise");
            System.out.println("I am working...");
        }
        else {
            Log.d("SKK","Praise");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        SharedPreferences preferences = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_logged_in = preferences.getBoolean("isLog", false);
                if (is_logged_in) {
                    startActivity(new Intent(getApplicationContext(), LearningPage.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                } else {
                    startActivity(new Intent(getApplicationContext(), Welcome.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                }
            }
        });
    }

    private void initialize() {
        start = findViewById(R.id.get_started);
    }

    @Override
    public void onBackPressed() {

    }
}