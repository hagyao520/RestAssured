package com.jmoney.luckeylink.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

    @SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		if ("".equals(str)) {
			return false;
		}else if(null != str){
			return true;
		}
		return false;
	}

	public static boolean isEqual(String str1, String str2) {
		if (str1 == str2 || str1.equals(str2)) {
			return true;
		}
		return false;
	}
}
