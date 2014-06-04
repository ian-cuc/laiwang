package com.yinyien.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import InternetContact.json;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.example.vdfbvfdvb.R;

import static com.yinyien.fragment.imagePath.IMAGES;










//
//import com.nostra13.example.universalimageloader.ImagePagerActivity;
//import com.nostra13.example.universalimageloader.Constants.Extra;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yinyien.activity.ImagePagerActivity;
import com.yinyien.activity.MainActivity;
import com.yinyien.activity.postImage;
import com.yinyien.fragment.imagePath.Extra;

public class FragmentOne extends SherlockFragment {
	
	DisplayImageOptions options;
//	protected ImageLoader imageLoader = ImageLoader.getInstance();//com.nostra13.universalimageloader.core.ImageLoader
	String[] imageUrls;
	String[] addressName;
	String[] imageAdd;
	String[] addressIntroduction;
	String[] Latitude;
	String[] longitude;
	public static BDLocation mylocation=null;
	protected AbsListView listView;
	ItemAdapter itemAdapter;
	int tag;
	int flag;
	String user;
	String psw;

	public static FragmentOne newInstance() {
		FragmentOne fragment = new FragmentOne();
		return fragment;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
				View v = inflater.inflate(R.layout.fragment1, null);
				this.setHasOptionsMenu(true);

		return v;
	}
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Bundle bundle = getActivity().getIntent().getExtras();
		flag=bundle.getInt("flag");
		user=bundle.getString("user");
		psw=bundle.getString("psw");

		String signin_url=bundle.getString("signin_url");
		Log.v("yinyien111111111", signin_url);
		imageUrls=IMAGES;
		tag=0;
		 MainActivity.mLocationClient.registerLocationListener(new BDLocationListener() {  
	            @Override  
	            public void onReceiveLocation(BDLocation location) {  
	                // TODO Auto-generated method stub  
	                if (location == null) {  
	                    return;  
	                }  
	              mylocation=location;
	                StringBuffer sb_= new StringBuffer(256);
	                sb_.append("http://1.longinus.sinaapp.com/search?");
	                sb_.append("latitude=");
	                sb_.append(location.getLatitude());
	                sb_.append("&");
	                sb_.append("longitude=");
	                sb_.append(location.getLongitude());
	                new DownloadWebpageText().execute(sb_.toString());
//	                Log.v("mylog",sb.toString());
	          }  
	            @Override  
	            public void onReceivePoi(BDLocation location) {  
	            }  
	        });  //location注册监听器；随时获取Location；
		    
		    LocationClientOption option = new LocationClientOption();
		    option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		    option.setCoorType("bd09ll");//返回的定位结果是百度经纬度，默认值gcj02
//		    option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
		    option.setIsNeedAddress(true);//返回的定位结果包含地址信息
		    option.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
		    MainActivity.mLocationClient.setLocOption(option);
		    MainActivity.mLocationClient.start();
		    if (MainActivity.mLocationClient != null && MainActivity.mLocationClient.isStarted())
		    	MainActivity.mLocationClient.requestLocation();
		    	else 
		    	 Log.d("LocSDK3", "locClient is null or not started");
		
			 
		options = new DisplayImageOptions.Builder()//显示图片的可选项设置
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(20))
		.build();
		
//		listView = (ListView) findViewById(android.R.id.list);
		//获取listview的实例，findviewById是activity的特有方法，在fragment里面并不存在。
		listView =(ListView) getActivity().findViewById(R.id.list);//0x7f040037
//		((ListView) listView).setAdapter(new ItemAdapter());
		

		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				startImagePagerActivity(position);

			}
		});
		
	}
	private void startImagePagerActivity(int position) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		intent.putExtra(Extra.Latitude, Latitude);
		intent.putExtra(Extra.longitude, longitude);
		intent.putExtra(Extra.addressIntroduction, addressIntroduction);
		intent.putExtra(Extra.addressName, addressName);


		startActivity(intent);
	}


	class ItemAdapter extends BaseAdapter {
//
		private ImageLoadingListener imageLoadingListener = new AnimateFirstDisplayListener();

		//
		private class ViewHolder {
			public TextView text;
			public ImageView image;
		}

		@Override
		public int getCount() {
			return addressName.length;
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = getLayoutInflater(null).inflate(R.layout.item_list_image, parent, false);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.text);
				holder.image = (ImageView) view.findViewById(R.id.image);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			holder.text.setText(addressName[position]);

			// imageLoader.displayImage(imageUrls[position], holder.image, options，imageLoadingListener);
			MainActivity.imageLoader.displayImage(imageUrls[position], holder.image,options,imageLoadingListener);
			//displayimage函数有4个参数，最后一个为imageloadinglistener,
			//animateFirstListener extends SimpleImageLoadingListener
			//而SimpleImageLoadingListener又implements ImageLoadingListener
			//void com.nostra13.universalimageloader.core.ImageLoader.displayImage(String uri, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener)
			return view;
		}
	}
	
