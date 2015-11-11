/**
 * 
 */
package com.imjx.nanerbense.model;

import cn.bmob.v3.datatype.BmobFile;

/**
 * @defined 发现列表内容实体
 * @author linwm
 * @version 1.0
 */

public class FindList {

	private String findAbstractContent;//发现的条目内容摘要
	private BmobFile findItemIMG;//发现的条目内容图片
	private String findItemTitle;//发现的条目内容标题

	public String getFindAbstractContent() {
		return findAbstractContent;
	}

	public void setFindAbstractContent(String findAbstractContent) {
		this.findAbstractContent = findAbstractContent;
	}

	public BmobFile getFindItemIMG() {
		return findItemIMG;
	}

	public void setFindItemIMG(BmobFile findItemIMG) {
		this.findItemIMG = findItemIMG;
	}

	
}
