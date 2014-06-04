package com.yinyien.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.vdfbvfdvb.R;


public class FragmentFour extends SherlockFragment {
	
	public static FragmentFour newInstance() {
		FragmentFour fragment = new FragmentFour();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment4, null);
		return v;
	}
}
