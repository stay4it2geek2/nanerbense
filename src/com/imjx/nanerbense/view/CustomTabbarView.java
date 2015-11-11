package com.imjx.nanerbense.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.imjx.nanerbense.R;



/**
 * tabbar 控件，作为底部导航
 * 
 * @author linwm
 * 
 */
public class CustomTabbarView extends RelativeLayout implements OnClickListener {

	private CustomTabbarViewLinstener linstener;
	private RadioButton findBtn;
	private RadioButton secertBtn;
	private RadioButton goodBtn;
	private RadioButton taoBtn;
	private RadioButton moreBtn;
	ArrayList<RadioButton> btnList = new ArrayList<RadioButton>();

	public interface CustomTabbarViewLinstener {
		public void onTabbarSelectWithIndex(int index);
	}

	public CustomTabbarView(final Context context) {
		super(context);
		init(context);
	}

	public CustomTabbarView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CustomTabbarView(final Context context, final AttributeSet attrs,
			final int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/**
	 * 设置自定义Tabbar控件监听
	 * 
	 * @param linstener
	 */
	public void setCustomTabbarViewLinstener(CustomTabbarViewLinstener linstener) {
		this.linstener = linstener;
	}

	/**
	 * 根据Tab名称字符串创建自定义Tab
	 * 
	 * @param nameList
	 */
	@SuppressLint("ResourceAsColor")
	public void init(Context context) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_tab_view, this);
		RadioGroup tabgroup = (RadioGroup) view.findViewById(R.id.tabgroup);
		findBtn = (RadioButton) view.findViewById(R.id.findBtn);
		secertBtn = (RadioButton) view.findViewById(R.id.secertBtn);
		goodBtn = (RadioButton) view.findViewById(R.id.goodBtn);
		taoBtn = (RadioButton) view.findViewById(R.id.buyBtn);
		moreBtn = (RadioButton) view.findViewById(R.id.moreBtn);
		btnList.add(findBtn);
		btnList.add(secertBtn);
		btnList.add(goodBtn);
		btnList.add(taoBtn);
		btnList.add(moreBtn);
		findBtn.setText("发现");
		secertBtn.setText("秘密");
		goodBtn.setText("福利");
		taoBtn.setText("淘货");
		moreBtn.setText("更多");
		for (int j = 0; j < btnList.size(); j++) {
			btnList.get(j).setOnClickListener(this);
			btnList.get(j).setGravity(Gravity.CENTER);
			btnList.get(j).setTextSize(18f);

		}

	}

	/**
	 * 根据Index选择对应Tabbar
	 * 
	 * @param index
	 */
	public void setSelectIndex(int index) {
		for (int i = 0; i < btnList.size(); i++) {
			RadioButton btn = btnList.get(i);
			if (i == index) {
				btn.setChecked(true);
				if (linstener != null) {
					linstener.onTabbarSelectWithIndex(btnList.indexOf(btn));
				}
			} else {
				btn.setChecked(false);
			}
		}

	}

	@Override
	public void onClick(View v) {
		int index = btnList.indexOf(v);
		setSelectIndex(index);

	}
}
