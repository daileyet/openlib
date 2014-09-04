/**
 * 与web页面设计相关的包
 */
package web.page;

/**
 * 页面分页工具类
 * 
 * @author dmj
 * @version 2010/11/16
 */
public class Page {
	private int currentPage;// 当前页数
	private int numOfPage;// 页面显示的记录数
	private int totalNum;// 显示的记录总数

	private int pageNum;// 总页数
	private int nextPage;// 下一页页码
	private int prePage;// 上一页页码

	/**
	 * 一些特殊页的预定义处理选项
	 */
	private String endPage;// 最后页的附加状态
	private String firstPage;// 第一页的附加状态
	private String nonPage;// 没有任何页的附加状态

	private static Page p = null;

	/**
	 * 获取分页对象
	 * 
	 * @param currentPage
	 *            当前页数
	 * @param numOfPage
	 *            一页显示的记录数
	 * @param totalNum
	 *            显示的记录总数
	 * @return Page Page的单例对象
	 */
	public static Page getPage(int currentPage, int numOfPage, int totalNum) {
		if (p == null)
			p = new Page(currentPage, numOfPage, totalNum);
		// 重新设定分页属性
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
	 * 获取当前页数
	 * 
	 * @return int 当前页码
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页数
	 * 
	 * @param currentPage
	 *            当前页码
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
	 * 获取每页显示记录数
	 * 
	 * @return int 每页显示记录数
	 */
	public int getNumOfPage() {
		return numOfPage;
	}

	/**
	 * 设置每页显示的记录数
	 * 
	 * @param numOfPage
	 *            每页显示的记录数
	 */
	public void setNumOfPage(int numOfPage) {
		if (numOfPage <= 0)
			this.numOfPage = 1;
		else
			this.numOfPage = numOfPage;
		getPageNum();
	}

	/**
	 * 获取总记录数
	 * 
	 * @return int 总记录数
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param totalNum
	 *            总记录数
	 */
	public void setTotalNum(int totalNum) {
		if (totalNum < 0)
			this.totalNum = 0;
		else
			this.totalNum = totalNum;
		getPageNum();
	}

	/**
	 * 返回下一页页数
	 * 
	 * @return int 下一页页码
	 */
	public int getNextPage() {
		nextPage = (currentPage >= getPageNum()) ? currentPage
				: currentPage + 1;
		return nextPage;
	}

	/**
	 * 计算并返回总的分页数
	 * 
	 * @return int 总的分页数
	 */
	public int getPageNum() {
		pageNum = (totalNum / numOfPage + ((totalNum % numOfPage) == 0 ? 0 : 1));
		return pageNum;
	}

	/**
	 * 返回前一页页数
	 * 
	 * @return int 前一页页数
	 */
	public int getPrePage() {
		prePage = (currentPage <= 1) ? currentPage : currentPage - 1;
		return prePage;
	}

	/**
	 * 判断是否有下一页
	 * 
	 * @return
	 */
	public boolean isHasNext() {
		return getNextPage() > getCurrentPage();
	}

	/**
	 * 判断是否有上一页
	 * 
	 * @return
	 */
	public boolean isHasPrevious() {
		return getPrePage() < getCurrentPage();
	}

	/**
	 * 获取当前的第一条记录的索引
	 * 
	 * @return int 当前的第一条记录的索引
	 */
	public int getCurrentBegin() {
		return (getCurrentPage() - 1) * getNumOfPage();
	}

	/**
	 * 获取当前页最后一条记录索引
	 * 
	 * @return int 当前页最后一条记录索引
	 */
	public int getCurrentEnd() {
		int endIndex = getCurrentBegin() + getNumOfPage();
		return endIndex >= getTotalNum() ? getTotalNum() : endIndex;
	}

	/**
	 * 获取当为最后页时附加状态
	 * 
	 * @return String
	 */
	public String getEndPage() {
		return this.isHasNext() ? "" : endPage;
	}

	/**
	 * 设置当为最后页时附加状态
	 * 
	 * @param endPage
	 */
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}

	/**
	 * 获取当为第一页时附加状态
	 * 
	 * @return String
	 */
	public String getFirstPage() {
		return this.isHasPrevious() ? "" : firstPage;
	}

	/**
	 * 设置当为第一页时附加状态
	 * 
	 * @param firstPage
	 */
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	/**
	 * 获取没有任何页的附加状态
	 * 
	 * @return String
	 */
	public String getNonPage() {
		return this.getPageNum() <= 0 ? nonPage : "";
	}

	/**
	 * 设置没有任何页的附加状态
	 * 
	 * @param nonPage
	 */
	public void setNonPage(String nonPage) {
		this.nonPage = nonPage;
	}

}
