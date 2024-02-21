package com.praise.Haneasy.Screens.Learning.Pinyin.Initials;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Pinyin.Adapter.PinyinModal;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.praise.Haneasy.Screens.Learning.Pinyin.Initials.Adapter.InitialAdapter;
import com.praise.Haneasy.Screens.Learning.Pinyin.PinyinClass;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This Activity loads all initials from the database
 * which is set to speak on click
 */
public class InitialsPage extends AppCompatActivity implements MyClickInterface {
    RecyclerView recyclerView;
    ArrayList<PinyinModal> modals;
    InitialAdapter adapter;
    ImageView back;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initials_page);
        initialize();
        getData();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InitialAdapter(this, modals, this);
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL+"Initials.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    modals = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        Log.d("pinyin", obj.toString());
                        modals.add(new PinyinModal(Integer.parseInt(obj.getString("id")),obj.getString("text"),obj.getString("sound")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void initialize() {
        recyclerView = findViewById(R.id.recycler);
        back = findViewById(R.id.back);
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
    public void onItemClick(int position) {
        String output = modals.get(position).getSound();
        textToSpeech.speak(output, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), PinyinClass.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.stay);
    }
}