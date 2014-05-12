package com.example.expandablelistview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImageFragment extends Fragment {

	int fragVal;
	public static Integer[] mThumbIds ;
	public static void setAlbum(Integer[] alb){
		mThumbIds=alb;
	}
	public static Integer[] getAlbum() {
		return mThumbIds;
	}

	static ImageFragment init(int val) {
		ImageFragment truitonFrag = new ImageFragment();
		// Supply val input as an argument.
		Bundle args = new Bundle();
		args.putInt("val", val);
		truitonFrag.setArguments(args);
		return truitonFrag;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = inflater.inflate(R.layout.activity_image_fragment, container,
				false);
		ImageView img= (ImageView) layoutView.findViewById(R.id.img);
		img.setImageResource(mThumbIds[fragVal]);
		
		return layoutView;
	}

}
