package web.check.impl;

/**
 * 数字验证码类
 * 
 * @author dmj
 * 
 */
public class NumberValidata extends GenicValidata {

	/**
	 * 默认生成4位数字验证码
	 */
	public NumberValidata() {
	}

	/**
	 * 生成指定长度的数字验证码
	 * 
	 * @param len
	 */
	public NumberValidata(int len) {
		super(len);
	}

	@Override
	/**
	 * 实现父类中的抽象方法,生成随机位数的数字验证串
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
