package org.droid.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Utils {

	public static Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

		Bitmap bm = null;
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(path, options); 

		return bm;   
	}

	public static Bitmap getSquareBitmap(Bitmap srcBitmap){

		Bitmap dstBmp=null;

		if (srcBitmap.getWidth() >= srcBitmap.getHeight()){

			dstBmp = Bitmap.createBitmap(
					srcBitmap, 
					srcBitmap.getWidth()/2 - srcBitmap.getHeight()/2,
					0,
					srcBitmap.getHeight(), 
					srcBitmap.getHeight()
					);

		}else{

			dstBmp = Bitmap.createBitmap(
					srcBitmap,
					0, 
					srcBitmap.getHeight()/2 - srcBitmap.getWidth()/2,
					srcBitmap.getWidth(),
					srcBitmap.getWidth() 
					);
		}

		return dstBmp;
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float)height / (float)reqHeight);    
			} else {
				inSampleSize = Math.round((float)width / (float)reqWidth);    
			}   
		}

		return inSampleSize;    
	}

	/**
	 * Returns the actual width of screen of device in pixels
	 * @param context: context of activity
	 * @return width in pixels
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context){
		if(android.os.Build.VERSION.SDK_INT >= 13){
			DisplayMetrics metrics = new DisplayMetrics();
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
			return metrics.widthPixels;

		}else{
			Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).
					getDefaultDisplay();
			return display.getWidth();
		}
	}

	/**
	 * Returns the actual height of screen of device in pixels
	 * @param context: context of activity
	 * @return height in pixels
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Context context){
		if((android.os.Build.VERSION.SDK_INT >= 13)){
			DisplayMetrics metrics = new DisplayMetrics();
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
			return metrics.heightPixels;

		}else{
			Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).
					getDefaultDisplay();
			return display.getHeight();
		}
	}


}
