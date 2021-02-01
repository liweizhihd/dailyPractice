package com.prac.demo1.bean;

import lombok.Data;

/**
* @Description: 常用的返回数据包装类 
* @Param:
* @return:  
* @Author: Roderick-zhang 
* @Date: 2019/1/3 
*/
@Data
public class CommonResult {

	private String name;
	private String value;
	private String status;

	public CommonResult(String name, String value, String status) {
		this.name = name;
		this.value = value;
		this.status = status;
	}

	public CommonResult() {
	}
}
