package com.praise.Haneasy.Screens.Forum;

import static com.praise.Haneasy.Splash.APP_URL;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Bot.ChatBot;
import com.praise.Haneasy.Screens.Culture.Types.CultureType;
import com.praise.Haneasy.Screens.Forum.PostAdapter.PostAdapter;
import com.praise.Haneasy.Screens.Forum.PostAdapter.PostEntities;
import com.praise.Haneasy.Screens.Info.UserInfo;
import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.praise.Haneasy.Screens.Learning.Pinyin.Adapter.PinyinClassAdapter;
import com.praise.Haneasy.Screens.Learning.Pinyin.Adapter.PinyinClassModal;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewPost extends AppCompatActivity implements MyClickInterface {

    PostAdapter postAdapter;
    ArrayList<PostEntities> postEntitiesArrayList;
    ImageView postButton, back;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        initialize();
        fetchData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MakePost.class));
                overridePendingTransition(0, 0);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
                refreshLayout.setRefreshing(false);

            }
        });


    }

    private void fetchData() {
        FetchData fetchData = new FetchData(APP_URL + "fetchpost.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                try {
                    JSONObject jsnobject = new JSONObject(fetchData.getData());
                    jsnobject.get("data");
                    JSONArray jsonArray = jsnobject.getJSONArray("data");
                    postEntitiesArrayList = new ArrayList<PostEntities>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        postEntitiesArrayList.add(new PostEntities(
                                Integer.parseInt(obj.get("id").toString()),
                                obj.get("userName").toString(),
                                obj.get("postDesc").toString(),
                                obj.get("postDate").toString()));
                       // System.out.println(postEntitiesArrayList.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        postAdapter = new PostAdapter(this, postEntitiesArrayList, this);
        recyclerView.setAdapter(postAdapter);
    }

    private void initialize() {
        recyclerView = findViewById(R.id.recycler);
        postButton = findViewById(R.id.postBtn);
        refreshLayout = findViewById(R.id.swipe);
        back = findViewById(R.id.back);

    }

    @Override
    public void onItemClick(int position) {
        String d = postEntitiesArrayList.get(position).getPostDesc();
        String user = postEntitiesArrayList.get(position).getUserName();
        String time = postEntitiesArrayList.get(position).getPostDate();
        int id = postEntitiesArrayList.get(position).getId();
        // Toast.makeText(getApplicationContext(), d, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), DetailedPost.class);
        intent.putExtra("username", user);
        intent.putExtra("desc", d);
        intent.putExtra("date", time);
        intent.putExtra("id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LearningPage.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}