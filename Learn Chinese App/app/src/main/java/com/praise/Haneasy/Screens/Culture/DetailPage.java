package com.praise.Haneasy.Screens.Culture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.praise.Haneasy.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This activity shows the details of each culture
 * loaded from the database..
 */
public class DetailPage extends AppCompatActivity {
    ImageView image;
    TextView name, content;
    String img;
    String cnt_name;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        img = intent.getStringExtra("img");
        cnt_name = intent.getStringExtra("name");
        initialize();
        setContents();
        getData(id);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
    }

    private void getData(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL+"CultureDetail.php", "POST", field, data);
        if(putData.startPut()){
            if(putData.onComplete()){
                String value = putData.getResult();
                Log.d("details", value);
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    String question = jsonObject.get("data").toString();
                    Log.d("details",question);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        String text = obj.getString("text");
                        Log.d("details",text);
                        content.setText(Html.fromHtml(text));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initialize() {
        image = findViewById(R.id.content_img);
        name = findViewById(R.id.content_name);
        content = findViewById(R.id.content_txt);
        back  = findViewById(R.id.back);
    }

    private void setContents() {
        Glide.with(getApplicationContext()).load(img).placeholder(R.drawable.black_bg).into(image);
        name.setText(cnt_name);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), CulturePage.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }
}