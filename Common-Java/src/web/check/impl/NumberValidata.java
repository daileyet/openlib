package web.check.impl;

/**
 * ������֤����
 * 
 * @author dmj
 * 
 */
public class NumberValidata extends GenicValidata {

	/**
	 * Ĭ������4λ������֤��
	 */
	public NumberValidata() {
	}

	/**
	 * ����ָ�����ȵ�������֤��
	 * 
	 * @param len
	 */
	public NumberValidata(int len) {
		super(len);
	}

	@Override
	/**
	 * ʵ�ָ����еĳ��󷽷�,�������λ����������֤��
	 */
	public String create() {
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < getLen(); i++) {
			ret.append(ck.getIndex(0, 10));
		}
		setLastCheck(ret.toString());
		return ret.toString();
	}

}
