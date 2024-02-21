package com.praise.Haneasy.Screens.Info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Bot.ChatBot;
import com.praise.Haneasy.Screens.Culture.Types.CultureType;
import com.praise.Haneasy.Screens.Forum.ViewPost;
import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.praise.Haneasy.Screens.Welcome;

/**
 *  This activity gets user info through shared preferences
 */
public class UserInfo extends AppCompatActivity {
    private static String IS_LOGGED_IN = "isLogIn";
    BottomNavigationView bottomNavigationView;
    CardView logout, aboutUs;
    TextView name, email;
    Dialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initialize();
        getData();
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.about_us);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogOut();
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exercises:
                        startActivity(new Intent(getApplicationContext(), LearningPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.culture:
                        startActivity(new Intent(getApplicationContext(), CultureType.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.post:
                        startActivity(new Intent(getApplicationContext(), ViewPost.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.ai:
                        startActivity(new Intent(getApplicationContext(), ChatBot.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info:
                        return true;
                }
                return false;
            }
        });
    }

    private void getData() {
        SharedPreferences preferences = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE);
        String nme = preferences.getString("user_name", "");
        String mail = preferences.getString("user_mail", "");
        name.setText(nme);
        email.setText(mail);
    }

    private void handleLogOut() {
        builder.setTitle("Sign out")
                .setMessage("Do you want to sign out?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE).edit();
                        editor.putBoolean("isLog", false);
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(), Welcome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();

    }


    private void initialize() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.info);
        logout = findViewById(R.id.logout);
        aboutUs = findViewById(R.id.about);
        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(true);
        builder = new AlertDialog.Builder(this);
    }

    @Override
    public void onBackPressed() {

    }
}