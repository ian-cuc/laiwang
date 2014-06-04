package com.yinyien.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.example.vdfbvfdvb.R;


public class FragmentFive extends SherlockFragment {
	
	public static FragmentFive newInstance() {
		FragmentFive fragment = new FragmentFive();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment5, null);
		this.setHasOptionsMenu(true);
		return v;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuItem ads = menu.add("ads");
        ads.setIcon(R.drawable.menu_apprec);
        ads.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        ads.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), "这个是广告。。。", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        MenuItem rate = (MenuItem) menu.add("推荐给好友");
        rate.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        rate.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), "推荐给好友", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        MenuItem about = (MenuItem) menu.add("关于");
        about.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        about.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), "关于", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        MenuItem login = (MenuItem) menu.add("登陆");
        login.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        login.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), "登陆", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        MenuItem exit = (MenuItem) menu.add("退出");
        exit.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        exit.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), "退出", Toast.LENGTH_LONG).show();
                return true;
            }
        });
		super.onCreateOptionsMenu(menu, inflater);
	}
}
