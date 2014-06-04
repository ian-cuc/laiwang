package com.yinyien.activity;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
//基本的activity，
public abstract class BaseActivity extends Activity {

//	protected ImageLoader imageLoader = ImageLoader.getInstance();
//	//获取imageloader的实例
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main_menu, menu);
//		return true;
//	}
//	//重载OptionsMenu菜单
//	//下面是OptionsMenu各项的点击函数
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//			case R.id.item_clear_memory_cache:
//				imageLoader.clearMemoryCache();//清理内存缓存
//				return true;
//			case R.id.item_clear_disc_cache:
//				imageLoader.clearDiscCache();//清理disc的缓存
//				return true;
//			default:
//				return false;
//		}
//	}
}
