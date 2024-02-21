package com.praise.Haneasy.Screens.Learning.Videos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.praise.Haneasy.Screens.Learning.Videos.Adapter.VideoAdapter;
import com.praise.Haneasy.Screens.Learning.Videos.Adapter.VideoModal;
import com.praise.Haneasy.Screens.Learning.Videos.Video.VideoPage;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * to learn chinese through videos with dialogue examples...
 */
public class VideoActivity extends AppCompatActivity implements MyClickInterface {
    RecyclerView recyclerView;
    VideoAdapter courseAdapter;
    ArrayList<VideoModal> modals;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initialize();
        if (isNetworkConnected()) {
            getVideos();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        courseAdapter = new VideoAdapter(this, modals, this);
        recyclerView.setAdapter(courseAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });

    }

    private void getVideos() {
        FetchData fetchData = new FetchData(APP_URL+"Videos.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(fetchData.getData());
                    jsonObject.get("data");
                    //Printing JSON object
                    System.out.println("JSON Object");
                    System.out.println(jsonObject);
                    //Getting languages JSON array from the JSON object
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    modals = new ArrayList<VideoModal>();
                    // 遍历jsons数组对象，
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        Log.d("courses", obj.toString());
                        modals.add(new VideoModal(Integer.parseInt(obj.get("id").toString()),
                                obj.getString("name"),
                                obj.getString("duration"), obj.getString("link")));

//                 Log.d("courses", modals.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Do whatever. okay...
            }
        }
    }

    private void initialize() {
        recyclerView = findViewById(R.id.course_recycler);
        back = findViewById(R.id.back);
    }

    @Override
    public void onItemClick(int positionOfCourse) {
        int id = modals.get(positionOfCourse).getId();
        String name = modals.get(positionOfCourse).getName();
        String link = modals.get(positionOfCourse).getLink();
        Intent intent = new Intent(getApplicationContext(), VideoPage.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("link",link);
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