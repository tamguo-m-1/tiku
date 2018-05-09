package com.tamguo.util;

/**
 * Setting - 系统
 * 
 * @author candy.tam
 *
 */
public final class Setting {

	private static Setting instance = null; 
	
	public static Setting getSetting(){
		if(instance == null){
			instance = new Setting();
		}
		return instance;
	}
	
	/** 域名 */
	public final String domain = "http://www.tamguo.com/";
	/** 真题 */
	public final String PAPER_TYPE_ZHENTI = "1";
	/** 模拟*/
	public final String PAPER_TYPE_MONI = "2";
	/** 押题*/
	public final String PAPER_TYPE_YATI = "3";
	/** 名校 */
	public final String PAPER_TYPE_MINGXIAO = "4";
	
}
