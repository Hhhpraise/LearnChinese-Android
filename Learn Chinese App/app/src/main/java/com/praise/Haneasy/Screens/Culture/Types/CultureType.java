package com.praise.Haneasy.Screens.Culture.Types;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Bot.ChatBot;
import com.praise.Haneasy.Screens.Culture.CulturePage;
import com.praise.Haneasy.Screens.Culture.Types.Adapter.CultureTypes;
import com.praise.Haneasy.Screens.Culture.Types.Adapter.TypeAdapter;
import com.praise.Haneasy.Screens.Culture.Types.Adapter.TypeClickInterface;
import com.praise.Haneasy.Screens.Forum.ViewPost;
import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.praise.Haneasy.Screens.Info.UserInfo;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This page shows the categories of the chinese culture
 * loads data from the database
 */

public class CultureType extends AppCompatActivity implements TypeClickInterface {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    TypeAdapter adapter;
    ArrayList<CultureTypes> types;
    private  static  String TYPE_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_type);
        initialize();
        if(isNetworkConnected()){
            getData();
        }
        else {
            Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
        }

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TypeAdapter(this, types, this);
        recyclerView.setAdapter(adapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exercises:
                        startActivity(new Intent(getApplicationContext(), LearningPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.culture:
                        return true;
                    case R.id.post:
                        startActivity(new Intent(getApplicationContext(), ViewPost.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.ai:
                        startActivity(new Intent(getApplicationContext(), ChatBot.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(), UserInfo.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void getData() {
        //Start ProgressBar first (Set visibility VISIBLE)
        FetchData fetchData = new FetchData(APP_URL+"CultureTypes.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                //End ProgressBar (Set visibility to GONE)
                JSONObject jsnobject = null;
                try {
                    jsnobject = new JSONObject(fetchData.getData());
                    jsnobject.get("data");
                    JSONArray jsonArray = jsnobject.getJSONArray("data");
                    types = new ArrayList<CultureTypes>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        int id = Integer.parseInt(obj.get("id").toString());
                        String name = obj.get("type").toString();
                        String image = obj.get("img").toString();
                        types.add(new CultureTypes(id,name,image));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initialize() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.culture);
        recyclerView = findViewById(R.id.type_recycler);
    }

    @Override
    public void onItemClick(int position) {
        int id = types.get(position).getId();
        String name = types.get(position).getName();
        Intent intent = new Intent(getApplicationContext(), CulturePage.class);
        intent.putExtra("id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        SharedPreferences.Editor editor = getSharedPreferences(TYPE_NAME, MODE_PRIVATE).edit();
        editor.putInt("id", id);
        editor.putString("name",name);
        editor.apply();
    }

    @Override
    public void onBackPressed() {

    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}