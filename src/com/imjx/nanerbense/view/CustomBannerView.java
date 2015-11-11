package com.imjx.nanerbense.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.android.volley.toolbox.NetworkImageView;
import com.imjx.nanerbense.R;
import com.imjx.nanerbense.adapter.AutoScrollerAdapter;
import com.imjx.nanerbense.model.Banner;


/**
 * 自定义自动循环滚动旗帜广告view
 * 
 * @author linwm
 * 
 */
public class CustomBannerView extends RelativeLayout {
	private Context context;

	private HashMap<String, NetworkImageView> n;

	private ViewPager viewpager;

	private AutoScrollerAdapter adapter;

	private LinearLayout viewGroup;

	private ImageView dot, dots[];

	private Runnable runnable;

	private int autoChangeTime = 3000;

	private ArrayList<Banner> bannerList;



	/**
	 * 构造函数区域
	 * 
	 * @param context
	 * @param attrs
	 */
	public CustomBannerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init(context);
	}

	public CustomBannerView(Context context) {
		super(context);
		init(context);

	}

	public CustomBannerView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);

	}

	public void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflater.inflate(R.layout.custom_banner_view, this);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		download();
	}

	private void download() {
        //下载图片
        BmobQuery<Banner> query=new BmobQuery<Banner>();
        query.order("-createdAt");// 按照时间降序
        query.findObjects(context, new FindListener<Banner>() {
			
			@Override
			public void onSuccess(List<Banner> bannerList) {
            	
            	initViewPager(bannerList);
			
			}
			
			@Override
			public void onError(String arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }
	
	private void initViewPager(List<Banner> bannerList) {
		adapter = new AutoScrollerAdapter(context, bannerList);
		viewpager.setAdapter(adapter);
		viewpager.setOnPageChangeListener(myOnPageChangeListener);

		initDot();

		runnable = new Runnable() {
			@Override
			public void run() {
				int next = viewpager.getCurrentItem() + 1;
				if (next >= adapter.getCount()) {
					next = 0;
				}
				viewHandler.sendEmptyMessage(next);
			}
		};
		viewHandler.postDelayed(runnable, autoChangeTime);
	}

	// 初始化dot视图
	private void initDot() {
		viewGroup = (LinearLayout) findViewById(R.id.viewGroup);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				15, 15);
		layoutParams.setMargins(4, 3, 4, 3);

		dots = new ImageView[adapter.getCount()];
		for (int i = 0; i < adapter.getCount(); i++) {
			dot = new ImageView(context);

			dot.setLayoutParams(layoutParams);
			dots[i] = dot;
			dots[i].setTag(i);
			dots[i].setOnClickListener(onClick);

			if (i == 0) {
				dots[i].setBackgroundResource(R.drawable.banner_pagecontrol_normal);
			} else {
				dots[i].setBackgroundResource(R.drawable.banner_pagecontrol_selected);
			}

			viewGroup.addView(dots[i]);
		}
	}

	OnPageChangeListener myOnPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			setCurDot(arg0);
			viewHandler.removeCallbacks(runnable);
			viewHandler.postDelayed(runnable, autoChangeTime);
		}

	};

	// 实现dot点击响应功能
	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			setCurView(position);
		}

	};

	/**
	 * 设置当前的引导页
	 */
	private void setCurView(int position) {
		if (position < 0 || position > adapter.getCount()) {
			return;
		}
		viewpager.setCurrentItem(position);
	}

	/**
	 * 选中当前引导小点
	 */
	private void setCurDot(int position) {
		for (int i = 0; i < dots.length; i++) {
			if (position == i) {
				dots[i].setBackgroundResource(R.drawable.banner_pagecontrol_normal);
			} else {
				dots[i].setBackgroundResource(R.drawable.banner_pagecontrol_selected);
			}
		}
	}

	/**
	 * 每隔固定时间切换广告栏图
	 */
	@SuppressLint("HandlerLeak")
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			setCurView(msg.what);
		}

	};
}
