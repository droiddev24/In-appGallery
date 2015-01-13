package org.droid.controller;

import java.util.ArrayList;

import org.droid.model.ImageInfo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class AssetGetter {
	
	public static ArrayList<ImageInfo> getImagePaths(Context context){
		
		ArrayList<ImageInfo> imageList=new ArrayList<ImageInfo>();
		
		String[] projection = {MediaStore.Images.Media.DATA};
		// Create the cursor pointing to the SDCard
		Cursor cursor=((Activity)context).getContentResolver().query
				(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
				projection,
				null,
				null,
				null);

		if(cursor.moveToFirst()){
			do{
				ImageInfo imageInfo=new ImageInfo();
				// Get the column index of the thumbnails Image ID
				int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				//get the path of image from column index and put it into model class
				imageInfo.setImagePath(cursor.getString(columnIndex));
				imageList.add(imageInfo);
			}while(cursor.moveToNext());
		}
		
		return imageList;
	}

}
