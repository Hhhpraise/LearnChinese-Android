package com.praise.Haneasy.Screens.Forum;

import static com.praise.Haneasy.Splash.APP_URL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Login;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakePost extends AppCompatActivity {

    EditText desc;
    CardView postBtn;
    private static String IS_LOGGED_IN = "isLogIn";
    String user;
    String pdesc;
    String timestamp;
    ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
        initialize();
        getData();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }
        );
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdesc = desc.getText().toString();
                if (!user.isEmpty() && !pdesc.isEmpty()) {
                    timestamp = getCurrentTime();
                    HandlePost(user, pdesc, timestamp);
                }
            }
        });

    }

    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa EEE,MMM d");
        Date date = new Date();
        return formatter.format(date).toString();
    }

    private void HandlePost(String user, String pdesc, String timestamp) {
        String[] field = new String[3];
        field[0] = "userName";
        field[1] = "postDesc";
        field[2] = "postDate";


        String[] data = new String[3];
        data[0] = user;
        data[1] = pdesc;
        data[2] = timestamp;


        PutData putData = new PutData(APP_URL + "Post.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    String data_val = jsonObject.getString("data");
                    if (data_val.equals("Successful")) {

//
                       // Toast.makeText(getApplicationContext(), "posting...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ViewPost.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private void initialize() {

        postBtn = findViewById(R.id.makePost);
        desc = findViewById(R.id.postDesc);
        backBtn = findViewById(R.id.back);
    }

    private void getData() {
        SharedPreferences preferences = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE);
        user = preferences.getString("user_name", "");
        String mail = preferences.getString("user_mail", "");
    }
}