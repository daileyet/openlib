package web.check.impl;

/**
 * 字母验证码类
 * 
 * @author dmj
 * 
 */
public class LetterValidata extends GenicValidata {
	private final String[] LETTER = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	/**
	 * 默认生成4位字母验证码
	 */
	public LetterValidata() {
	}

	/**
	 * 生成指定长度的字母验证码
	 * 
	 * @param len
	 */
	public LetterValidata(int len) {
		super(len);
	}

	@Override
	/**
	 * 实现父类中的抽象方法,生成随机位数的字母验证串
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
