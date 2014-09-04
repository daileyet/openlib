/**
 * 验证码接口实现包
 */
package web.check.impl;

import web.check.Validata;
import web.check.util.CkUtil;

/**
 * 默认验证码实现类
 * 
 * @author dmj
 * @version 2010/06/24
 */
public abstract class GenicValidata implements Validata {
	/**
	 * 产生的字符长度
	 */
	private int len;
	/**
	 * 验证码的最近值
	 */
	private String lastCheck = null;
	protected CkUtil ck = null;

	/**
	 * 默认构造随机生成4位验证码字符串
	 */
	public GenicValidata() {
		this(4);
	}

	/**
	 * 随机生成指定位验证码字符串
	 * 
	 * @param len
	 */
	public GenicValidata(int len) {
		ck = new CkUtil();
		setLen(len);
	}

	@Override
	/**
	 * 获取上次所生成的验证字符串
	 */
	public String getLastCheck() {
		return lastCheck;
	}

	/**
	 * 设置上次所生成的验证字符串
	 * 
	 * @param lastCheck
	 */
	void setLastCheck(String lastCheck) {
		this.lastCheck = lastCheck;
	}

	@Override
	public abstract String create();

	/**
	 * 设置生成的字符长度
	 * 
	 * @param len
	 */
	public void setLen(int len) {
		if (len < 1)
			this.len = 4;
		else
			this.len = len;
	}

	/**
	 * 获取生成验证字符串的长度
	 * 
	 * @return
	 */
	public int getLen() {
		return len;
	}
}
