/**
 * 
 */
package com.imjx.nanerbense.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imjx.nanerbense.R;

/** @defined 
 * @author linwm 
 * @version 1.0  
 */

public class SecrectFragment extends BackHandledFragment {

	View view ;
	@Override
	public boolean onBackPressed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.fragment_secrect, null);

		return view;
	}

}



