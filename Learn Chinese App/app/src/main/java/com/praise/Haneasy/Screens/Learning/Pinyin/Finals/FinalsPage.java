package com.praise.Haneasy.Screens.Learning.Pinyin.Finals;

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
import com.praise.Haneasy.Screens.Learning.Pinyin.Finals.Adapter.CompoundAdapter;
import com.praise.Haneasy.Screens.Learning.Pinyin.Finals.Adapter.CompoundInterface;
import com.praise.Haneasy.Screens.Learning.Pinyin.Finals.Adapter.NasalAdapter;
import com.praise.Haneasy.Screens.Learning.Pinyin.Finals.Adapter.NasalInterface;
import com.praise.Haneasy.Screens.Learning.Pinyin.Finals.Adapter.SimpleAdapter;
import com.praise.Haneasy.Screens.Learning.Pinyin.PinyinClass;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This activity loads all categories of finals from the database
 */
public class FinalsPage extends AppCompatActivity implements MyClickInterface, CompoundInterface, NasalInterface {
    ImageView back;
    RecyclerView simple, compound, nasal;
    ArrayList<PinyinModal> simpleArray, compoundArray, nasalArray;
    SimpleAdapter simpleAdapter;
    CompoundAdapter compoundAdapter;
    NasalAdapter nasalAdapter;
    String type;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finals_page);
        initialize();
        simpleArray = getData(1);
        compoundArray = getData(2);
        nasalArray = getData(3);
        ;

        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "simple";
            }
        });

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, LinearLayout.VERTICAL);
        simple.setLayoutManager(layoutManager);
        simpleAdapter = new SimpleAdapter(this, simpleArray, this);
        simple.setAdapter(simpleAdapter);

        StaggeredGridLayoutManager layoutManage = new StaggeredGridLayoutManager(4, LinearLayout.VERTICAL);
        compound.setLayoutManager(layoutManage);
        compoundAdapter = new CompoundAdapter(this, compoundArray, this);
        compound.setAdapter(compoundAdapter);

        StaggeredGridLayoutManager layoutMan = new StaggeredGridLayoutManager(4, LinearLayout.VERTICAL);
        nasal.setLayoutManager(layoutMan);
        nasalAdapter = new NasalAdapter(this, nasalArray, this);
        nasal.setAdapter(nasalAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private ArrayList<PinyinModal> getData(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        ArrayList<PinyinModal> temp = new ArrayList<>();
        PutData putData = new PutData(APP_URL+"Finals.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    Log.d("pinyin", jsonObject.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        Log.d("pinyin", obj.toString());
                        temp.add(new PinyinModal(Integer.parseInt(obj.getString("id")), obj.getString("text"), obj.getString("sound")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        return temp;
    }


    private void initialize() {
        back = findViewById(R.id.back);
        simple = findViewById(R.id.simple_final_recycler);
        compound = findViewById(R.id.compound_final_recycler);
        nasal = findViewById(R.id.nasal_final_recycler);
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
        speak(simpleArray.get(position).getSound());
    }

    @Override
    public void onCompoundClick(int position) {
        speak(compoundArray.get(position).getSound());
    }

    @Override
    public void onNasalClick(int position) {

        speak(nasalArray.get(position).getSound());


    }

    private void speak(String str) {
        textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
        //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), PinyinClass.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}