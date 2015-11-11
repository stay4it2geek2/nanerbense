/**
 * 
 */
package com.imjx.nanerbense.interfaces;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.imjx.nanerbense.utils.ImageFileCacheUtils;

/** @defined 
 * @author linwm 
 * @version 1.0  
 */

public class ImageFileCache implements ImageCache{
	 
	  @Override
	  public Bitmap getBitmap(String url) {
	    return ImageFileCacheUtils.getInstance().getImage(url);
	  }
	 
	  @Override
	  public void putBitmap(String url, Bitmap bitmap) {
	    ImageFileCacheUtils.getInstance().saveBitmap(bitmap, url);
	  }
	 
	}


