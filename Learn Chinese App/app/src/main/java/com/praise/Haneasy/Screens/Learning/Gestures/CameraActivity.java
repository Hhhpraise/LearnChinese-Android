package com.praise.Haneasy.Screens.Learning.Gestures;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.praise.Haneasy.R;

import org.opencv.android.OpenCVLoader;

import java.util.ArrayList;



public class CameraActivity extends AppCompatActivity {
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    private static final String TAG = "CameraActivity";
    private static final String MODEL_FILE = "file:///android_asset/digital_gesture500_200_c3.pb"; //模型存放路径

    //加载OpenCV,必须先加载
    static {
        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "init failed");
        }
    }

    ///为了使照片竖直显示
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private CameraDevice mCameraDevice;//摄像头设备
    private Classifier classifier;//识别类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_camera);

        initView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "对不起，没有权限，无法正常使用相机", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            // initCamera2();
            initCamera();
        }
    }

    private void initCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_CANCELED){
            if (requestCode == 100) {
                if(data.getExtras().get("data")!=null){
                    Bitmap captureImage = (Bitmap) data.getExtras().get("data");
                    classifier = new Classifier(getAssets(), MODEL_FILE);
                    ArrayList<String> result = classifier.predict(captureImage);
                    //传递参数
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("image", captureImage);
                    bundle.putStringArrayList("recognize_result", result);
                    Intent intent = new Intent(CameraActivity.this, DisplayResult.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        }
        else {
            onBackPressed();
        }

    }

    private void initView() {
        mSurfaceView = findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.setKeepScreenOn(true);
        // mSurfaceView添加回调
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) { //SurfaceView创建
                // 初始化Camera
                //获取摄像头权限
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CameraActivity.this, new String[]{
                                Manifest.permission.CAMERA
                        }, 100);
                    } else {
                        initCamera();

                    }
                } else {
                    initCamera();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (null != mCameraDevice) {
                    mCameraDevice.close();
                    mCameraDevice = null;
                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), IntroPage.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }
}
