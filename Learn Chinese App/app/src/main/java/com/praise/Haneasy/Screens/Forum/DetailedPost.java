package com.praise.Haneasy.Screens.Forum;

import static com.praise.Haneasy.Splash.APP_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Culture.Culture_Adapter.CultureModal;
import com.praise.Haneasy.Screens.Forum.PostAdapter.CommentAdapter;
import com.praise.Haneasy.Screens.Forum.PostAdapter.CommentEntity;
import com.praise.Haneasy.Screens.Forum.PostAdapter.PostAdapter;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailedPost extends AppCompatActivity {
    TextView postName, post, postDate;
    CommentAdapter commentAdapter;
    ArrayList<CommentEntity> commentEntities;
    RecyclerView recyclerView;
    int id;
    EditText commentEdt;
    ImageView commentBtn, back;
    SwipeRefreshLayout refreshLayout;
    private static String IS_LOGGED_IN = "isLogIn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);
        initialize();
        getIntentData();
        fetchData(id);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        refresh();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                refreshLayout.setRefreshing(false);

            }
        });


        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commentEdt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                String txt = commentEdt.getText().toString();
                postComment(id, getCurrentUser(), txt, getCurrentTime());
            }
        });

    }

    private void postComment(int id, String currentUser, String txt, String currentTime) {
        String[] field = new String[4];
        field[0] = "post_id";
        field[1] = "user_name";
        field[2] = "txt";
        field[3] = "time";

        String[] data = new String[4];
        data[0] = id + "";
        data[1] = currentUser;
        data[2] = txt;
        data[3] = currentTime;

        PutData putData = new PutData(APP_URL + "PostComment.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    String data_val = jsonObject.getString("data");
                    if (data_val.equals("Successful")) {
                        commentEdt.setText("");
                        //Toast.makeText(getApplicationContext(), "posting...", Toast.LENGTH_SHORT).show();
                        refresh();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private void refresh() {
        fetchData(id);
        commentAdapter = new CommentAdapter(this, commentEntities);
        recyclerView.setAdapter(commentAdapter);
    }

    private String getCurrentTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa ,MMM d");
        Date date = new Date();
        return formatter.format(date).toString();
    }

    private String getCurrentUser() {
        SharedPreferences preferences = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE);
        String user = preferences.getString("user_name", "");
        return user;
    }

    private void fetchData(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL + "Comment.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String value = putData.getResult();
                System.out.println(value);
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    commentEntities = new ArrayList<>();
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        String user = obj.getString("user_name");
                        String txt = obj.getString("txt");
                        String time = obj.getString("time");
                        int c_id = Integer.parseInt(obj.getString("id"));
                        commentEntities.add(new CommentEntity(c_id, user, txt, time));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void getIntentData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String desc = intent.getStringExtra("desc");
        String time = intent.getStringExtra("date");
        id = intent.getIntExtra("id", 1);
        setData(name, desc, time);
    }

    private void setData(String name, String desc, String time) {
        postName.setText(name);
        post.setText(desc);
        postDate.setText(time);
    }

    private void initialize() {
        postName = findViewById(R.id.postName);
        post = findViewById(R.id.postD);
        postDate = findViewById(R.id.post_date);
        recyclerView = findViewById(R.id.recycler);
        commentEdt = findViewById(R.id.comment_input);
        commentBtn = findViewById(R.id.comment_btn);
        back = findViewById(R.id.back);
        refreshLayout = findViewById(R.id.swipe);
    }
}