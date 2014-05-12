package com.example.expandablelistview;

import com.placesdata.PhotoAlbums;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FullPhotoActivity extends FragmentActivity {
	static int ITEMS;
	MyAdapter mAdapter;
	ViewPager mPager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_photo);
		Drawable d = getResources().getDrawable(R.drawable.back);
		getActionBar().setBackgroundDrawable(d);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setIcon(R.drawable.logo);
		int pressedAlbum = getIntent().getIntExtra("press", 0);
		chooseAlbum(pressedAlbum);
		mAdapter = new MyAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		// to add animation
		//mPager.setPageTransformer(true, new ZoomOutPageTransformer());
		//or 
		mPager.setPageTransformer(true, new DepthPageTransformer());
		mPager.setAdapter(mAdapter);

		// Watch for button clicks.
		Button button = (Button) findViewById(R.id.first);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mPager.setCurrentItem(0);
			}
		});
		button = (Button) findViewById(R.id.last);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mPager.setCurrentItem(ITEMS - 1);
			}
		});

	}

	private void chooseAlbum(int pressedAlbum) {
		// b islam land m f
		switch (pressedAlbum) {
		case 0:
			ImageFragment.setAlbum(PhotoAlbums.mThumbIdsB);
			ITEMS = PhotoAlbums.mThumbIdsB.length;
			return;
		case 1:
			ImageFragment.setAlbum(PhotoAlbums.mThumbIdsI);
			ITEMS = PhotoAlbums.mThumbIdsI.length;
			return;
		case 2:
			ImageFragment.setAlbum(PhotoAlbums.mThumbIdsL);
			ITEMS = PhotoAlbums.mThumbIdsL.length;
			return;
		case 3:
			ImageFragment.setAlbum(PhotoAlbums.mThumbIdsM);
			ITEMS = PhotoAlbums.mThumbIdsM.length;
			return;
		case 4:
			ImageFragment.setAlbum(PhotoAlbums.mThumbIdsF);
			ITEMS = PhotoAlbums.mThumbIdsF.length;
			return;
		default:
			ImageFragment.setAlbum(PhotoAlbums.mThumbIdsB);
			ITEMS = PhotoAlbums.mThumbIdsB.length;
			return;
		}

	}

	public static class MyAdapter extends
			android.support.v4.app.FragmentStatePagerAdapter {

		public MyAdapter(android.support.v4.app.FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0: // Fragment # 0 - This will show image
				return ImageFragment.init(position);
			case 1: // Fragment # 1 - This will show image
				return ImageFragment.init(position);
			default:// Fragment # 2-9 - Will show list
				// return ArrayListFragment.init(position);
				return ImageFragment.init(position);
				// return null;
			}
		}

		@Override
		public int getCount() {
			return ITEMS;
		}

	}

	// this class to add animation first type
	public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
		private static final float MIN_SCALE = 0.85f;
		private static final float MIN_ALPHA = 0.5f;

		@Override
		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();
			int pageHeight = view.getHeight();

			if (position < -1) { // [-Infinity,-1)
				// This page is way off-screen to the left.
				view.setAlpha(0);

			} else if (position <= 1) {
				// Modify the default slide transition to shrink the page as
				// well
				float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
				float vertMargin = pageHeight * (1 - scaleFactor) / 2;
				float horzMargin = pageWidth * (1 - scaleFactor) / 2;
				if (position < 0) {
					view.setTranslationX(horzMargin - vertMargin / 2);
				} else {
					view.setTranslationX(-horzMargin + vertMargin / 2);
				}

				// Scale the page down (between MIN_SCALE and 1)
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

				// Fade the page relative to its size.
				view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
						/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));
			} else {
				// (1,+Infinity]
				// This page is way off-screen to the right.
				view.setAlpha(0);
			}

		}

	}

	// another class to add animation
	public class DepthPageTransformer implements ViewPager.PageTransformer {
		private static final float MIN_SCALE = 0.75f;

		@Override
		public void transformPage(View view, float position) {

			int pageWidth = view.getWidth();

			if (position < -1) { // [-Infinity,-1)
				// This page is way off-screen to the left.
				view.setAlpha(0);

			} else if (position <= 0) { // [-1,0]
				// Use the default slide transition when moving to the left page
				view.setAlpha(1);
				view.setTranslationX(0);
				view.setScaleX(1);
				view.setScaleY(1);

			} else if (position <= 1) { // (0,1]
				// Fade the page out.
				view.setAlpha(1 - position);

				// Counteract the default slide transition
				view.setTranslationX(pageWidth * -position);

				// Scale the page down (between MIN_SCALE and 1)
				float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
						* (1 - Math.abs(position));
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

			} else { // (1,+Infinity]
				// This page is way off-screen to the right.
				view.setAlpha(0);
			}
		}
	}

}