private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

	static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) 
	{
		if (loadedImage != null) 
		{
			ImageView imageView = (ImageView) view;
			boolean firstDisplay = !displayedImages.contains(imageUri);
			if (firstDisplay) 
			{
				FadeInBitmapDisplayer.animate(imageView, 500);
				displayedImages.add(imageUri);
			}
		}
	}
}


private class DownloadWebpageText extends AsyncTask<String,Integer,String > {
    @Override
    protected String doInBackground(String... urls) {

        // 参数来自execute(),调用params[0]得到URL
        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return "无法获取网页，URL可能无效！Unable to retrieve web page. URL may be invalid.";
        }
    }
    
    // onPostExecute显示AsyncTask结果.
	@Override
    protected void onPostExecute(String result) {
    	Log.v("hahaha",result);
	    json.jsonParse(result);
		imageUrls=json.imageAdd;
		addressName=json.addressName;
		addressIntroduction=json.addressIntroduction;
		Latitude=json.Latitude;
		longitude=json.longitude;
		if(tag==0)
		{
		itemAdapter=new ItemAdapter();
		((ListView) listView).setAdapter(itemAdapter);
		}
		else
		{
			itemAdapter.notifyDataSetChanged();
		Log.v("yinyien", tag+"yinyien");
		}
		}
}
 
 private String downloadUrl(String myurl) throws IOException {
	    InputStream is = null;
	    // 先显示获取到的前500个字节
	    // 网页内容
	    int len = 4096;

	    try {
	        URL url = new URL(myurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 );
	        conn.setConnectTimeout(15000);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        // 开始查询
	        conn.connect();
	        int response = conn.getResponseCode();
	        Log.d("yinyien", "The response is: " + response);
	        is = conn.getInputStream();

	        // 将InputStream转化为string
	        String contentAsString = readIt(is, len);
	        return contentAsString;

	    // 确保当app用完InputStream对象后关闭它。
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
	}
 public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}
 @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuItem add = menu.add("add");
		add.setIcon(R.drawable.menu_add_note);
		add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		add.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
// 		    MainActivity.mLocationClient.start();
        	 Intent intent = new Intent();
        	 intent.putExtra("flag", flag);
        	 intent.putExtra("user", user);
        	 intent.putExtra("psw", psw);
        	 intent.setClass(getActivity(), postImage.class);
    		startActivity(intent);

//     		intent.putExtra(Extra.IMAGES, imageUrls);
//     		intent.putExtra(Extra.IMAGE_POSITION, position);
//     		intent.putExtra(Extra.Latitude, Latitude);
//     		intent.putExtra(Extra.longitude, longitude);
//     		intent.putExtra(Extra.addressIntroduction, addressIntroduction);

             return true;
         }
     });
//     MenuItem search = menu.add("search");
//     search.setIcon(R.drawable.menu_search);
//     search.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//     search.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//         @Override
//         public boolean onMenuItemClick(MenuItem item) {
//             Toast.makeText(getActivity(), "查找", Toast.LENGTH_LONG).show();
//             return true;
//         }
//     });
//     
     MenuItem sync = menu.add("sync");
     sync.setIcon(R.drawable.menu_sync);
     sync.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
     sync.setOnMenuItemClickListener(new OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
//            Toast.makeText(getActivity(), "同步", Toast.LENGTH_LONG).show();
        	 tag=tag+1;
 		    MainActivity.mLocationClient.stop();
 		   MainActivity.mLocationClient.start();

             return true;
         }
     });
		super.onCreateOptionsMenu(menu, inflater);
	}
}