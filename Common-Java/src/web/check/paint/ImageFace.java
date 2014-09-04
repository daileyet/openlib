package web.check.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

import web.check.Validata;
import web.check.util.CkUtil;

/**
 * 验证图片类
 * 
 * @author dmj
 */
public class ImageFace {
	private CkUtil ck = new CkUtil();
	private int width;// 图片宽度
	private int height;// 图片高度
	private Color bgColor = new Color(253, 254, 225);// 图片背景颜色

	private Color fgColor = Color.decode("#000000");// 图片前景色
	private boolean manuColor = false;// 是否手动设置过前景颜色
	private boolean autoFgColor = true;// 是否自动设定每个字符颜色
	private boolean autoFontSize = true;// 是否自动设定每个字符大小
	private int fontSize = 10;// 字符默认大小

	/**
	 * 默认以高50宽100创建图片
	 */
	public ImageFace() {
		this.setHeight(100);
		this.setWidth(50);
	}

	/**
	 * 以给定的宽高设定验证图片的大小
	 * 
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public ImageFace(int width, int height) {
		this.setHeight(height);
		this.setWidth(width);
	}

	/**
	 * 根据验证接口生成相应类型的验证图片
	 * 
	 * @param val
	 *            验证接口
	 * @return Image类型的图片
	 */
	public Image createImage(Validata val) {
		// 获取生成的验证字符串
		String word = val.create();
		// 创建图片缓存
		BufferedImage buff = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图片画笔
		Graphics g = buff.getGraphics();
		// 背景色RGB
		int[] R = { bgColor.getRed() };
		int[] G = { bgColor.getGreen() };
		int[] B = { bgColor.getBlue() };
		g.setColor(new Color(R[0], G[0], B[0]));
		g.fillRect(0, 0, width, height);// 用背景色填充图片

		// 获取当前字体信息
		Font font = g.getFont();
		String name = font.getName();
		int style = font.getStyle();
		int size = font.getSize();

		// 每个字符的字体
		Font[] fs = new Font[word.length()];
		// 绘制的字符串宽度总和,字符串中最大高度
		int fw = 0, fh = 0;

		for (int i = 0; i < word.length(); i++) {
			// 随机设置每个字符的大小
			int s = size + getFontSize()
					+ (isAutoFontSize() ? ck.getIndex(0, 10) : 0);
			fs[i] = new Font(name, style, s);
			g.setFont(fs[i]);
			// 累计每个新字体后的字符宽度
			fw += g.getFontMetrics().charWidth(word.charAt(i));
			if (g.getFontMetrics().getHeight() > fh)
				fh = g.getFontMetrics().getHeight();
		}

		for (int i = 0; i < word.length(); i++) {
			g.setFont(fs[i]);
			// 随机设置每个字符的颜色,排除背景色
			if (autoFgColor)
				g.setColor(new Color(ck.getIndex(0, 256, R), ck.getIndex(0,
						256, G), ck.getIndex(0, 256, B)));
			else
				g.setColor(fgColor);
			g.drawString(word.substring(i, i + 1), (getWidth() - fw) / 2 + i
					* (fw / word.length()), (getHeight() + fh / 2) / 2);
		}
		g.dispose();
		return buff;
	}

	/**
	 * 按照指定的图片格式将图片image输出到输出流out中
	 * 
	 * @param image
	 *            需要输出的图片
	 * @param imagetype
	 *            指定输出类型
	 * @param out
	 *            输出流
	 * @throws IOException
	 *             输入输出异常
	 */
	public void store(Image image, String imagetype, OutputStream out)
			throws IOException {
		ImageIO.write((RenderedImage) image, imagetype, out);
	}

	/**
	 * 将图片image按照JPEG格式输出到输出流out中
	 * 
	 * @param image
	 *            需要输出的图片
	 * @param out
	 *            输出流
	 * @throws IOException
	 *             输入输出异常
	 */
	public void store(Image image, OutputStream out) throws IOException {
		store(image, "JPEG", out);
	}

	/**
	 * 根据验证接口生成图片并按照JPEG格式输出到输出流out中
	 * 
	 * @param val
	 *            验证接口
	 * @param out
	 *            输出流
	 * @throws IOException
	 *             输入输出异常
	 */
	public void store(Validata val, OutputStream out) throws IOException {
		store(createImage(val), "JPEG", out);
	}

	/**
	 * 获取图片宽度
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 设置图片宽度
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 获取图片高度
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 设置图片高度
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 设置图片背景色RGB色值
	 * 
	 * @param r
	 *            红色 0~255
	 * @param g
	 *            绿色 0~255
	 * @param b
	 *            蓝色 0~255
	 */
	public void setBgColor(int r, int g, int b) {
		this.bgColor = new Color(r, g, b);
	}

	/**
	 * 以十六进制的方式设置背景颜色
	 * 
	 * @param hex
	 *            例:#ffffff
	 */
	public void setBgColor(String hex) {
		if (hex.indexOf("#") != -1)
			this.bgColor = Color.decode(hex);
	}

	/**
	 * 设置图片前景色RGB色值 一旦设定每个验证码颜色将一致,随机颜色设定将无效
	 * 
	 * @param r
	 *            红色 0~255
	 * @param g
	 *            绿色 0~255
	 * @param b
	 *            蓝色 0~255
	 */
	public void setFgColor(int r, int g, int b) {
		this.fgColor = new Color(r, g, b);
		manuColor = true;
		autoFgColor = false;
	}

	/**
	 * 以十六进制的方式设置前景颜色 一旦设定成功则每个验证码颜色将一致,随机颜色设定将无效
	 * 
	 * @param hex
	 *            例:#ffffff
	 */
	public void setFgColor(String hex) {
		if (hex.indexOf("#") != -1) {
			this.fgColor = Color.decode(hex);
			manuColor = true;
			autoFgColor = false;
		}
	}

	/**
	 * 前景色是否随机改变 默认为随机改变
	 * 
	 * @return true/false
	 */
	public boolean isAutoFgColor() {
		return autoFgColor;
	}

	/**
	 * 设定前景色是否随机改变
	 * 
	 * @param autoFgColor
	 */
	public void setAutoFgColor(boolean autoFgColor) {
		if (manuColor == false)
			this.autoFgColor = autoFgColor;
	}

	/**
	 * 获取字体大小
	 * 
	 * @return int
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * 设定字体大小
	 * 
	 * @param fontSize
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * 验证码字体是否随机改变 默认随机变化
	 * 
	 * @param autoFontSize
	 */
	public boolean isAutoFontSize() {
		return autoFontSize;
	}

	/**
	 * 设定字体是否随机改变
	 * 
	 * @param autoFontSize
	 */
	public void setAutoFontSize(boolean autoFontSize) {
		this.autoFontSize = autoFontSize;
	}

}
