package com.yinyien.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yinyien.fragment.FragmentFive;
import com.yinyien.fragment.FragmentFour;
import com.yinyien.fragment.FragmentOne;
import com.yinyien.fragment.FragmentThree;
import com.yinyien.fragment.FragmentTwo;

public class MainFragmentAdapter extends FragmentPagerAdapter {
	private String[] titleStr = {"地点","我的"};
	
	//,"第二","第三","第四","第五"
	public MainFragmentAdapter(FragmentManager fm) {
		super(fm);
		
	}

	public MainFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return FragmentOne.newInstance();
		case 1:
			return FragmentTwo.newInstance();
//		case 2:
//			return YouDaoFragmentThree.newInstance();
//		case 3:
//			return YouDaoFragmentFour.newInstance();
//		case 4:
//			return YouDaoFragmentFive.newInstance();
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleStr[position];
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		return titleStr.length;
	}
}
