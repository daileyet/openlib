package web.check.impl;

/**
 * ��ĸ��֤����
 * 
 * @author dmj
 * 
 */
public class LetterValidata extends GenicValidata {
	private final String[] LETTER = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	/**
	 * Ĭ������4λ��ĸ��֤��
	 */
	public LetterValidata() {
	}

	/**
	 * ����ָ�����ȵ���ĸ��֤��
	 * 
	 * @param len
	 */
	public LetterValidata(int len) {
		super(len);
	}

	@Override
	/**
	 * ʵ�ָ����еĳ��󷽷�,�������λ������ĸ��֤��
	 */
	public String create() {
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < getLen(); i++) {
			String c = LETTER[ck.getIndex(0, 26)];
			// c=ck.getIndex(0, 10)>4?c.toLowerCase():c.toUpperCase();
			ret.append(c);
		}
		setLastCheck(ret.toString());
		return ret.toString();
	}

}
