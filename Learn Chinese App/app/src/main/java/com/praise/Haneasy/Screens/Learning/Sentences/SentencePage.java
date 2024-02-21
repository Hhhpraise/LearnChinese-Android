package com.praise.Haneasy.Screens.Learning.Sentences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.praise.Haneasy.Screens.Learning.Listening.Audio.AudioPage;
import com.praise.Haneasy.Screens.Learning.Sentences.Adapter.LevelModal;
import com.praise.Haneasy.Screens.Learning.Sentences.Adapter.LevelAdapter;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

public class SentencePage extends AppCompatActivity implements MyClickInterface {
    ImageView back;
    RecyclerView recyclerView;
    LevelAdapter levelAdapter;
    ArrayList<LevelModal> levelModals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_page);
        initialize();
        if(isNetworkConnected()){
            getLevelData();
        }
        else {
            Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
        }



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        levelAdapter = new LevelAdapter(this, levelModals, this);
        recyclerView.setAdapter(levelAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
     

    }

    private void getLevelData() {
        FetchData fetchData = new FetchData(APP_URL+"Levels.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                //End ProgressBar (Set visibility to GONE);
                try {
                    JSONObject jsnobject = new JSONObject(fetchData.getData());
                    jsnobject.get("data");
                    //Printing JSON object
                    // System.out.println("JSON Object");
                    // System.out.println(jsnobject);
                    //Getting languages JSON array from the JSON object
                    JSONArray jsonArray = jsnobject.getJSONArray("data");

                    levelModals = new ArrayList<LevelModal>();
                    // 遍历jsons数组对象，
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        levelModals.add(new LevelModal(Integer.parseInt(obj.get("id").toString()), obj.get("name").toString()));
                        System.out.println(levelModals.toString());
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Do whatever.
            }

        }
    }

    private void initialize() {
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.genlist_recyccler);
      
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LearningPage.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }

    @Override
    public void onItemClick(int position) {
        int id = levelModals.get(position).getId();
        Intent intent = new Intent(getApplicationContext(), SentenceGen.class);
        intent.putExtra("level_id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}