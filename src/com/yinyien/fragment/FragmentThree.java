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

public class FragmentThree extends SherlockFragment {
	
	public static FragmentThree newInstance() {
		FragmentThree fragment = new FragmentThree();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment3, null);
		this.setHasOptionsMenu(true);
		return v;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem sync = menu.add("sync");
        sync.setIcon(R.drawable.menu_sync);
        sync.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        sync.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), "同步", Toast.LENGTH_LONG).show();
                return true;
            }
        });
		super.onCreateOptionsMenu(menu, inflater);
	}
}
