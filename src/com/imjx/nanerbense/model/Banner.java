package com.imjx.nanerbense.model;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


/**
 * @defined 轮播图片数据模型
 * @author linwm
 * @version 1.0
 */
public class Banner extends BmobObject {
	
	private BmobFile imageUrl;
	private String urlToJump;
	
	public BmobFile getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(BmobFile imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUrlToJump() {
		return urlToJump;
	}
	public void setUrlToJump(String urlToJump) {
		this.urlToJump = urlToJump;
	}
	
	

}
