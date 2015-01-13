package org.droid.view.activities;

import org.droid.controller.AssetGetter;
import org.droid.samplegallery.R;
import org.droid.view.adapter.GalleryAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryActivity extends FragmentActivity {
	
	private Context context=this;
	private GalleryAdapter galleryAdapter;
	
	public ImageView selectedImageIv;
	private GridView galleryGv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_gallery);
		
		selectedImageIv=(ImageView)findViewById(R.id.iv_selected_image);
		galleryGv=(GridView)findViewById(R.id.gv_gallery);
		
		galleryAdapter=new GalleryAdapter(context, AssetGetter.getImagePaths(context));
		
		galleryGv.setAdapter(galleryAdapter);
	}

}
