package com.praise.Haneasy.Screens.Learning.Sentences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Sentences.Sentence.GenModal;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import static com.praise.Haneasy.Splash.APP_URL;

public class SentenceGen extends AppCompatActivity implements View.OnClickListener {
    ImageView back , clear;
    CardView  submit;
    ConstraintLayout play;
    Button one, two, three, four;
    TextView result;
    int buttonClick = 0;
    String resultText;
    TextView question_pos;
    ArrayList<GenModal> modals;
    int  questionsAttempted = 1, currentPos = 0;
    String question;
    int current_score = 0;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_gen);
        initialize();
        setClicks();
        Intent intent = getIntent();
        int id = intent.getIntExtra("level_id", 1);
        getData(id);
        setData(currentPos);

    }

    private void setData(int pos) {
        if (questionsAttempted > modals.size()) {
        showScore();
        } else {
            resetAll();
            question_pos.setText("Question " + questionsAttempted + "/" + modals.size());
            question = modals.get(pos).getQuestion();
            one.setText(modals.get(pos).getOption1());
            two.setText(modals.get(pos).getOption2());
            three.setText(modals.get(pos).getOption3());
            four.setText(modals.get(pos).getOption4());

        }
    }


    private void showScore() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SentenceGen.this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottomsheet, (ConstraintLayout) findViewById(R.id.layout_result));
        TextView score = view.findViewById(R.id.score);
        CardView finish = view.findViewById(R.id.finish);
        score.setText(current_score + "/" + modals.size());
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), SentencePage.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    private void showResult(String quest, String ans, String status) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SentenceGen.this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.result_sheet, (ConstraintLayout) findViewById(R.id.layout_result));
        TextView question = view.findViewById(R.id.expected);
        TextView userAns = view.findViewById(R.id.user_ans);
        TextView title = view.findViewById(R.id.textView3);
        CardView next = view.findViewById(R.id.finish);
        question.setText("Expected : "+quest);
        userAns.setText("Result : "+ans);
        title.setText(status);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                questionsAttempted++;
                currentPos++;
                setData(currentPos);
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
        PutData putData = new PutData(APP_URL+"GenQuestions.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    System.out.println(jsonObject);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    modals = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        Log.d("check",obj+"");
                        modals.add(new GenModal(obj.getString("question"),
                                obj.getString("A"),
                                obj.getString("B"),
                                obj.getString("C"),
                                obj.getString("D")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private void setClicks() {
        back.setOnClickListener(this::onClick);
        play.setOnClickListener(this::onClick);
        clear.setOnClickListener(this::onClick);
        one.setOnClickListener(this::onClick);
        two.setOnClickListener(this::onClick);
        three.setOnClickListener(this::onClick);
        four.setOnClickListener(this::onClick);
        submit.setOnClickListener(this::onClick);

    }

    private void initialize() {
        back = findViewById(R.id.back);
        one = findViewById(R.id.gen_one);
        two = findViewById(R.id.gen_two);
        three = findViewById(R.id.gen_three);
        four = findViewById(R.id.gen_four);
        play = findViewById(R.id.player);
        clear = findViewById(R.id.clear);
        submit = findViewById(R.id.submit);

        question_pos = findViewById(R.id.question_position);
        result = findViewById(R.id.result);
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

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), SentencePage.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.player:
                playQuestion();
                break;
            case R.id.clear:
                resetAll();
                break;
            case R.id.submit:
                checkAnswer(resultText);
                break;

            case R.id.gen_one:
            case R.id.gen_two:
            case R.id.gen_three:
            case R.id.gen_four:
                textHandler(view.getId());
                buttonClick++;
                break;
        }
    }

    private void playQuestion() {
        String audioTxt = question;
        textToSpeech.speak(audioTxt, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void textHandler(int id) {
        if(buttonClick >=4){

        }else {
            switch (id) {
                case R.id.gen_one:
                    resultText = resultText + one.getText().toString().trim();

                    break;
                case R.id.gen_two:
                    resultText = resultText + two.getText().toString().trim();

                    break;
                case R.id.gen_three:
                    resultText = resultText + three.getText().toString().trim();

                    break;
                case R.id.gen_four:
                    resultText = resultText  + four.getText().toString().trim();

                    break;

            }
            result.setText(resultText);
        }
    }

    private void checkAnswer(String text) {
        String status ;
        if(question.equals(text.trim())){
           // Toast.makeText(getApplicationContext(),"Correct", Toast.LENGTH_SHORT).show();
            status = "Correct";
            current_score++;
        }
        else {
           // Toast.makeText(getApplicationContext(),"Wrong", Toast.LENGTH_SHORT).show();
            status = "Wrong";
        }
        showResult(question,text,status);
    }

    private void resetAll() {
        result.setText("");
        resultText = "";
        buttonClick = 0;

    }

}