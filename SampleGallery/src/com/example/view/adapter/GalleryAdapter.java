package com.example.view.adapter;

import java.util.ArrayList;
import java.util.Objects;

import com.example.samplegallery.R;

import com.example.controller.Utils;
import com.example.model.ImageInfo;
import com.example.view.activities.GalleryActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GalleryAdapter extends BaseAdapter {
	
	private Context context;
	
	private boolean isSelectionModeOn=false;
	private ArrayList<String> selectedImagePaths=new ArrayList<String>();
	private ArrayList<ImageInfo> imageList=new ArrayList<ImageInfo>();
	
	public GalleryAdapter(Context context, ArrayList<ImageInfo> imageList) {
		this.context=context;
		this.imageList=imageList;
	}

	@Override
	public int getCount() {
		return imageList.size();
	}

	@Override
	public Object getItem(int position) {
		return imageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return imageList.indexOf(imageList.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if(convertView==null){
			LayoutInflater mInflater=(LayoutInflater)context.getSystemService
					(Activity.LAYOUT_INFLATER_SERVICE);
			convertView=mInflater.inflate(R.layout.layout_gallery, parent, false);
			viewHolder=new ViewHolder();
			viewHolder.imageIv=(ImageView)convertView.findViewById(R.id.iv_image);
			viewHolder.selectedTv=(TextView)convertView.findViewById(R.id.tv_selected);
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		final ImageInfo imageInfo=(ImageInfo)getItem(position);
		
		if(imageInfo.isSelected()){
			viewHolder.selectedTv.setVisibility(View.VISIBLE);
		}
		else{
			viewHolder.selectedTv.setVisibility(View.GONE);
		}
		
		Bitmap galleryThumbBm=Utils.decodeSampledBitmapFromUri
				(imageInfo.getImagePath(), 200, 200);
		
		int requiredImageWidth=Utils.getScreenWidth(context)/4;
		
		RelativeLayout.LayoutParams imageParams=new RelativeLayout.LayoutParams
				(requiredImageWidth, requiredImageWidth);
		viewHolder.imageIv.setLayoutParams(imageParams);
		viewHolder.imageIv.setImageBitmap(Utils.getSquareBitmap(galleryThumbBm));
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bitmap selectedBmp=Utils.decodeSampledBitmapFromUri
						(imageInfo.getImagePath(), 200, 200);
				((GalleryActivity)context).selectedImageIv.setImageBitmap(Utils.getSquareBitmap(selectedBmp));
				if(isSelectionModeOn){
					if(imageInfo.isSelected()){
						imageInfo.setSelected(false);
						selectedImagePaths.remove(imageInfo.getImagePath());
						viewHolder.selectedTv.setVisibility(View.GONE);
					}
					else{
						imageInfo.setSelected(true);
						selectedImagePaths.add(imageInfo.getImagePath());
						viewHolder.selectedTv.setVisibility(View.VISIBLE);
					}
					if(selectedImagePaths.isEmpty()){
						isSelectionModeOn=false;
					}
				}
			}
		});
		
		convertView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				if(isSelectionModeOn){
					//we don't have to do anything in long click, while selection mode is turned on
				}
				else{
					isSelectionModeOn=true;
					selectedImagePaths.add(imageInfo.getImagePath());
					imageInfo.setSelected(true);
					viewHolder.selectedTv.setVisibility(View.VISIBLE);
				}
				return true;
			}
		});
		
		return convertView;
	}
	
	private class ViewHolder{
		private ImageView imageIv;
		private TextView selectedTv;
	}

	public ArrayList<ImageInfo> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<ImageInfo> imageList) {
		this.imageList = Objects.requireNonNull(imageList, "List cannot be null");
	}

}
