package com.praise.Haneasy.Screens.Learning.Gestures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.praise.Haneasy.Screens.Learning.Gestures.Adapter.Gesture;
import com.praise.Haneasy.Screens.Learning.Gestures.Adapter.GestureAdapter;
import com.praise.Haneasy.Screens.Learning.LearningPage;

import java.util.ArrayList;

public class IntroPage extends AppCompatActivity {

    ArrayList<Gesture> gestureArrayList;
    GestureAdapter adapter;
    RecyclerView recyclerView;
    ImageView back;
    ConstraintLayout camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);
        initialize();
        if (isNetworkConnected()) {
            getData();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CameraActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                finish();
            }
        });
    }

    private void getData() {
        gestureArrayList = new ArrayList<>();
        gestureArrayList.add(new Gesture("One (一, 'yī')","Extend your index finger","http://www.thatsmags.com/image/view/201702/IMG_2030.jpg"));
        gestureArrayList.add(new Gesture("Two (二, 'èr' or 两, 'liǎng')","Extend both your index finger and middle finger","https://www.thatsmags.com/image/view/201702/IMG_2031.jpg"));
        gestureArrayList.add(new Gesture("Three (三, 'sān')","Extend your index finger, middle finger and ring finger","http://www.thatsmags.com/image/view/201702/IMG_2032.jpg"));
        gestureArrayList.add(new Gesture("Four (四, 'sì')","Extend your all four of your fingers (index, middle, ring and pinky)","https://tse1-mm.cn.bing.net/th/id/R-C.b3025af01f6989c8f9ab6516a1b417bc?rik=ulkXtoiMvyJ3vw&riu=http%3a%2f%2fwww.thatsmags.com%2fimage%2fview%2f201702%2fIMG_2033.jpg&ehk=NMN2L2CBChZtyG43ZHloupqV%2byyfHU0JxB2%2fZTuL8Uw%3d&risl=&pid=ImgRaw&r=0"));
        gestureArrayList.add(new Gesture("Five (五, 'wǔ')","Extend all of your fingers plus your thumb","https://www.thatsmags.com/image/view/201702/IMG_2034.jpg"));
        gestureArrayList.add(new Gesture("Six (六, 'liù')"," Extend your pinky to one side and your thumb in the opposite direction. Fold your index, middle and ring fingers down against your palm","https://tse1-mm.cn.bing.net/th/id/R-C.650a532b35c2685fa53594b129c62544?rik=tsRIq0d9Rubl%2bQ&riu=http%3a%2f%2fwww.thatsmags.com%2fimage%2fview%2f201702%2fIMG_2035.jpg&ehk=B0v874kC2A92ILfd5xgre%2br79671d%2b6wtvVyoduOtyI%3d&risl=&pid=ImgRaw&r=0"));
        gestureArrayList.add(new Gesture("Seven (七, 'qī')","All fingertips touch, or just the tips of the index finger, middle finger and thumb.","https://www.thatsmags.com/image/view/201702/IMG_2036.jpg"));
        gestureArrayList.add(new Gesture("Eight (八, 'bā')","Point and extend your index finger in the air, and also extend your thumb, so that you make an \"L\" shape.","https://tse1-mm.cn.bing.net/th/id/R-C.2f88be2e849b1bdbbb696a02439124b1?rik=aV7hdSeIYL3%2fRA&riu=http%3a%2f%2fwww.thatsmags.com%2fimage%2fview%2f201702%2fIMG_2037.jpg&ehk=eLIfsgVbCkVLuuF0DqyfUnWwDWa%2ftJQOdtLGXj%2bpkXs%3d&risl=&pid=ImgRaw&r=0"));
        gestureArrayList.add(new Gesture("Nine (九, 'jiǔ')","Curl your index finger. Other fingers can be closed like a fist.","https://www.thatsmags.com/image/view/201702/IMG_2044.jpg"));
        gestureArrayList.add(new Gesture("Ten (十, 'shí')","Make a fist. (Though this can also be interpreted as \"zero\").","https://www.thatsmags.com/image/view/201702/IMG_2039.jpg"));
        gestureArrayList.add(new Gesture("Ten (十, 'shí')","An alternative way is to take both index fingers of each hand and then cross them like the character for ten: \"十”","https://tse1-mm.cn.bing.net/th/id/R-C.43264c0c06e25be9a47bb3bb7eba61dd?rik=wkZpOzhzd2jmag&riu=http%3a%2f%2fwww.thatsmags.com%2fimage%2fview%2f201702%2fIMG_2043.jpg&ehk=rTy5VweVYVfAhaNpuWW4145aGz27xzr1lZB1Lxu2eyU%3d&risl=&pid=ImgRaw&r=0"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GestureAdapter(getApplicationContext(),gestureArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void initialize() {
        recyclerView = findViewById(R.id.gesture_recycler);
        back = findViewById(R.id.back);
        camera = findViewById(R.id.camera_btn);

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