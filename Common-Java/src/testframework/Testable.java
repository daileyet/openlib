package testframework;

/**
 * 测试方法接口
 * 
 * @author dmj
 * 
 */
public interface Testable {

	/**
	 * 初始化测试方法
	 */
	public void initialMethod();

	/**
	 * 调用测试的方法
	 */
	public void callMethod();

	/**
	 * 清理测试后的方法
	 */
	public void endMethod();
}
