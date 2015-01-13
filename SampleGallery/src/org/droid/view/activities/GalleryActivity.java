package org.droid.view.activities;

import org.droid.controller.AssetGetter;
import org.droid.controller.Utils;
import org.droid.samplegallery.R;
import org.droid.view.adapter.GalleryAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryActivity extends FragmentActivity {
	
	private Context context=this;
	private GalleryAdapter galleryAdapter=new GalleryAdapter(context, null);
	
	private ImageView selectedImageIv;
	private GridView galleryGv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_gallery);
		
		selectedImageIv=(ImageView)findViewById(R.id.iv_selected_image);
		galleryGv=(GridView)findViewById(R.id.gv_gallery);
		
		
		galleryAdapter.setImageList(AssetGetter.getImagePaths(context));
		
		galleryGv.setAdapter(galleryAdapter);
		
		galleryGv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Bitmap selectedBmp=Utils.decodeSampledBitmapFromUri
						(galleryAdapter.getImageList().get(position).getImagePath(),
								200, 200);
				selectedImageIv.setImageBitmap(Utils.getSquareBitmap(selectedBmp));
			}
		});
	}

}
