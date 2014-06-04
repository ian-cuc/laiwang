package com.yinyien.activity;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.VersionInfo;

import InternetContact.callUrl;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
//import baidumapsdk.demo.DemoApplication;



import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.viewpagerindicator.TabPageIndicator;
import com.yinyien.adapter.MainFragmentAdapter;
import com.yinyien.fragment.imagePath.Extra;
import com.example.vdfbvfdvb.R;
/**
 * @author yinyien 
 */
public class MainActivity extends SherlockFragmentActivity {
	private View mainActionBarView;
	private FragmentPagerAdapter adapter;
	public static ImageLoader imageLoader = ImageLoader.getInstance();//建立imageloader的实例方便后面使用；
	public static ImageLoader imageLoader_1 = ImageLoader.getInstance();//建立imageloader的实例方便后面使用；

	String result;
	public static LocationClient mLocationClient = null;//百度定位API的类的声明；必须在主线程声明
	public static LocationClient mLocationClient_1 = null;//百度定位API的类的声明；必须在主线程声明

	BDLocation  mylocation=null;//地理位置信息；
	String signin_url;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		
		mLocationClient = new LocationClient((laiwangApplication)this.getApplication());//声明LocationClient类
		Bundle bundle = getIntent().getExtras();

		signin_url=bundle.getString("signin_url");


        initActionBar();
        initViews();
    }
    @Override
	protected void onResume() {
	    laiwangApplication app = (laiwangApplication)this.getApplication();
		if (!app.m_bKeyRight) {
//            text.setText(R.string.key_error);
//            text.setTextColor(Color.RED);
		}
		else{
//			text.setTextColor(Color.YELLOW);
//			text.setText("欢迎使用百度地图Android SDK v"+VersionInfo.getApiVersion());
		}
		super.onResume();
	}
    @Override
	// 建议在APP整体退出之前调用MapApi的destroy()函数，不要在每个activity的OnDestroy中调用，
    // 避免MapApi重复创建初始化，提高效率
	protected void onDestroy() {
    	laiwangApplication app = (laiwangApplication)this.getApplication();
		if (app.mBMapManager != null) {
			app.mBMapManager.destroy();
			app.mBMapManager = null;
		}
		super.onDestroy();
		System.exit(0);
	}
	

    
    private void initViews() {
		adapter = new MainFragmentAdapter(getSupportFragmentManager(), this);
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
	}
    
    private void initActionBar() {
		
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		
        getSupportActionBar().setBackgroundDrawable(getResources()
        		.getDrawable(R.drawable.actionbar_tab_bg));
        mainActionBarView = LayoutInflater.from(this).
        		inflate(R.layout.main_action_bar, null);
		getSupportActionBar().setCustomView(mainActionBarView);
	}
    
    
    
    
    
//    public void myClickHandler(View view) {
//        // 从UI的text字段中得到URL
//        String stringUrl = "http://www.weather.com.cn/data/sk/101010100.html";
//        ConnectivityManager connMgr = (ConnectivityManager) 
//            getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            new DownloadWebpageText().execute(stringUrl);
//        } else {
////            textView.setText("No network connection available.");
//        }
//    }

     // 使用AsyncTask创建一个独立于主UI线程之外的任务. 并使用URL字符串创建一个HttpUrlConnection对象。 
     // 一旦连接建立，AsyncTask则将网页内容作为一个InputStream对象进行下载。
     // 最终，InputStream对象会被转换为一个字符串对象，并被AsyncTask的onPostExecute方法显示在UI上。
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
        	Log.v("mylog",result);
       }
    }
     
     private String downloadUrl(String myurl) throws IOException {
    	    InputStream is = null;
    	    // 先显示获取到的前500个字节
    	    // 网页内容
    	    int len = 500;

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
     
}

