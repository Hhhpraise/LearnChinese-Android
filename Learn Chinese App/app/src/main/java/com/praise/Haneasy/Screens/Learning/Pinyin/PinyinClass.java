package com.praise.Haneasy.Screens.Learning.Pinyin;

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
import com.praise.Haneasy.Screens.Learning.Pinyin.Adapter.PinyinClassAdapter;
import com.praise.Haneasy.Screens.Learning.Pinyin.Adapter.PinyinClassModal;
import com.praise.Haneasy.Screens.Learning.Pinyin.Tones.TonePage;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.praise.Haneasy.Screens.Learning.Pinyin.Finals.FinalsPage;
import com.praise.Haneasy.Screens.Learning.Pinyin.Initials.InitialsPage;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This class list the initials, finals and Chinese tones
 */
public class PinyinClass extends AppCompatActivity implements MyClickInterface {
    RecyclerView recyclerView;
    ArrayList<PinyinClassModal> modals;
    PinyinClassAdapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinyin_class);
        initialize();
        if(isNetworkConnected()){
            data();
        }
        else {
            Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PinyinClassAdapter(this, modals, this);
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             onBackPressed();
            }
        });

    }

    private void data() {
        FetchData fetchData = new FetchData(APP_URL+"PinyinClass.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                try {
                    JSONObject jsnobject = new JSONObject(fetchData.getData());
                    jsnobject.get("data");
                    JSONArray jsonArray = jsnobject.getJSONArray("data");
                    modals = new ArrayList<PinyinClassModal>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        modals.add(new PinyinClassModal(
                                Integer.parseInt(obj.get("id").toString()),
                                obj.get("name").toString(),
                                obj.get("img").toString()));
                        System.out.println(modals.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void initialize() {
        recyclerView = findViewById(R.id.pinyin_recycler);
        back = findViewById(R.id.back);
    }

    @Override
    public void onItemClick(int position) {
        int id = modals.get(position).getId();
        Intent intent;
        switch (id) {
            case 1:
                intent = new Intent(getApplicationContext(), InitialsPage.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 2:
                intent = new Intent(getApplicationContext(), FinalsPage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
            case 3:
                intent = new Intent(getApplicationContext(), TonePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;
        }

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
    }
}