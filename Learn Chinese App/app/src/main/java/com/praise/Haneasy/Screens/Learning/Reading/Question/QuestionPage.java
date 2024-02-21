package com.praise.Haneasy.Screens.Learning.Reading.Question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Reading.ReadingActivity;
import com.praise.Haneasy.Screens.Learning.Reading.QuizModal;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This is the reading exercise question page...
 * it checks if the selected answer of the user is correct and notifies them when wrong...
 * count down is set to 60s
 */
public class QuestionPage extends AppCompatActivity {
    final int oneMin = 1 * 60 * 1000;
    ImageView imageView;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    RadioGroup radioGroup;
    TextView question_text, question_pos;
    RadioButton option_A, option_B, option_C, option_D;
    int current_score = 0, questionsAttempted = 1, currentPos;
    int total;
    CardView nextBtn;
    private ArrayList<QuizModal> quizModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);
        initialize();
        Intent intent = getIntent();
        int id = intent.getIntExtra("question_id", 1);
        getQuizFromDatabase(id);
        currentPos = 0;
        setQuizData(currentPos);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionsAttempted++;
                currentPos++;
                setQuizData(currentPos);
            }
        });
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
                       // Toast.makeText(getApplicationContext(), "c", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.option_d:
                        answerController(option_D);
                       // Toast.makeText(getApplicationContext(), "d", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void getQuizFromDatabase(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL+"Questions.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String value = putData.getResult();
                Log.d("questions", value);
                quizModalArrayList = new ArrayList<QuizModal>();
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    String question = jsonObject.get("data").toString();
                    Log.d("questions", question);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        org.json.JSONArray options = (org.json.JSONArray) obj.get("options");
                        quizModalArrayList.add(new QuizModal(obj.getString("question"),
                                new JSONObject(options.get(0).toString()).get("A").toString(),
                                new JSONObject(options.get(0).toString()).get("B").toString(),
                                new JSONObject(options.get(0).toString()).get("C").toString(),
                                new JSONObject(options.get(0).toString()).get("D").toString(),
                                new JSONObject(options.get(0).toString()).get("answer").toString()));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showScore() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuestionPage.this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottomsheet, (ConstraintLayout) findViewById(R.id.layout_result));
        TextView score = view.findViewById(R.id.score);
        CardView finish = view.findViewById(R.id.finish);
        score.setText(current_score + "/" + quizModalArrayList.size());
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), ReadingActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    private void answerController(RadioButton option) {
        String ans = quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase();
        if (ans.equals(option.getText().toString().toLowerCase().trim())) {
            current_score++;
            option.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        } else {
            option.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_wrong));
            setRightAnswer(ans);
        }
    }

    private void setRightAnswer(String ans) {
        if (ans.equals(option_A.getText().toString().toLowerCase().trim())) {
            option_A.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        } else if (ans.equals(option_B.getText().toString().toLowerCase().trim())) {
            option_B.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        } else if (ans.equals(option_C.getText().toString().toLowerCase().trim())) {
            option_C.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        } else if (ans.equals(option_D.getText().toString().toLowerCase().trim())) {
            option_D.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_right));
        }
    }

    private void setQuizData(int pos) {
        radioGroup.setEnabled(true);
        if (questionsAttempted > quizModalArrayList.size()) {
            showScore();
            countDownTimer.cancel();
        } else {
            question_pos.setText("Question " + questionsAttempted + "/" + quizModalArrayList.size());
            question_text.setText(quizModalArrayList.get(pos).getQuestion());
            option_A.setText(quizModalArrayList.get(pos).getOption1());
            option_B.setText(quizModalArrayList.get(pos).getOption2());
            option_C.setText(quizModalArrayList.get(pos).getOption3());
            option_D.setText(quizModalArrayList.get(pos).getOption4());
            resetRadioButton();
            progressHandler();
        }
    }

    private void resetRadioButton() {
        radioGroup.clearCheck();
        radioGroup.check(0);
        option_A.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
        option_B.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
        option_C.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
        option_D.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_bg));
    }

    private void progressHandler() {
        progressBar.setProgress(0);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(oneMin, 1000) {
            @Override
            public void onTick(long l) {
                long finishedSeconds = oneMin - l;
                total = (int) (((float) finishedSeconds / (float) oneMin) * 100.0);
                progressBar.setProgress(total);
            }

            @Override
            public void onFinish() {
                total = 0;
                questionsAttempted++;
                currentPos++;
                setQuizData(currentPos);
                Toast.makeText(getApplicationContext(), "time up", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
    }

    private void initialize() {
        imageView = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        radioGroup = findViewById(R.id.radioGroup);
        option_A = findViewById(R.id.option_a);
        option_B = findViewById(R.id.option_b);
        option_C = findViewById(R.id.option_c);
        option_D = findViewById(R.id.option_d);
        question_text = findViewById(R.id.question_text);
        question_pos = findViewById(R.id.question_position);
        nextBtn = findViewById(R.id.next);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        startActivity(new Intent(getApplicationContext(), ReadingActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }
}