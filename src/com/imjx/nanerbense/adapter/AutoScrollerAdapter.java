package com.imjx.nanerbense.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.imjx.nanerbense.interfaces.ImageFileCache;
import com.imjx.nanerbense.model.Banner;


/**
 * 自动轮播旗帜广告图适配器
 * 
 * @author imjx
 * 
 */
public class AutoScrollerAdapter extends PagerAdapter implements
		OnClickListener {

	private List<Banner> bannerlist;
	private Context mContext;

	private HashMap<String, NetworkImageView> viewList;

	public AutoScrollerAdapter(Context context, List<Banner> bannerlist) {
		mContext = context;
		this.bannerlist = bannerlist;
		viewList = new HashMap<String, NetworkImageView>();
		
		ImageLoader imageLoader= new ImageLoader(Volley.newRequestQueue(context), new ImageFileCache());

		for (int i = 0; i < bannerlist.size(); i++) {
			ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			NetworkImageView view = new NetworkImageView(context);
			view.setLayoutParams(param);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			Log.e("fdsffdsfdsfds",  bannerlist.get(i).getImageUrl().getUrl());
			view.setImageUrl("http://file.bmob.cn/"+bannerlist.get(i).getImageUrl().getUrl(),
					imageLoader);
			viewList.put(bannerlist.get(i).getObjectId(), view);
		}

	}
	
	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == (View) obj;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		NetworkImageView view = null;
		for (int i = 0; i < bannerlist.size(); i++) {
			view = viewList.get(bannerlist.get(position).getObjectId());
			if (view.getParent() != null) {
				ViewGroup viewGroup = (ViewGroup) view.getParent();
				viewGroup.removeView(view);
			}
			container.addView(view, 0);
		}
		view.setOnClickListener(this);

		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public void onClick(View arg0) {

		for (Entry<String, NetworkImageView> m : viewList.entrySet()) {

			if (m.getValue().equals(arg0)) {

//				ToastUtil.showToast(mContext, "点击id为" + m.getKey() + "的图");

			}

		}

	}

}
