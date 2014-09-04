package web.check.resource;

import java.io.InputStream;

/**
 * 资源文件工具类
 * 
 * @author dmj
 * 
 */
public class ResourceUtil {
	/**
	 * 返回相应路径文件的输入流
	 * 
	 * @param path
	 *            相应文件路径
	 * @return InputStream 输入流
	 */
	public static InputStream getResource(String path) {
		return ResourceUtil.class.getResourceAsStream(path);
	}
}
