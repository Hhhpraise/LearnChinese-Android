package com.praise.Haneasy.Screens.Learning.Pinyin.Tones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.praise.Haneasy.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * This activity , shows each tone,chart and examples..
 */
public class ToneDetails extends AppCompatActivity implements View.OnClickListener {
    ArrayList<ToneExample> examples;
    TextView title, toneInfo;
    ImageView back;
    ConstraintLayout one, two;
    TextToSpeech textToSpeech;
    private int id;
    ImageView chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tone_details);
        initialize();
        examples = new ArrayList<>();
        examples.add(new ToneExample("妈", "摸"));
        examples.add(new ToneExample("麻", "膜"));
        examples.add(new ToneExample("马", "抹"));
        examples.add(new ToneExample("杩", "默"));
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        getData(id);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        one.setOnClickListener(this::onClick);
        two.setOnClickListener(this::onClick);

    }

    private void initialize() {
        title = findViewById(R.id.tone_name);
        toneInfo = findViewById(R.id.tone_info);
        back = findViewById(R.id.back);
        one = findViewById(R.id.toneOne);
        two = findViewById(R.id.toneTwo);
        chart = findViewById(R.id.tone_chart);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.CHINESE);
                }
            }
        });
    }

    private void getData(int id) {
        String info = "";
        String name = "";
        int img =0;
        switch (id) {
            case 0:
                info = getResources().getString(R.string.first_tone_detail);
                name = "First Tone";
                img = (R.drawable.first_tone);
                break;
            case 1:
                info = getResources().getString(R.string.sec_tone_detail);
                name = "Rising Tone";
                img = (R.drawable.second_tone);
                break;
            case 2:
                info = getResources().getString(R.string.third_tone_detail);
                name = "Falling-Rising Tone";
                img = (R.drawable.third_tone);
                break;
            case 3:
                info = getResources().getString(R.string.fourth_tone_detail);
                name = "Falling Tone";
                img = (R.drawable.fouth_tone);
                break;
        }
        setData(name, info,img);
    }

    private void setData(String name, String info,int img) {
        title.setText(name);
        toneInfo.setText(info);
        chart.setImageResource(img);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), TonePage.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    @Override
    public void onClick(View view) {
        String text = "";
        switch (view.getId()) {
            case R.id.toneOne:
                text = examples.get(id).getFirst();
                break;
            case R.id.toneTwo:
                text = examples.get(id).getSecond();
                break;
        }
        speak(text);
    }

    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}