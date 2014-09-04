/**
 * ��֤��ӿ�ʵ�ְ�
 */
package web.check.impl;

import web.check.Validata;
import web.check.util.CkUtil;

/**
 * Ĭ����֤��ʵ����
 * 
 * @author dmj
 * @version 2010/06/24
 */
public abstract class GenicValidata implements Validata {
	/**
	 * �������ַ�����
	 */
	private int len;
	/**
	 * ��֤������ֵ
	 */
	private String lastCheck = null;
	protected CkUtil ck = null;

	/**
	 * Ĭ�Ϲ����������4λ��֤���ַ���
	 */
	public GenicValidata() {
		this(4);
	}

	/**
	 * �������ָ��λ��֤���ַ���
	 * 
	 * @param len
	 */
	public GenicValidata(int len) {
		ck = new CkUtil();
		setLen(len);
	}

	@Override
	/**
	 * ��ȡ�ϴ������ɵ���֤�ַ���
	 */
	public String getLastCheck() {
		return lastCheck;
	}

	/**
	 * �����ϴ������ɵ���֤�ַ���
	 * 
	 * @param lastCheck
	 */
	void setLastCheck(String lastCheck) {
		this.lastCheck = lastCheck;
	}

	@Override
	public abstract String create();

	/**
	 * �������ɵ��ַ�����
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
	 * ��ȡ������֤�ַ����ĳ���
	 * 
	 * @return
	 */
	public int getLen() {
		return len;
	}
}
