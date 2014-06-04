package com.yinyien.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vdfbvfdvb.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yinyien.fragment.imagePath.Extra;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 * @author yinyien 
 */
public class ImagePagerActivity extends BaseActivity {


	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private static final String STATE_POSITION = "STATE_POSITION";

	DisplayImageOptions options;

	ViewPager pager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_pager);

		Bundle bundle = getIntent().getExtras();
		assert bundle != null;
		String[] imageUrls = bundle.getStringArray(Extra.IMAGES);
		String[] addressIntroduction=bundle.getStringArray(Extra.addressIntroduction);
		String[] Latitude=bundle.getStringArray(Extra.Latitude);
		String[] longitude=bundle.getStringArray(Extra.longitude);
		String[] addressName=bundle.getStringArray(Extra.addressName);

		int pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.resetViewBeforeLoading(true)
			.cacheOnDisc(true)
			.imageScaleType(ImageScaleType.EXACTLY)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.considerExifParams(true)
			.displayer(new FadeInBitmapDisplayer(300))
			.build();
		
		
		
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new ImagePagerAdapter(imageUrls,addressIntroduction,Latitude,longitude,addressName));
		pager.setCurrentItem(pagerPosition);
	}
	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}
	

	private class ImagePagerAdapter extends PagerAdapter {

		private String[] images;
		private String[] addressName;
		private String[] addressIntroduction;
		private LayoutInflater inflater;
		private String[] Latitude;
		private String[] longitude;
		ImagePagerAdapter(String[] images,String[] addressIntroduction,String[] Latitude,String[] longitude,String[] addressName) {
			this.images = images;
			this.addressIntroduction=addressIntroduction;
			this.Latitude=Latitude;
			this.longitude=longitude;
			this.addressName=addressName;
			inflater = getLayoutInflater();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			return images.length;
		}


		@Override
		public Object instantiateItem(ViewGroup view,final int position) {
			View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);
			assert imageLayout != null;
			ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
			TextView textView=(TextView)imageLayout.findViewById(R.id.text);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
			
			Button howgo=(Button)imageLayout.findViewById(R.id.button_howgo);
			howgo.setOnClickListener(new View.OnClickListener() {  
			      
			    @Override  
			    public void onClick(View v) {  
			        // TODO Auto-generated method stub  
//			    	  Intent intent = new Intent(ImagePagerActivity.this,luxian.class);
			    	  Intent intent = new Intent(ImagePagerActivity.this,RoutePlan.class);
	                    intent.putExtra("Latitude", Latitude[position]);
	                    Log.v("yinyien",Latitude[position]);
	                    intent.putExtra("longitude",longitude[position]);
	                    intent.putExtra("addressName", addressName[position]);
	                    startActivity(intent); 
			       
			    }  
			});  
			
			
			
			
			
			textView.setText(addressIntroduction[position]);
			imageLoader.displayImage(images[position], imageView, options, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					String message = null;
					switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case DECODING_ERROR:
							message = "Image can't be decoded";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					Toast.makeText(ImagePagerActivity.this, message, Toast.LENGTH_SHORT).show();

					spinner.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
				}
			});

			view.addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}

	
}
