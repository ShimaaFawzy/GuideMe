package com.example.expandablelistview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class ViewPanoramaActivity extends Activity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_panorama);
		getActionBar().hide();
		webView = (WebView) findViewById(R.id.viewPanorama);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://keyengine.net/iti/tour.html");
	}

	

}
