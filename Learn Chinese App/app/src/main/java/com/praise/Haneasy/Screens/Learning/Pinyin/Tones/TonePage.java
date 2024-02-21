package com.praise.Haneasy.Screens.Learning.Pinyin.Tones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Pinyin.PinyinClass;

/**
 * This activity shows the introduction to the Chinese tones
 */
public class TonePage extends AppCompatActivity implements View.OnClickListener {
    ConstraintLayout one, two, three, four;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tone_page);
        initialize();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        one.setOnClickListener(this::onClick);
        two.setOnClickListener(this::onClick);
        three.setOnClickListener(this::onClick);
        four.setOnClickListener(this::onClick);

    }

    private void initialize() {
        back = findViewById(R.id.back);
        one = findViewById(R.id.toneOne);
        two = findViewById(R.id.toneTwo);
        three = findViewById(R.id.toneThree);
        four = findViewById(R.id.toneFour);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), PinyinClass.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }

    /**
     * id for the four tones ranges from 0-3
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ToneDetails.class);
        int id = 0;
        switch (view.getId()) {
            case R.id.toneOne:
                id = 0;
                break;
            case R.id.toneTwo:
                id = 1;
                break;
            case R.id.toneThree:
                id = 2;
                break;
            case R.id.toneFour:
                id = 3;
                break;
            //handle multiple view click events
        }
        intent.putExtra("id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }

    private void makeToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}