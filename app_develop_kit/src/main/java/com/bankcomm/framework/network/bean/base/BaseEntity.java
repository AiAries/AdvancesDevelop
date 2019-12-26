package com.bankcomm.framework.network.bean.base;

import java.util.List;

/**
 * Created by  on 2018/6/1.
 */

public class BaseEntity<T> {
	private String responseCode;
	private List<T> data;
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
}
