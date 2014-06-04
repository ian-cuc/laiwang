package com.yinyien.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.vdfbvfdvb.R;
import com.yinyien.fragment.imagePath.Extra;

import InternetContact.json;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
/**
 * @author yinyien 
 */
public class signinActivity extends Activity{

	Button button_signin;
	Button no_signin_button;
	Button button_signup;
	EditText username;
	EditText password;
	
	String[] imageUrls;
	String[] addressName;
	String[] imageAdd;
	String[] addressIntroduction;
	String[] Latitude;
	String[] longitude;
	StringBuffer sb_;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.login);
	        button_signin=(Button)findViewById(R.id.signin_button);
	        no_signin_button=(Button)findViewById(R.id.no_signin_button);
	        button_signup=(Button)findViewById(R.id.signup_button);
	        
	        username=(EditText)findViewById(R.id.username_edit);
	        password=(EditText)findViewById(R.id.password_edit);
//	        http://1.longinus.sinaapp.com/signin?
	        sb_= new StringBuffer(256);

	        OnClickListener clickListener = new OnClickListener(){
				public void onClick(View v) {
					if (button_signin.equals(v)) {
			            sb_.delete(0,sb_.length());

						String user=username.getText().toString();
						String psw=password.getText().toString();
			            sb_.append("http://1.longinus.sinaapp.com/signin?");

						 sb_.append("username=");
						 sb_.append(user);
			             sb_.append("&");
			             sb_.append("password=");
			             sb_.append(psw);

			             new DownloadWebpageText().execute(sb_.toString());
			            
					}
					if (no_signin_button.equals(v)) {
						
						Intent intent = new Intent();
						intent.setClass(signinActivity.this, MainActivity.class);
						intent.putExtra("signin_url",sb_.toString());
						intent.putExtra("flag",0);
						intent.putExtra("user", "");
						intent.putExtra("psw", "");
	                    startActivity(intent); 
					}
					if (button_signup.equals(v)) {
			            sb_.delete(0,sb_.length());
			            String user=username.getText().toString();
						String psw=password.getText().toString();
			            sb_.append("http://1.longinus.sinaapp.com/signup?");

						 sb_.append("username=");
						 sb_.append(user);
			             sb_.append("&");
			             sb_.append("password=");
			             sb_.append(psw);
			             new DownloadWebpageText().execute(sb_.toString());

//						Intent intent = new Intent();
//						intent.setClass(signinActivity.this, MainActivity.class);
//	                    startActivity(intent); 
					}
					
					
				}
	        };

	        button_signin.setOnClickListener(clickListener);
	        no_signin_button.setOnClickListener(clickListener);
	        button_signup.setOnClickListener(clickListener);

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
			    String s=result.substring(0,13);
				if(s.equals("user unexist!"))
				{
		             Toast.makeText(signinActivity.this, "用户或者密码不正确", Toast.LENGTH_LONG).show();
		             sb_.delete(0,sb_.length());
				}
				else{

		            Intent intent = new Intent();
		            intent.setClass(signinActivity.this, MainActivity.class);
		            
		            sb_.delete(0,sb_.length());
		            String user=username.getText().toString();
					String psw=password.getText().toString();
		            sb_.append("http://1.longinus.sinaapp.com/signin?");
					 sb_.append("username=");
					 sb_.append(user);
		             sb_.append("&");
		             sb_.append("password=");
		             sb_.append(psw);
		             
					intent.putExtra("signin_url",sb_.toString());
					intent.putExtra("flag",1);
					intent.putExtra("user",user);
					intent.putExtra("psw",psw);


					Log.v("signin", sb_.toString());
	                startActivity(intent); 
				}
				
		}
	 }
	 private String downloadUrl(String myurl) throws IOException {
		    InputStream is = null;
		    // 先显示获取到的前4096个字节
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
		        Log.d("yinyien", "The response is: " + myurl);

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
