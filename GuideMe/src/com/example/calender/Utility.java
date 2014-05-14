package com.example.calender;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import android.os.AsyncTask;


public class Utility {
	public static ArrayList<String> nameOfEvent = new ArrayList<String>();
	public static ArrayList<String> startDates = new ArrayList<String>();
	public static ArrayList<String> endDates = new ArrayList<String>();
	public static ArrayList<String> descriptions = new ArrayList<String>();
	public DownloadTask dt;
	String contentAsString;
	URL url;

	public  ArrayList<String> readCalendarEvent(Context context) {
		
		nameOfEvent.clear();
		startDates.clear();
		endDates.clear();
		descriptions.clear();
		//dt = new DownloadTask();
		//dt.execute("http://localhost:8084/EventsCalendar/Events/events");
		
		for (int i = 0; i < 1; i++) {
			//1
			nameOfEvent.add("Concert Orchestra \"Omar Khairat\"");
			startDates.add("2014-05-03");
			endDates.add("2014-05-04");
			descriptions.add("Concert Orchestra \"Omar Khairat\"");
			//2
			nameOfEvent.add("An evening of poetry \"feelings Tjaddedna\" \nFreedom Center for Innovation");
			startDates.add("2014-05-05");
			endDates.add("2014-05-06");
			descriptions.add("An evening of poetry \"feelings Tjaddedna\"Freedom Center for Innovation");
			//3
			nameOfEvent.add("Lecture \"Photography invisible\"\nLecture Hall - Bibliotheca Alexandrina");
			startDates.add("2014-05-14");
			endDates.add("2014-05-15");
			descriptions.add("Lecture \"Photography invisible\"\nLecture Hall - Bibliotheca Alexandrina");
			//4
		
			nameOfEvent.add("View Play \"Dali\"\nLittle Theatre - Bibliotheca Alexandrina");
			startDates.add("2014-05-20");
			endDates.add("2014-05-20");
			descriptions.add("View Play \"Dali\"\nLittle Theatre - Bibliotheca Alexandrina");
			//5
			nameOfEvent.add("Concert Orchestra \"Omar Khairat\"");
			startDates.add("2014-06-03");
			endDates.add("2014-06-04");
			descriptions.add("Concert Orchestra \"Omar Khairat\"");
		}
		return nameOfEvent;
	}

	public static String getDate(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}
	
	
	
	
}