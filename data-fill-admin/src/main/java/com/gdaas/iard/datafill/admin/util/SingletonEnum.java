package com.gdaas.iard.datafill.admin.util;

import java.text.SimpleDateFormat;

public enum SingletonEnum {

	SIMPLEDATE,REVIEW("0"),DELETE("-1");

	private SimpleDateFormat simpleDate;
	private String value;

	SingletonEnum(){
		simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	SingletonEnum(String arg1){
		this.value = arg1;
	}

	public SimpleDateFormat getSimpleDate(){
		return simpleDate;
	}
	public String getValue(){
		return value;
	}
}
