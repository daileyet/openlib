package web.check.resource;

import java.io.InputStream;

/**
 * ��Դ�ļ�������
 * 
 * @author dmj
 * 
 */
public class ResourceUtil {
	/**
	 * ������Ӧ·���ļ���������
	 * 
	 * @param path
	 *            ��Ӧ�ļ�·��
	 * @return InputStream ������
	 */
	public static InputStream getResource(String path) {
		return ResourceUtil.class.getResourceAsStream(path);
	}
}
