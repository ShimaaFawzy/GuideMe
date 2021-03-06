package adapters;

import com.example.expandablelistview.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

public class GalleryImageAdapter extends BaseAdapter {

	  private Context mContext;
	  private Integer[] mImageIds = {
              R.drawable.one,
              R.drawable.two,
              R.drawable.three,
              R.drawable.four
      };
	  
	  public GalleryImageAdapter(Context context) 
	    {
	        mContext = context;
	    }

	@Override
	  public int getCount() {
        return mImageIds.length;
    }

 
	@Override
	   public Object getItem(int position) {
        return position;
    }

  


	@Override
	  public long getItemId(int position) {
        return position;
    }


   

	@Override
	 // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup) 
    {
        // TODO Auto-generated method stub
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[index]);
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));
    
        i.setScaleType(ImageView.ScaleType.FIT_XY);

        return i;
    }



}
