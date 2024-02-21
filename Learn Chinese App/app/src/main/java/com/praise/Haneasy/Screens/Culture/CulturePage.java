package com.praise.Haneasy.Screens.Culture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Culture.Culture_Adapter.CultureAdapter;
import com.praise.Haneasy.Screens.Culture.Culture_Adapter.CultureModal;
import com.praise.Haneasy.Screens.Culture.Types.CultureType;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * This activity shows all cultures under each categories..
 * loads from database
 */
public class CulturePage extends AppCompatActivity implements MyClickInterface {

    RecyclerView recyclerView;
    CultureAdapter cultureAdapter;
    ArrayList<CultureModal> cultureModals;
    TextView name;
    String nameType;
    ImageView back;
    int id;
    private  static  String TYPE_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_page);
        initialize();
        getIntentData();
        getData(id);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        cultureAdapter = new CultureAdapter(this, cultureModals, this);
        recyclerView.setAdapter(cultureAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void getIntentData() {
        SharedPreferences preferences = getSharedPreferences(TYPE_NAME, MODE_PRIVATE);
        id = preferences.getInt("id",1);
        nameType = preferences.getString("name","");
        name.setText(nameType);
    }
    private void getData(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        PutData putData = new PutData(APP_URL+"Cultures.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String value = putData.getResult();
                Log.d("Culture", value);
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    String jsonData = jsonObject.get("data").toString();
                    cultureModals = new ArrayList<>();
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                        String text = obj.getString("name");
                        String img = obj.getString("img");
                        int culture_id = Integer.parseInt(obj.getString("id"));
                        cultureModals.add(new CultureModal(culture_id,text,img));
                        Log.d("Culture",text);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void initialize() {
        recyclerView = findViewById(R.id.culture_recyccler);
        name = findViewById(R.id.nameType);
        back = findViewById(R.id.back);

    }

    @Override
    public void onItemClick(int positionOfItem) {
        int id = cultureModals.get(positionOfItem).getId();
        String img = cultureModals.get(positionOfItem).getImg();
        String name = cultureModals.get(positionOfItem).getName();
        //   Toast.makeText(getApplicationContext(), output + " was clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), DetailPage.class);
        intent.putExtra("id", id);
        intent.putExtra("img", img);
        intent.putExtra("name", name);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),CultureType.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}