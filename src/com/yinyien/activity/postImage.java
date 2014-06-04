package com.yinyien.activity;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.vdfbvfdvb.R;
import com.yinyien.fragment.FragmentOne;

import InternetContact.UploadUtil;
import InternetContact.UploadUtil.OnUploadProcessListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * @author yinyien 
 */
public class postImage extends Activity implements OnClickListener{
	
	
    private static String requestURL = "http://1.longinus.sinaapp.com/upload";  
    private Button selectImage, uploadImage,takePhoto;  
    private ImageView imageView;  
    private EditText place;
    private EditText describe;
    private String picPath = null;  
    private String user ;
    private String psw; 
    
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照  
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择  
    private static final int PHOTO_REQUEST_CUT = 3;// 结果  
    
    File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());

    String[] arrayString = { "拍照", "相册" };  
    String title = "上传照片";  
  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.postimage);  
  
        selectImage = (Button) this.findViewById(R.id.selectImage);  
        uploadImage = (Button) this.findViewById(R.id.uploadImage); 
//        takePhoto=(Button) this.findViewById(R.id.takePhoto); 
        
        place=(EditText)this.findViewById(R.id.place);
        describe=(EditText)this.findViewById(R.id.describe);

        selectImage.setOnClickListener(this);  
        uploadImage.setOnClickListener(this);  
//        takePhoto.setOnClickListener(this);  

        user=getIntent().getExtras().getString("user");
        psw =getIntent().getExtras().getString("psw");

  
        imageView = (ImageView) this.findViewById(R.id.imageView);  
//        progressDialog = new ProgressDialog(this);  
//        progressBar = (ProgressBar) findViewById(R.id.progressBar1);  
  
    }
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选择图片")
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        // 调用系统的拍照功能
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 指定调用相机拍照后照片的储存路径
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
                        startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                    }
                })
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setType("image/*");  
                        intent.setAction(Intent.ACTION_GET_CONTENT);  
                        startActivityForResult(intent,PHOTO_REQUEST_GALLERY);
                    }
                }).show();
    }

  
    @Override  
    public void onClick(View v) {  
        switch (v.getId()) {  
        case R.id.selectImage:  
//            /*** 
//             * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 
//             */  
//            Intent intent = new Intent();  
//            intent.setType("image/*");  
//            intent.setAction(Intent.ACTION_GET_CONTENT);  
//            startActivityForResult(intent,PHOTO_REQUEST_GALLERY);  
//            break;  
        	showDialog();
        	break; 
//        case R.id.takePhoto:  
//        	 Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//             // 指定调用相机拍照后照片的储存路径
//             intent1.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
//             startActivityForResult(intent1, PHOTO_REQUEST_TAKEPHOTO);
//        	
//            break;

        case R.id.uploadImage:  
            if (picPath == null) {  
  
                Toast.makeText(postImage.this, "请选择图片！", 1000).show();  
            } else {  
                final File file = new File(picPath);  
  
                if (file != null) {  
                	String fileKey = "pic";
                	UploadUtil uploadUtil = UploadUtil.getInstance();;
////                	uploadUtil.setOnUploadProcessListener(this); //设置监听器监听上传状态
//
                	Map<String, String> params = new HashMap<String, String>();
                	params.put("address_name", place.getText().toString());
                	params.put("introduction", describe.getText().toString());
                	BigDecimal lat= new BigDecimal(FragmentOne.mylocation.getLatitude());
                	BigDecimal longit= new BigDecimal(FragmentOne.mylocation.getLongitude());
                	params.put("latitude", lat.toString());
                	params.put("longitude", longit.toString());
                	params.put("username", user);
//                	params.put("password", psw);

                	uploadUtil.uploadFile( picPath,fileKey, requestURL,params);
//                	new uploadImage().execute(picPath);
//{"address_id":2,"address_name":"中国传媒大学北门喷泉","image_url":"http://www.cuc.edu.cn/fengguang/images/2.jpg","introduction":"北门喷泉，有金鱼","latitude":39.91501998901367,"longitude":116.55581665039062}
                }  
            }  
            break;  
        default:  
            break;  
        }  
    }
    private class uploadImage extends AsyncTask<String,Integer,String > {
        @Override
        protected String doInBackground(String... urls) {

            // 参数来自execute(),调用params[0]得到URL
            
            	String fileKey = "pic";
            	UploadUtil uploadUtil = UploadUtil.getInstance();;
//            	uploadUtil.setOnUploadProcessListener(this); //设置监听器监听上传状态

            	Map<String, String> params = new HashMap<String, String>();
            	params.put("orderId", "11111");
            	
            	uploadUtil.uploadFile( urls[0],fileKey, requestURL,params);
            	
                return "hahaha";
            
        }
        
        // onPostExecute显示AsyncTask结果.
        @Override
        protected void onPostExecute(String result) {
        	Log.v("mylog",result);
       }
    }
  
    @Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
    	Log.v("onActivityResult", "onActivityResult");

        if (requestCode == PHOTO_REQUEST_GALLERY) {  
            /**  
             * 当选择的图片不为空的话，在获取到图片的途径  
             */  
        	Log.v("PHOTO_REQUEST_GALLERY", ""+PHOTO_REQUEST_GALLERY);
            Uri uri = data.getData();  
            Log.e("uploadFile", "uri = " + uri);  
            try {  
                String[] pojo = { MediaStore.Images.Media.DATA };  
  
                Cursor cursor = managedQuery(uri, pojo, null, null, null);  
                if (cursor != null) {  
                    ContentResolver cr = this.getContentResolver();  
                    int colunm_index = cursor  
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);  
                    cursor.moveToFirst();  
                    String path = cursor.getString(colunm_index);  
                    /*** 
                     * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了， 
                     * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以 
                     */  
                    if (path.endsWith("jpg") || path.endsWith("png")) {  
                        picPath = path;  
                        Bitmap bitmap = BitmapFactory.decodeStream(cr  
                                .openInputStream(uri));  
                        imageView.setImageBitmap(bitmap);  
                    } else {  
                        alert();  
                    }  
                } else {  
                    alert();  
                }  
  
            } catch (Exception e) {  
            }  
        }
        if(requestCode ==PHOTO_REQUEST_TAKEPHOTO)
        {
        	
        	
        	 /**  
             * 当选择的图片不为空的话，在获取到图片的途径  
             */  
            Uri uri =  Uri.fromFile(tempFile);
            Log.e("uploadFile", "uri = " + uri);  
            try {  
            	String path;
  
            	path=tempFile.getAbsolutePath();  
            	Log.v("fwefecsdcfcesfcewdf", path);
                    if (path.endsWith("jpg") || path.endsWith("png")||path.endsWith("JPG") ) {  
                        picPath = path;  
                        imageView.setImageURI(Uri.fromFile(tempFile));
                    } else { 
                    	Log.v("ddwqdwqdq", "fefewfewfw");
                        alert();  
                    }  
                
  
            } catch (Exception e) {  
            }  

//                    imageView.setImageURI(Uri.fromFile(tempFile));
                

        }
        if( requestCode ==PHOTO_REQUEST_CUT){
        	if (data != null) 
            	Log.v("PHOTO_REQUEST_CUT", ""+PHOTO_REQUEST_CUT);

            setPicToView(data);
    	}
  
        super.onActivityResult(requestCode, resultCode, data);  
    	} 
    
    private void setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            imageView.setImageBitmap(photo);  

        }
    }
    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

  
    private void alert() {  
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")  
                .setMessage("您选择的不是有效的图片")  
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int which) {  
                        picPath = null;  
                    }  
                }).create();  
        dialog.show();  
    }  
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

}
