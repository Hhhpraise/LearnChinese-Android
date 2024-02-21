package com.praise.Haneasy.Screens.Learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Bot.ChatBot;
import com.praise.Haneasy.Screens.Culture.Types.CultureType;
import com.praise.Haneasy.Screens.Forum.ViewPost;
import com.praise.Haneasy.Screens.Learning.Gestures.IntroPage;
import com.praise.Haneasy.Screens.Learning.Sentences.SentencePage;
import com.praise.Haneasy.Screens.Learning.Words.WordActivity;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.praise.Haneasy.Screens.Learning.Pinyin.PinyinClass;
import com.praise.Haneasy.Screens.Learning.Videos.VideoActivity;
import com.praise.Haneasy.Screens.Learning.Learning_Adapter.LearnEntities;
import com.praise.Haneasy.Screens.Learning.Learning_Adapter.LearningAdapter;
import com.praise.Haneasy.Screens.Learning.Listening.ListeningActivity;
import com.praise.Haneasy.Screens.Learning.Reading.ReadingActivity;
import com.praise.Haneasy.Screens.Info.UserInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Shows Learning Entities on the Learn page
 */

public class LearningPage extends AppCompatActivity implements MyClickInterface {
    LearningAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<LearnEntities> learnEntities;
    BottomNavigationView bottomNavigationView;
    TextView name;
    TextView currentDay;
    private static String IS_LOGGED_IN = "isLogIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_page);
        initialize();
        learnEntities = new ArrayList<LearnEntities>();
        learnEntities.add(new LearnEntities(1,"Pinyin","https://cdn.pixabay.com/photo/2014/10/04/16/55/wooden-cubes-473703_960_720.jpg"));
        learnEntities.add(new LearnEntities(2,"Listening Exercise","https://images.unsplash.com/photo-1538561554559-99b753091825?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
        learnEntities.add(new LearnEntities(3,"Learn Words","https://images.pexels.com/photos/9052325/pexels-photo-9052325.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        learnEntities.add(new LearnEntities(4,"Reading Exercise","https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
        learnEntities.add(new LearnEntities(5,"Videos","https://images.unsplash.com/photo-1529939620248-273fd31a7cdf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1463&q=80"));
        learnEntities.add(new LearnEntities(6,"Sentence Generator","https://images.pexels.com/photos/4065623/pexels-photo-4065623.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        learnEntities.add(new LearnEntities(7,"Chinese Number Gestures","https://www.thatsmags.com/image/view/201702/chinese-hand-gesture-sign-numbers.jpg"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LearningAdapter(getApplicationContext(), learnEntities, this);
        recyclerView.setAdapter(adapter);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exercises:
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
                        startActivity(new Intent(getApplicationContext(), UserInfo.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void initialize() {
        recyclerView = findViewById(R.id.recycler);
        bottomNavigationView= findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.exercises);
        name = findViewById(R.id.user_name);
        SharedPreferences preferences = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE);
        String nme = preferences.getString("user_name", "");
        name.setText("Welcome " +nme);
        currentDay = findViewById(R.id.day);
        currentDay .setText(getCurrentTime());
    }

    @Override
    public void onItemClick(int position) {
        int id = learnEntities.get(position).getId();
        switch (id){
            case 1:
                startActivity(new Intent(getApplicationContext(), PinyinClass.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), ListeningActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 3:
                startActivity(new Intent(getApplicationContext(), WordActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 4:
                startActivity(new Intent(getApplicationContext(), ReadingActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 5:
                startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 6:
                startActivity(new Intent(getApplicationContext(), SentencePage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 7:
                startActivity(new Intent(getApplicationContext(), IntroPage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
        }

    }

    @Override
    public void onBackPressed() {

    }
    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE,MMM d");
        Date date = new Date();
        return formatter.format(date).toString();
    }

}