package com.praise.Haneasy.Screens.Learning.Reading;

import androidx.appcompat.app.AppCompatActivity;
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
import com.praise.Haneasy.Screens.Learning.Reading.Adapter.LevelAdapter;
import com.praise.Haneasy.Screens.Learning.Reading.Adapter.LevelModal;
import com.praise.Haneasy.Screens.Learning.Reading.Question.QuestionPage;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * Loads all reading levels for reading exercises...
 */
public class ReadingActivity extends AppCompatActivity implements MyClickInterface {
    RecyclerView recyclerView;
    LevelAdapter levelAdapter;
    ArrayList<LevelModal> modals;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        initialize();
        if (isNetworkConnected()) {
            getLevelData();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        levelAdapter = new LevelAdapter(this, modals, this);
        recyclerView.setAdapter(levelAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getLevelData() {
        FetchData fetchData = new FetchData(APP_URL + "Levels.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                JSONObject jsnobject = null;
                try {
                    jsnobject = new JSONObject(fetchData.getData());
                    jsnobject.get("data");
                    JSONArray jsonArray = jsnobject.getJSONArray("data");

                    modals = new ArrayList<LevelModal>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        modals.add(new LevelModal(Integer.parseInt(obj.get("id").toString()),
                                obj.get("name").toString(),
                                obj.get("img").toString()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    private void initialize() {
        recyclerView = findViewById(R.id.level_recyccler);
        back = findViewById(R.id.back);
    }

    @Override
    public void onItemClick(int positionOfLevel) {
        // String output = modals.get(positionOfLevel).getName();
        int id = modals.get(positionOfLevel).getId();
        //   Toast.makeText(getApplicationContext(), output + " was clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), QuestionPage.class);
        intent.putExtra("question_id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LearningPage.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
