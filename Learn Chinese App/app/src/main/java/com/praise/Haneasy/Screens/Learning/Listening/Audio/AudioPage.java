package com.praise.Haneasy.Screens.Learning.Listening.Audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Listening.ListeningActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This page checks if the ans selected by the user is correct
 * and notifies them when wrong...
 */
public class AudioPage extends AppCompatActivity {
    ImageView imageView;
    RadioGroup radioGroup;
    RadioButton option_A, option_B, option_C, option_D;
    CardView nextBtn, playBtn;
    TextView question_pos;
    int current_score = 0, questionsAttempted = 1, currentPos = 0;
    private ArrayList<AudioModal> modals;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_page);
        Intent intent = getIntent();
        int id = intent.getIntExtra("level_id", 1);
        initialize();
        getData(id);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          onBackPressed();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioGroup.setEnabled(false);
                switch (i) {
                    case R.id.option_a:
                        answerController(option_A);
                      //  Toast.makeText(getApplicationContext(), "a", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.option_b:
                        answerController(option_B);
                      //  Toast.makeText(getApplicationContext(), "b", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.option_c:
                        answerController(option_C);
                      //  Toast.makeText(getApplicationContext(), "c", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.option_d:
                        answerController(option_D);
                       // Toast.makeText(getApplicationContext(), "d", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        setData(currentPos);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionsAttempted++;
                currentPos++;
                setData(currentPos);
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String audioTxt=modals.get(currentPos).word;
                textToSpeech.speak(audioTxt, TextToSpeech.QUEUE_FLUSH, null);
               // Toast.makeText(getApplicationContext(),"playing "+audioTxt,Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void answerController(RadioButton option) {
        String ans = modals.get(currentPos).getAnswer().trim().toLowerCase();
        if (ans.equals(option.getText().toString().toLowerCase().trim())) {
            current_score++;
            option.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        }
        else{
            option.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_wrong));
            setRightAnswer(ans);
        }
    }

    private void setRightAnswer(String ans) {
        if(ans.equals(option_A.getText().toString().toLowerCase().trim())){
            option_A.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        }
        else if(ans.equals(option_B.getText().toString().toLowerCase().trim())){
            option_B.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        }
        else if(ans.equals(option_C.getText().toString().toLowerCase().trim())){
            option_C.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        }
        else if(ans.equals(option_D.getText().toString().toLowerCase().trim())){
            option_D.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        }
    }

    private void setData(int pos) {
        if (questionsAttempted > modals.size()) {
            showScore();
        } else {
            resetRadioButton();
            question_pos.setText("Question " + questionsAttempted + "/" + modals.size());
            option_A.setText(modals.get(pos).getOption1());
            option_B.setText(modals.get(pos).getOption2());
            option_C.setText(modals.get(pos).getOption3());
            option_D.setText(modals.get(pos).getOption4());

        }

    }

    private void resetRadioButton() {
        radioGroup.setEnabled(true);
        radioGroup.clearCheck();
        radioGroup.check(0);
        option_A.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
        option_B.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
        option_C.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
        option_D.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
    }

    private void showScore() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AudioPage.this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottomsheet, (ConstraintLayout) findViewById(R.id.layout_result));
        TextView score = view.findViewById(R.id.score);
        CardView finish = view.findViewById(R.id.finish);
        score.setText(current_score + "/" + modals.size());
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), ListeningActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    private void getData(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL+"audioQuestion.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String value = putData.getResult();
                Log.d("questions", value);
                modals = new ArrayList<AudioModal>();
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    // 遍历jsons数组对象，
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        Log.d("questions", obj.toString());
                        org.json.JSONArray options = (org.json.JSONArray) obj.get("options");
                        modals.add(new AudioModal(obj.getString("question"),
                                new JSONObject(options.get(0).toString()).get("A").toString(),
                                new JSONObject(options.get(0).toString()).get("B").toString(),
                                new JSONObject(options.get(0).toString()).get("C").toString(),
                                new JSONObject(options.get(0).toString()).get("D").toString(),
                                new JSONObject(options.get(0).toString()).get("answer").toString()));
                        Log.d("questions", modals.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void initialize() {
        imageView = findViewById(R.id.back);
        radioGroup = findViewById(R.id.radioGroup);
        option_A = findViewById(R.id.option_a);
        option_B = findViewById(R.id.option_b);
        option_C = findViewById(R.id.option_c);
        option_D = findViewById(R.id.option_d);
        nextBtn = findViewById(R.id.next);
        playBtn = findViewById(R.id.play);
        question_pos = findViewById(R.id.question_position);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), ListeningActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }
}