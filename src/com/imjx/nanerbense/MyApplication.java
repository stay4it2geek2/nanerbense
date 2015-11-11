package com.imjx.nanerbense;

import cn.bmob.v3.Bmob;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.imjx.nanerbense.activity.CachedActivity;
import com.imjx.nanerbense.activity.CachingActivity;
import com.imjx.nanerbense.interfaces.ImageFileCache;
import com.youku.player.YoukuPlayerBaseConfiguration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;

/**
 * 接入时自定义的Application需要继承YoukuPlayerBaseConfiguration
 * 
 */
public class MyApplication extends Application {

	public static YoukuPlayerBaseConfiguration configuration;
	private String Bmob_AppId = "e9c3fbb4483793f28e828f97b58c98f0";
	 
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Bmob.initialize(this, Bmob_AppId);
		configuration = new YoukuPlayerBaseConfiguration(this) {

			/**
			 * 通过覆写该方法，返回“正在缓存视频信息的界面”， 则在状态栏点击下载信息时可以自动跳转到所设定的界面. 用户需要定义自己的缓存界面
			 */
			@Override
			public Class<? extends Activity> getCachingActivityClass() {
				// TODO Auto-generated method stub
				return CachingActivity.class;
			}

			/**
			 * 通过覆写该方法，返回“已经缓存视频信息的界面”， 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
			 * 用户需要定义自己的已缓存界面
			 */

			@Override
			public Class<? extends Activity> getCachedActivityClass() {
				// TODO Auto-generated method stub
				return CachedActivity.class;
			}

			/**
			 * 配置视频的缓存路径，格式举例： /appname/videocache/ 如果返回空，则视频默认缓存路径为：
			 * /应用程序包名/videocache/
			 * 
			 */
			@Override
			public String configDownloadPath() {
				// TODO Auto-generated method stub

//				return Environment.getExternalStorageDirectory()+""; // 举例
				 return null;
			}
		};
	}

}
