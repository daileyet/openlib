/**
 * ��webҳ�������صİ�
 */
package web.page;

/**
 * ҳ���ҳ������
 * 
 * @author dmj
 * @version 2010/11/16
 */
public class Page {
	private int currentPage;// ��ǰҳ��
	private int numOfPage;// ҳ����ʾ�ļ�¼��
	private int totalNum;// ��ʾ�ļ�¼����

	private int pageNum;// ��ҳ��
	private int nextPage;// ��һҳҳ��
	private int prePage;// ��һҳҳ��

	/**
	 * һЩ����ҳ��Ԥ���崦��ѡ��
	 */
	private String endPage;// ���ҳ�ĸ���״̬
	private String firstPage;// ��һҳ�ĸ���״̬
	private String nonPage;// û���κ�ҳ�ĸ���״̬

	private static Page p = null;

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param currentPage
	 *            ��ǰҳ��
	 * @param numOfPage
	 *            һҳ��ʾ�ļ�¼��
	 * @param totalNum
	 *            ��ʾ�ļ�¼����
	 * @return Page Page�ĵ�������
	 */
	public static Page getPage(int currentPage, int numOfPage, int totalNum) {
		if (p == null)
			p = new Page(currentPage, numOfPage, totalNum);
		// �����趨��ҳ����
		p.setNumOfPage(numOfPage);
		p.setTotalNum(totalNum);
		p.setCurrentPage(currentPage);
		return p;
	}

	private Page() {
		currentPage = 1;
		numOfPage = 10;
	}

	private Page(int currentPage, int numOfPage, int totalNum) {
		setNumOfPage(numOfPage);
		setTotalNum(totalNum);
		setCurrentPage(currentPage);
	}

	/**
	 * ��ȡ��ǰҳ��
	 * 
	 * @return int ��ǰҳ��
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * ���õ�ǰҳ��
	 * 
	 * @param currentPage
	 *            ��ǰҳ��
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0) {
			this.currentPage = 1;
			return;
		}
		;
		if (pageNum > 0 && currentPage > pageNum) {
			this.currentPage = pageNum;
		} else
			this.currentPage = currentPage;
	}

	/**
	 * ��ȡÿҳ��ʾ��¼��
	 * 
	 * @return int ÿҳ��ʾ��¼��
	 */
	public int getNumOfPage() {
		return numOfPage;
	}

	/**
	 * ����ÿҳ��ʾ�ļ�¼��
	 * 
	 * @param numOfPage
	 *            ÿҳ��ʾ�ļ�¼��
	 */
	public void setNumOfPage(int numOfPage) {
		if (numOfPage <= 0)
			this.numOfPage = 1;
		else
			this.numOfPage = numOfPage;
		getPageNum();
	}

	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @return int �ܼ�¼��
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * �����ܼ�¼��
	 * 
	 * @param totalNum
	 *            �ܼ�¼��
	 */
	public void setTotalNum(int totalNum) {
		if (totalNum < 0)
			this.totalNum = 0;
		else
			this.totalNum = totalNum;
		getPageNum();
	}

	/**
	 * ������һҳҳ��
	 * 
	 * @return int ��һҳҳ��
	 */
	public int getNextPage() {
		nextPage = (currentPage >= getPageNum()) ? currentPage
				: currentPage + 1;
		return nextPage;
	}

	/**
	 * ���㲢�����ܵķ�ҳ��
	 * 
	 * @return int �ܵķ�ҳ��
	 */
	public int getPageNum() {
		pageNum = (totalNum / numOfPage + ((totalNum % numOfPage) == 0 ? 0 : 1));
		return pageNum;
	}

	/**
	 * ����ǰһҳҳ��
	 * 
	 * @return int ǰһҳҳ��
	 */
	public int getPrePage() {
		prePage = (currentPage <= 1) ? currentPage : currentPage - 1;
		return prePage;
	}

	/**
	 * �ж��Ƿ�����һҳ
	 * 
	 * @return
	 */
	public boolean isHasNext() {
		return getNextPage() > getCurrentPage();
	}

	/**
	 * �ж��Ƿ�����һҳ
	 * 
	 * @return
	 */
	public boolean isHasPrevious() {
		return getPrePage() < getCurrentPage();
	}

	/**
	 * ��ȡ��ǰ�ĵ�һ����¼������
	 * 
	 * @return int ��ǰ�ĵ�һ����¼������
	 */
	public int getCurrentBegin() {
		return (getCurrentPage() - 1) * getNumOfPage();
	}

	/**
	 * ��ȡ��ǰҳ���һ����¼����
	 * 
	 * @return int ��ǰҳ���һ����¼����
	 */
	public int getCurrentEnd() {
		int endIndex = getCurrentBegin() + getNumOfPage();
		return endIndex >= getTotalNum() ? getTotalNum() : endIndex;
	}

	/**
	 * ��ȡ��Ϊ���ҳʱ����״̬
	 * 
	 * @return String
	 */
	public String getEndPage() {
		return this.isHasNext() ? "" : endPage;
	}

	/**
	 * ���õ�Ϊ���ҳʱ����״̬
	 * 
	 * @param endPage
	 */
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}

	/**
	 * ��ȡ��Ϊ��һҳʱ����״̬
	 * 
	 * @return String
	 */
	public String getFirstPage() {
		return this.isHasPrevious() ? "" : firstPage;
	}

	/**
	 * ���õ�Ϊ��һҳʱ����״̬
	 * 
	 * @param firstPage
	 */
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	/**
	 * ��ȡû���κ�ҳ�ĸ���״̬
	 * 
	 * @return String
	 */
	public String getNonPage() {
		return this.getPageNum() <= 0 ? nonPage : "";
	}

	/**
	 * ����û���κ�ҳ�ĸ���״̬
	 * 
	 * @param nonPage
	 */
	public void setNonPage(String nonPage) {
		this.nonPage = nonPage;
	}

}
