package com.cos.util;

public class Code {
	public static int maxListNum = 0;
	
	public synchronized static int getMaxListNum() {
		return maxListNum;
	}
	public synchronized static void setMaxListNum(int maxListNum) {
		Code.maxListNum= maxListNum;
	}
}
