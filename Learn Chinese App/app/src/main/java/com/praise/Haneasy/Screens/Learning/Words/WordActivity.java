package com.praise.Haneasy.Screens.Learning.Words;

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
import com.praise.Haneasy.Screens.Learning.Words.Adapter.WordClass;
import com.praise.Haneasy.Screens.Learning.Words.Adapter.WordClassAdapter;
import com.praise.Haneasy.Screens.Learning.Words.WordPage.WordPage;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * Loads all word categories from the database...
 */

public class WordActivity extends AppCompatActivity implements MyClickInterface {
    RecyclerView recyclerView;
    ImageView back;
    ArrayList<WordClass> classes;
    WordClassAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        initialize();
        if (isNetworkConnected()) {
            getData();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WordClassAdapter(getApplicationContext(), classes, this);
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getData() {
        FetchData fetchData = new FetchData(APP_URL+"WordClasses.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(fetchData.getData());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    classes = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        classes.add(new WordClass(Integer.parseInt(obj.get("id").toString()), obj.get("name").toString()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initialize() {
        recyclerView = findViewById(R.id.word_recycler);
        back = findViewById(R.id.back);
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
    String name = classes.get(position).getName();
    int id = classes.get(position).getId();
        Intent intent = new Intent(getApplicationContext(), WordPage.class);
        intent.putExtra("id", id);
        intent.putExtra("name",name);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}