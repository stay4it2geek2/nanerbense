/**
 * 
 */
package com.imjx.nanerbense.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.imjx.nanerbense.R;
import com.imjx.nanerbense.adapter.FindListAdapter;
import com.imjx.nanerbense.model.FindList;
import com.imjx.nanerbense.utils.ToastUtil;
import com.imjx.nanerbense.view.RefreshLayoutView;
import com.imjx.nanerbense.view.RefreshLayoutView.OnLoadListener;

/** @defined 
 * @author linwm 
 * @version 1.0  
 */
/**
 * @author imjx
 * 
 */
public class FindFragment extends BackHandledFragment implements
		OnRefreshListener, OnLoadListener {
	View view;
	private RefreshLayoutView swipeLayout;
	private ListView listView;
	private ArrayList<HashMap<String, String>> list;
	private View bannerView_header;
	private ListView autoListView_findFrgment;
	private FindListAdapter adapter;
	private static boolean canLoadFlag=true;
	private static String loadFlag;
	private static List<FindList> findLists;

	@Override
	public boolean onBackPressed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_find, null);
		autoListView_findFrgment = (ListView) view
				.findViewById(R.id.lv_findfragment);
		swipeLayout = (RefreshLayoutView) view.findViewById(R.id.swipe_container);
		swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		setListener();
		findLists = new ArrayList<FindList>();
		new Handler().post(new Runnable() {

			@Override
			public void run() {
				loadData();
			}
		});
		return view;
	}

	private void loadData() {

		BmobQuery<FindList> query = new BmobQuery<FindList>();
		query.order("createdAt");//
		query.setLimit(4);
		query.findObjects(getActivity(), new FindListener<FindList>() {

			@Override
			public void onSuccess(List<FindList> findList) {
				findLists.addAll(findList);
				adapter = new FindListAdapter(findLists, getActivity());
				autoListView_findFrgment.setAdapter(adapter);

			}

			@Override
			public void onError(String arg0) {

			}
		});

	}

	static int loadMoreCount = 0;

	private void loadOrRefreshData() {
		if (canLoadFlag) {

			int pageNum = 4;
			BmobQuery<FindList> query = new BmobQuery<FindList>();
			query.setLimit(pageNum);
			if (loadMoreCount >= 1) {
				pageNum = pageNum * loadMoreCount;
				query.setSkip(pageNum);
			}
			query.order("createdAt");//
			query.findObjects(getActivity(), new FindListener<FindList>() {

				@Override
				public void onSuccess(final List<FindList> findList) {
					if ("load".equals(loadFlag)) {
						findLists.addAll(findList);
					} else if ("refresh".equals(loadFlag)) {
						findLists.addAll(0, findList);

					} else {
						findLists.addAll(findList);
					}
					new Handler().post(new Runnable() {

						@Override
						public void run() {
							if (findList.size() == 0) {
								ToastUtil.showToast(getActivity(), "第"
										+ (loadMoreCount + 1) + "页没有数据!");
								canLoadFlag = false;
							}
							adapter.notifyDataSetChanged();

						}
					});
				}

				@Override
				public void onError(String arg0) {

				}
			});

		}else {
			ToastUtil.showToast(getActivity(), "没有更多数据，您就不用再刷新了哦！");
		}
	}

	private void setListener() {
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setOnLoadListener(this);

	}

	@Override
	public void onLoad() {
		loadFlag = "load";
		loadMoreCount = loadMoreCount + 1;
		swipeLayout.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 更新数据
				loadOrRefreshData();
				// 更新完后调用该方法结束刷新
				swipeLayout.setLoading(false);
			}
		}, 1000);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener#onRefresh
	 * ()
	 */
	@Override
	public void onRefresh() {
		loadFlag = "refresh";
		loadMoreCount = loadMoreCount + 1;

		swipeLayout.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 更新数据
				loadOrRefreshData();
				// 更新完后调用该方法结束刷新
				swipeLayout.setRefreshing(false);
			}
		}, 1000);

	}
	
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.e("fdsfdsf", "onDestroy");
	}
}
