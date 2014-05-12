package com.example.calender;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.calender.CalendarAdapter;
import com.example.expandablelistview.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalenderActivity extends Activity {
	public static ArrayList<String> nameOfEvent = new ArrayList<String>();
	public static ArrayList<String> startDates = new ArrayList<String>();
	public static ArrayList<String> endDates = new ArrayList<String>();
	public static ArrayList<String> descriptions = new ArrayList<String>();
	
	public DownloadTask dt;
	String contentAsString;
	URL url;
	JSONObject object1 ,object2, fileObject1;
	JSONArray eventsJson;
	String myJson , testFound;
	StringBuffer stringBuffer;
	public GregorianCalendar month, itemmonth;// calendar instances.

	public CalendarAdapter adapter;// adapter instance
	public Handler handler;// for grabbing some event values for showing the dot
							// marker.
	public ArrayList<String> items; // container to store calendar items which
								// needs showing the event marker
	LinearLayout rLayout;
	ArrayList<String> date;
	ArrayList<String> desc;
	String events;
	String eventFromWeb;
	SharedPreferences savePre;
	SharedPreferences.Editor editor;
	ConnectivityManager conMgr;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myJson ="{\"Dayes\":\" \",\"stat\":\"ok\"}";
		nameOfEvent.clear();
		startDates.clear();
		endDates.clear();
		descriptions.clear();
		setContentView(R.layout.activity_calender);
		Drawable d = getResources().getDrawable(R.drawable.back);
		getActionBar().setBackgroundDrawable(d);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setIcon(R.drawable.logo);
		Locale.setDefault(Locale.US);

		rLayout = (LinearLayout) findViewById(R.id.text);
		month = (GregorianCalendar) GregorianCalendar.getInstance();
		itemmonth = (GregorianCalendar) month.clone();
		events = "eventsFile";
		items = new ArrayList<String>();
		adapter = new CalendarAdapter(this, month);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		handler = new Handler();
		readFromFile();
		handler.post(calendarUpdater);
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setPreviousMonth();
				readFromFile();
				TextView title = (TextView) findViewById(R.id.title);
				adapter.refreshDays();
				adapter.notifyDataSetChanged();
				handler.post(calendarUpdater); // generate some calendar items
				title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

				//refreshCalendar();
			}
		});

		RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();

			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// removing the previous view if added
				if (((LinearLayout) rLayout).getChildCount() > 0) {
					((LinearLayout) rLayout).removeAllViews();
				}
				desc = new ArrayList<String>();
				date = new ArrayList<String>();
				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = CalendarAdapter.dayString
						.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
				// navigate to next or previous month on clicking offdays.
				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalendarAdapter) parent.getAdapter()).setSelected(v);

				for (int i = 0; i < startDates.size(); i++) {
					if (startDates.get(i).equals(selectedGridDate)) {
						desc.add(nameOfEvent.get(i));
					}
				}

				if (desc.size() > 0) {
					for (int i = 0; i < desc.size(); i++) {
						TextView rowTextView = new TextView(CalenderActivity.this);

						// set some properties of rowTextView or something
						rowTextView.setText("Event:" + desc.get(i));
						rowTextView.setTextColor(Color.BLACK);

						// add the textview to the linearlayout
						rLayout.addView(rowTextView);

					}

				}

				desc = null;

			}

		});
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		savePre = getApplicationContext().getSharedPreferences("name",
	            Context.MODE_PRIVATE);
		testFound = savePre.getString("DATAA", "Not Found");
		if(testFound.equalsIgnoreCase("Not Found")){
		
		eventFromWeb = savePre.getString("DATAA", "false");
		if(eventFromWeb.equalsIgnoreCase("false"))
    	{
		dt = new DownloadTask();
		dt.execute("http://10.1.42.126:8084/EventsCalendar/Events/events");
    	}else{readFromFile();}
		}
	}
	protected void setNextMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMaximum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) + 1),
					month.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) + 1);
		}

	}

	protected void setPreviousMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}

	}

	protected void showToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

	}

	public void refreshCalendar() {
		TextView title = (TextView) findViewById(R.id.title);
		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();

			// Print dates of the current week
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			String itemvalue;
			Log.d("=====Event====", nameOfEvent.toString());
			Log.d("=====Date ARRAY====", startDates.toString());

			for (int i = 0; i < startDates.size(); i++) {
				itemvalue = df.format(itemmonth.getTime());
				itemmonth.add(GregorianCalendar.DATE, 1);
				items.add(startDates.get(i).toString());
			}
			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};
