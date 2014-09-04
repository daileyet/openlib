package web.check.util;

import java.util.Arrays;

/**
 * 验证帮助类
 * 
 * @author dmj
 * 
 */
public class CkUtil {
	private int lastIndex = -1;

	/**
	 * 获取开始至结束位置中的任意一个数
	 * 
	 * @param begin
	 *            开始位置
	 * @param end
	 *            结束位置(不包括)
	 * @return 在该范围类的一个整数
	 */
	public int getIndex(int begin, int end) {
		if (begin < 0 || end < begin || end < 0)
			return -1;
		int rd = -1;
		do {
			rd = (int) (Math.random() * (end - begin)) + begin;
		} while (rd == lastIndex);
		lastIndex = rd;
		return rd;
	}

	/**
	 * 获取开始至结束位置中的任意一个数但不包括数组notin中的值
	 * 
	 * @param begin
	 *            开始位置
	 * @param end
	 *            结束位置(不包括)
	 * @param notin
	 *            剔除的数字
	 * @return 在该范围类的一个整数
	 */
	public int getIndex(int begin, int end, int[] notin) {
		if (begin < 0 || end < begin || end < 0)
			return -1;
		int rd = -1;
		Arrays.sort(notin);
		do {
			rd = (int) (Math.random() * (end - begin)) + begin;
		} while (rd == lastIndex || Arrays.binarySearch(notin, rd) > -1);
		lastIndex = rd;
		return rd;
	}

}
