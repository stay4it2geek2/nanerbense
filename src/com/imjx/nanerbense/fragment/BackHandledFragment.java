package com.imjx.nanerbense.fragment;


import com.imjx.nanerbense.interfaces.BackHandledListener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;


/**
 * 具备返回功能截获的基类fragment
 * 
 * @author linwm
 * 
 */
public abstract class BackHandledFragment extends Fragment {
	

	protected BackHandledListener mBackHandledInterface;
	private View baseView;

	/**
	 * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
	 */
	public abstract boolean onBackPressed();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!(getActivity() instanceof BackHandledListener)) {
			throw new ClassCastException(
					"Hosting Activity must implement BackHandledInterface");
		} else {

			this.mBackHandledInterface = (BackHandledListener) getActivity();
		}
	}


	@Override
	public void onStart() {
		super.onStart();

	}


	@Override
	public void onResume() {
		super.onResume();

	}

	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		
	}
}
