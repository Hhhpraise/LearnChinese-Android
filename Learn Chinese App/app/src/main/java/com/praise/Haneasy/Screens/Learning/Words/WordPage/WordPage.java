package com.praise.Haneasy.Screens.Learning.Words.WordPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Words.WordActivity;
import com.praise.Haneasy.Screens.Learning.Words.WordPage.Adapter.Word;
import com.praise.Haneasy.Screens.Learning.Words.WordPage.Adapter.WordAdapter;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * Loads all words present in a particular category...
 */
public class WordPage extends AppCompatActivity implements MyClickInterface {
    TextView name;
    ImageView back;
    RecyclerView recyclerView;
    WordAdapter adapter;
    ArrayList<Word> words;
    TextToSpeech textToSpeech;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_page);
        initialize();
        getIntentData();
        getData(id);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new WordAdapter(this,words,this::onItemClick);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getData(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL+"WordList.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    words = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        words.add(new Word(Integer.parseInt(obj.getString("id")),
                                obj.getString("english_text"),
                                obj.getString("chinese_char"),
                                obj.getString("pinyin")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            }
    }

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        String category = intent.getStringExtra("name");
        name.setText(category);
    }

    private void initialize() {
        name = findViewById(R.id.name);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.word_recycler);
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
        startActivity(new Intent(getApplicationContext(), WordActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }

    @Override
    public void onItemClick(int position) {
        String output = words.get(position).getChineseCharacter();
        textToSpeech.speak(output, TextToSpeech.QUEUE_FLUSH, null);
    }
}