package com.imjx.nanerbense.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.imjx.nanerbense.R;
import com.imjx.nanerbense.adapter.ContainerViewpagerAdapter;
import com.imjx.nanerbense.fragment.BackHandledFragment;
import com.imjx.nanerbense.fragment.FindFragment;
import com.imjx.nanerbense.fragment.MarketFragment;
import com.imjx.nanerbense.fragment.MoreFragment;
import com.imjx.nanerbense.fragment.SecrectFragment;
import com.imjx.nanerbense.fragment.WelfareFragment;
import com.imjx.nanerbense.interfaces.BackHandledListener;
import com.imjx.nanerbense.view.CustomTabbarView;
import com.imjx.nanerbense.view.CustomTabbarView.CustomTabbarViewLinstener;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends FragmentActivity implements
		CustomTabbarViewLinstener, BackHandledListener {

	private DrawerLayout mDrawerLayout;
	private ViewPager mViewPager;
	private ArrayList<Fragment> fragmentList;
	private CustomTabbarView tabbarView;
	private static boolean openFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initEvents();

	}

	public void OpenRightMenu(View view) {
		mDrawerLayout.openDrawer(Gravity.RIGHT);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
				Gravity.RIGHT);
	

	}

	public void Openleft(View view) {
		startActivity(new Intent(MainActivity.this, SecondActivity.class));
		

	}

	private void initEvents() {
		mDrawerLayout.setDrawerListener(new DrawerListener() {
			@Override
			public void onDrawerStateChanged(int newState) {
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals("LEFT")) {

					float leftScale = 1 - 0.3f * scale;

					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,
							mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else {
					ViewHelper.setTranslationX(mContent,
							-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			@Override
			public void onDrawerOpened(View drawerView) {
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				mDrawerLayout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
			}
		});
	}

	private void initView() {
		fragmentList = new ArrayList<Fragment>();
		tabbarView = (CustomTabbarView) findViewById(R.id.custom_bar);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mViewPager = (ViewPager) findViewById(R.id.vp_tab_category);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);
		initFragmentPage();
		tabbarView.setCustomTabbarViewLinstener(this);
		mViewPager.setOnPageChangeListener(mPageChangeListener);
	}

	/**
	 * handler线程启动加载fragment ,避免anr
	 */
	private void initFragmentPage() {

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				FindFragment findFragment = new FindFragment();
				SecrectFragment secrectFragment = new SecrectFragment();
				WelfareFragment welfareFragment = new WelfareFragment();
				MarketFragment marketFragment = new MarketFragment();
				MoreFragment moreFragment = new MoreFragment();

				fragmentList.add(findFragment);
				fragmentList.add(secrectFragment);
				fragmentList.add(welfareFragment);
				fragmentList.add(marketFragment);
				fragmentList.add(moreFragment);

				ContainerViewpagerAdapter adapter = new ContainerViewpagerAdapter(
						getSupportFragmentManager(), fragmentList);
				mViewPager.setOffscreenPageLimit(4);
				mViewPager.setAdapter(adapter);
				mViewPager.setCurrentItem(0);
			}

		}, 10);

	}

	/**
	 * 选项页面改变时的监听
	 */

	private OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			tabbarView.setSelectIndex(position);

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	/**
	 * 自定义tabbar的动作回调
	 */
	@Override
	public void onTabbarSelectWithIndex(int index) {
		if (!openFlag) {
			mViewPager.setCurrentItem(index);
			if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
				for (int i = 0; i < getSupportFragmentManager()
						.getBackStackEntryCount(); i++) {
					getSupportFragmentManager().popBackStack();
					
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imjx.nanrenbang.BackHandledListener#setSelectedFragment(com.imjx.
	 * nanrenbang.BackHandledFragment)
	 */
	private BackHandledFragment mBackHandedFragment;

	@Override
	public void setSelectedFragment(BackHandledFragment selectedFragment) {
		this.mBackHandedFragment = selectedFragment;

	}
	/**
	 * 物理返回键统一控制回调
	 */
	@Override
	public void onBackPressed() {

		if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
			if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
				super.onBackPressed();
			} else {
				getSupportFragmentManager().popBackStack();
			}
		}
	}
}