String convertInputStreamToString(InputStream is){
		
		BufferedReader br = null;
		StringBuilder result = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}

boolean isEnabledNetwork(){
	boolean enabled = false;
	conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo[] info = conMgr.getAllNetworkInfo();
    if (info != null) 
    {
        for (int i = 0; i < info.length; i++){ 
            if (info[i].getState() == NetworkInfo.State.CONNECTED)
            {
                enabled = true;
            }
        }
    }
    return enabled;
	
}
	public void connections (){
		//if(isEnabledNetwork()){
		System.out.println("=============connections ===========");
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://10.1.42.126:8084/EventsCalendar/Events/events");
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
		
			myJson = convertInputStreamToString(content);
			object1 = new JSONObject(myJson);
			System.out.println("========="+object1+"========");
			eventsJson  = object1.getJSONArray("Dayes");
			System.out.println("2========="+eventsJson.length()+"========");
		} catch (ClientProtocolException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		} catch (JSONException e) {e.printStackTrace();
		}
		/*}else {myJson ="{\"Dayes\":\" \",\"stat\":\"ok\"}";
		try {
			object1= new JSONObject(myJson);
			eventsJson  = object1.getJSONArray("Dayes");
		} catch (JSONException e) {e.printStackTrace();}
		showToast(" No network connection...!");}*/
	}
	
	public void readFromFile(){
		nameOfEvent.clear();
		startDates.clear();
		endDates.clear();
		descriptions.clear();
		stringBuffer = new StringBuffer();  
		try {
			InputStream inputStream =openFileInput(events);
			if( inputStream != null ){
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
		    String inputString;
		                  
		    while ((inputString = inputReader.readLine()) != null) {
		        stringBuffer.append(inputString);
		    }
		    String test = stringBuffer.toString();
		    Toast.makeText(getApplicationContext(),stringBuffer.toString(),Toast.LENGTH_LONG).show();
		    try {
				fileObject1 = new JSONObject(test);
				
				eventsJson  = fileObject1.getJSONArray("Dayes");
				for (int i = 0; i < eventsJson.length(); i++) {
					object2 = eventsJson.getJSONObject(i);
					object2.getString("descriptions");
					System.out.println(object2.getString("descriptions")+"=======Desc");
					nameOfEvent.add(object2.getString("name"));
					startDates.add(object2.getString("startDate"));
					endDates.add(object2.getString("endDate"));
					descriptions.add(object2.getString("descriptions"));
				}
			} catch (JSONException e) {e.printStackTrace();}
		}
		} catch (IOException e) {e.printStackTrace();}
	}
		class DownloadTask extends AsyncTask<String, Void, Void>{

		protected void onPreExecute() {
			super.onPreExecute();}

		@Override
		protected Void doInBackground(String... arg0) {
			System.out.println(arg0+"url========");
				connections();
				return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			for (int i = 0; i < eventsJson.length(); i++) {
				try {
					object2 = eventsJson.getJSONObject(i);
					object2.getString("descriptions");
					System.out.println(object2.getString("descriptions")+"=======Desc");
					nameOfEvent.add(object2.getString("name"));
					startDates.add(object2.getString("startDate"));
					endDates.add(object2.getString("endDate"));
					descriptions.add(object2.getString("descriptions"));
				} catch (JSONException e) {e.printStackTrace();}
				System.out.println("=========="+object2+i+"=======");
				FileOutputStream fos;
				   try {
					fos = openFileOutput(events,Context.MODE_PRIVATE);//
					fos.write(myJson.getBytes());
					fos.close();
				} catch (IOException e) {e.printStackTrace();}
				   Toast.makeText(getApplicationContext(),events + " saved",Toast.LENGTH_LONG).show();
				}
			savePre = getApplicationContext().getSharedPreferences("name",
		            Context.MODE_PRIVATE);
		    editor = savePre.edit();
		    editor.putString("DATAA","true");
		    eventFromWeb = "true";
		    editor.commit();
			}
}
}