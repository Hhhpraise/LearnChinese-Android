package com.praise.Haneasy.Screens.Learning.Videos.Video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Videos.Adapter.Dialogue;
import com.praise.Haneasy.Screens.Learning.Videos.Adapter.DialogueAdapter;
import com.praise.Haneasy.Screens.Learning.Videos.VideoActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * Plays video and read subtitles..
 */
public class VideoPage extends AppCompatActivity {
    VideoView player;
    String url, name;
    TextView videoName;
    ImageView back;
    RecyclerView recyclerView;
    ArrayList<Dialogue> dialogues;
    DialogueAdapter dialogueAdapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_page);
        initialize();
        getIntentData();
        setData();
        getDialogues(id);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        dialogueAdapter = new DialogueAdapter(getApplicationContext(), dialogues);
        recyclerView.setAdapter(dialogueAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getDialogues(int id) {

        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL+"Dialogue.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String value = putData.getResult();
                Log.d("Dialogue", value);
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    String jsonData = jsonObject.get("data").toString();
                    Log.d("Dialogue", jsonData);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    dialogues = new ArrayList<Dialogue>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        String A = obj.getString("A");
                        String B = obj.getString("B");
                        int dialogue_id = Integer.parseInt(obj.getString("id"));
                        dialogues.add(new Dialogue(dialogue_id, A, B));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }


    }

    private void setData() {
        videoName.setText(name);
        Uri uri = Uri.parse(url);
        player.setVideoURI(uri);
        MediaController controller = new MediaController(this);
        controller.setAnchorView(player);
        controller.setMediaPlayer(player);
        player.setMediaController(controller);
        player.start();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        url = intent.getStringExtra("link");
        name = intent.getStringExtra("name");
    }

    private void initialize() {
        player = (VideoView) findViewById(R.id.video);
        videoName = findViewById(R.id.video_name);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.convo_recycler);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), VideoActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }
}