package com.imjx.nanerbense.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast对话框工具类
 * 
 * @author linwm
 */
public class ToastUtil {
	private static Toast mToast;
	private static final int AIRPLAY_TOAST_DISPLAY_TIME = 1000;

	/**
	 * 显示吐司
	 * 
	 * @param text
	 *            显示的文字
	 */
	public static void showToastShort(Context context, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.CENTER, 0, 0);

		}
		mToast.show();
	}

	/**
	 * 隐藏吐司
	 */
	public static void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}

	// AIRPLAY_TOAST_DISPLAY_TIME : Toast显示的时间长度
	// 最后在handler中处理消息
	private static Handler m_Handler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 0:
				break;
			case AIRPLAY_TOAST_DISPLAY_TIME:
				cancelToast();
				break;

			}

		};
	};

	public static void showToast(final FragmentActivity context,
			final String info) {
		if (context != null) {
			context.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// 调用显示函数并向handler发消息
					if (context != null) {
						showToastShort(context, info);
						Message delayMsg = m_Handler.obtainMessage();
						m_Handler.sendMessageDelayed(delayMsg,
								AIRPLAY_TOAST_DISPLAY_TIME);
					}
				}
			});
		}
	}

	/**
	 * * 自定义Toast
	 * 
	 * @param context
	 *            上下文环境
	 * @param message
	 *            显示的消息
	 */

	public static void showToastContext(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}