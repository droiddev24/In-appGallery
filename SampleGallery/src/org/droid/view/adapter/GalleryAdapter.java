package org.droid.view.adapter;

import java.util.ArrayList;

import org.droid.controller.Utils;
import org.droid.model.ImageInfo;
import org.droid.samplegallery.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GalleryAdapter extends BaseAdapter {
	
	private Context context;
	
	private ArrayList<ImageInfo> imageList=new ArrayList<ImageInfo>();
	
	public GalleryAdapter(Context context, ArrayList<ImageInfo> imageList) {
		this.context=context;
		this.imageList=imageList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return imageList.indexOf(imageList.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(convertView==null){
			LayoutInflater mInflater=(LayoutInflater)context.getSystemService
					(Activity.LAYOUT_INFLATER_SERVICE);
			convertView=mInflater.inflate(R.layout.layout_gallery, parent, false);
			viewHolder=new ViewHolder();
			viewHolder.imageIv=(ImageView)convertView.findViewById(R.id.iv_image);
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		Bitmap galleryThumbBm=Utils.decodeSampledBitmapFromUri
				(imageList.get(position).getImagePath(), 200, 200);
		
		int requiredImageWidth=Utils.getScreenWidth(context)/4;
		
		RelativeLayout.LayoutParams imageParams=new RelativeLayout.LayoutParams
				(requiredImageWidth, requiredImageWidth);
		viewHolder.imageIv.setLayoutParams(imageParams);
		viewHolder.imageIv.setImageBitmap(Utils.getSquareBitmap(galleryThumbBm));
		
		return convertView;
	}
	
	private class ViewHolder{
		ImageView imageIv;
	}

	public ArrayList<ImageInfo> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<ImageInfo> imageList) {
		this.imageList = imageList;
	}

}
