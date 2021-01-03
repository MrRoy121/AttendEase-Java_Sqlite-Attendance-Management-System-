package com.example.attendancesystem.activity;


import com.example.attendancesystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Handler handler = new Handler(Looper.getMainLooper());
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent =new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		}, 2500);



	}


}
