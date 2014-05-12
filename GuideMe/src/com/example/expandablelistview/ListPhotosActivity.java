package com.example.expandablelistview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class ListPhotosActivity extends Activity {

	ListView list;//b islam land m f
	public Integer[] mThumbIds = { R.drawable.beaches,
			R.drawable.islamic, R.drawable.landspace,
			R.drawable.museums,R.drawable.f4, R.drawable.ic_launcher };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_photos);
		Drawable d = getResources().getDrawable(R.drawable.back);
		getActionBar().setBackgroundDrawable(d);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setIcon(R.drawable.logo);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(new myAdapter());
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int itemPosition = position;
				Toast.makeText(getApplicationContext(),
						"Position :" + itemPosition, Toast.LENGTH_LONG).show();
				//wmomken ab3at array kolha afdal 
				Intent intent = new Intent(ListPhotosActivity.this,
						FullPhotoActivity.class);
				intent.putExtra("press", position);
				startActivity(intent);

			}
		});
	}

	class myAdapter extends ArrayAdapter<Integer> {
		public myAdapter() {
			super(ListPhotosActivity.this, R.layout.photos_cell, mThumbIds);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemview = convertView;
			if (itemview == null) {
				itemview = getLayoutInflater().inflate(R.layout.photos_cell, parent,
						false);
			}
			ImageView img = (ImageView) itemview.findViewById(R.id.imageView1);
			img.setImageResource(mThumbIds[position]);
			return itemview;
			
		}
	}


}
