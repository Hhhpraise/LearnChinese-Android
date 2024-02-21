package com.praise.Haneasy.Screens.Learning.Gestures;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.praise.Haneasy.R;

import java.util.ArrayList;

public class DisplayResult extends Activity {
	private ImageView imageView,back;
	private TextView resultTextView;
	private TextView probTextView;
	private Bitmap bitmap;
	CardView exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_result);
		Bundle bundle = getIntent().getExtras();
		bitmap = (Bitmap)bundle.getParcelable("image");
		ArrayList<String> list = (ArrayList<String>)bundle.get("recognize_result");
		imageView = (ImageView)findViewById(R.id.display_photo);
		back = (ImageView) findViewById(R.id.back);
		imageView.setImageBitmap(bitmap);
		resultTextView = (TextView)findViewById(R.id.display_result);
		resultTextView.setText("Number: " + list.get(0));
		probTextView = (TextView)findViewById(R.id.display_prob);
		probTextView.setText("Probability: " + list.get(1));
		//dialog=new MyDialog(this);
		exit =  findViewById(R.id.exit);
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(getApplicationContext(),CameraActivity.class));
		overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
		finish();
	}

}
