package web.check.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import web.check.resource.ResourceUtil;

/**
 * 中文验证码类
 * 
 * @author dmj
 * 
 */
public class CharacterValidata extends GenicValidata {

	// 存放汉字的列表
	private static List<String> character = null;

	static {
		try {
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 默认生成4位中文验证码
	 */
	public CharacterValidata() {
		this(4);
	}

	/**
	 * 生成指定长度的中文验证码
	 * 
	 * @param len
	 */
	public CharacterValidata(int len) {
		super(len);
	}

	/**
	 * 加载汉字
	 * 
	 * @throws IOException
	 */
	public static void load() throws IOException {
		InputStream input = ResourceUtil.getResource("character.txt");
		LineNumberReader lr = new LineNumberReader(new InputStreamReader(input));
		character = new ArrayList<String>();
		String line = null;

		while ((line = lr.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				character.add(line.substring(i, i + 1));
			}
		}
		lr.close();
	}

	@Override
	public String create() {
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < getLen(); i++) {
			String c = character.get(ck.getIndex(0, character.size()));
			ret.append(c);
		}
		setLastCheck(ret.toString());
		return ret.toString();
	}

}
