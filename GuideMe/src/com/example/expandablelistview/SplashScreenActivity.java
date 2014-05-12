package com.example.expandablelistview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	private long ms = 0;
	private long splashTime = 1000;
	private boolean splashActive = true;
	private boolean paused = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		getActionBar().hide();
		Thread mythread = new Thread() {
			public void run() {
				try {
					while (splashActive && ms < splashTime) {
						if (!paused)
							ms = ms + 100;
						sleep(100);
					}
				} catch (Exception e) {
				} finally {
					Intent intent = new Intent(SplashScreenActivity.this,
							MainProjectActivity.class);
					startActivity(intent);
					finish();
				}
			}
		};
		mythread.start();
		
	}
	
	}
	


