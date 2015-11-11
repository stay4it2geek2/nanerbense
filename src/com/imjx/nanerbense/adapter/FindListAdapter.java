/**
 * 
 */
package com.imjx.nanerbense.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.imjx.nanerbense.R;
import com.imjx.nanerbense.interfaces.ImageFileCache;
import com.imjx.nanerbense.model.FindList;

/**
 * @defined
 * @author linwm
 * @version 1.0
 */

public class FindListAdapter extends BaseAdapter {

	private List<FindList> findList;
	private Context context;
	private ImageLoader imageLoader;

	public FindListAdapter(List<FindList> findList, Context context) {
		super();
		this.findList = findList;
		this.context = context;
		imageLoader = new ImageLoader(Volley.newRequestQueue(context),
				new ImageFileCache());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return findList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public FindList getItem(int arg0) {
		// TODO Auto-generated method stub
		return findList.get(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.finditem, null);

			holder = new ViewHolder();

			convertView.setTag(holder);

			holder.iv_findItem = (NetworkImageView) convertView
					.findViewById(R.id.iv_findItem);
			holder.tv_findItem = (TextView) convertView
					.findViewById(R.id.tv_findItem);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_findItem.setText(findList.get(position)
				.getFindAbstractContent());
		holder.iv_findItem.setDefaultImageResId(R.drawable.vip);
		holder.iv_findItem.setErrorImageResId(R.drawable.youce);
		holder.iv_findItem.setImageUrl(
				"http://file.bmob.cn/"
						+ findList.get(position).getFindItemIMG().getUrl(),
				imageLoader);
		return convertView;
	}

	static class ViewHolder {
		NetworkImageView iv_findItem;
		TextView tv_findItem;
	}
}
