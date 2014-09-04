package web.check.util;

import java.util.Arrays;

/**
 * ��֤������
 * 
 * @author dmj
 * 
 */
public class CkUtil {
	private int lastIndex = -1;

	/**
	 * ��ȡ��ʼ������λ���е�����һ����
	 * 
	 * @param begin
	 *            ��ʼλ��
	 * @param end
	 *            ����λ��(������)
	 * @return �ڸ÷�Χ���һ������
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
	 * ��ȡ��ʼ������λ���е�����һ����������������notin�е�ֵ
	 * 
	 * @param begin
	 *            ��ʼλ��
	 * @param end
	 *            ����λ��(������)
	 * @param notin
	 *            �޳�������
	 * @return �ڸ÷�Χ���һ������
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
