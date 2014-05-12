package com.javapapers.android;

import com.example.expandablelistview.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ShareExternalServer appUtil;
	String regId;
	String msg;
	AsyncTask<Void, Void, String> shareRegidTask;
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
			}
	public void takeReg(String regId2,Context c) {
		appUtil = new ShareExternalServer();
		this.regId=regId2;
		//regId = getIntent().getStringExtra("regId");
		//msg = getIntent().getStringExtra("msg");
		if(regId!=null){
			System.out.println("REGGGGGGG--------------------"+regId2);
		Log.d("MainActivity", "regId: " + regId2);

		final Context context = c;
		shareRegidTask = new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String result = appUtil.shareRegIdWithAppServer(context, regId);
				return result;
			}

			@Override
			protected void onPostExecute(String result) {
				shareRegidTask = null;
				Toast.makeText(context, result,
						Toast.LENGTH_LONG).show();
			}

		};
		shareRegidTask.execute(null, null, null);
	
	}

		
	}
}
