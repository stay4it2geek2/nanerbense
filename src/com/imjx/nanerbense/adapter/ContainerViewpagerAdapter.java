package com.imjx.nanerbense.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * fragment 页面管理适配器
 * @author linwm
 * 
 */
public class ContainerViewpagerAdapter extends FragmentStatePagerAdapter {

	private Context context;
	private ArrayList<Fragment> fragments;

	public ContainerViewpagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/**
	 * 
	 * @param fm
	 * @param fragments
	 */
	public ContainerViewpagerAdapter(FragmentManager fm,
			ArrayList<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	
	public void addFragment(int location, Fragment fragment) {
		this.fragments.add(location, fragment);
		this.notifyDataSetChanged();
	}

	
	public void addFragment(Fragment fragment) {
		this.fragments.add(fragment);
		this.notifyDataSetChanged();
	}
}
