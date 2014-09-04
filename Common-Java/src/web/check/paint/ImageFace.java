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
 * ��֤ͼƬ��
 * 
 * @author dmj
 */
public class ImageFace {
	private CkUtil ck = new CkUtil();
	private int width;// ͼƬ���
	private int height;// ͼƬ�߶�
	private Color bgColor = new Color(253, 254, 225);// ͼƬ������ɫ

	private Color fgColor = Color.decode("#000000");// ͼƬǰ��ɫ
	private boolean manuColor = false;// �Ƿ��ֶ����ù�ǰ����ɫ
	private boolean autoFgColor = true;// �Ƿ��Զ��趨ÿ���ַ���ɫ
	private boolean autoFontSize = true;// �Ƿ��Զ��趨ÿ���ַ���С
	private int fontSize = 10;// �ַ�Ĭ�ϴ�С

	/**
	 * Ĭ���Ը�50��100����ͼƬ
	 */
	public ImageFace() {
		this.setHeight(100);
		this.setWidth(50);
	}

	/**
	 * �Ը����Ŀ���趨��֤ͼƬ�Ĵ�С
	 * 
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 */
	public ImageFace(int width, int height) {
		this.setHeight(height);
		this.setWidth(width);
	}

	/**
	 * ������֤�ӿ�������Ӧ���͵���֤ͼƬ
	 * 
	 * @param val
	 *            ��֤�ӿ�
	 * @return Image���͵�ͼƬ
	 */
	public Image createImage(Validata val) {
		// ��ȡ���ɵ���֤�ַ���
		String word = val.create();
		// ����ͼƬ����
		BufferedImage buff = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// ��ȡͼƬ����
		Graphics g = buff.getGraphics();
		// ����ɫRGB
		int[] R = { bgColor.getRed() };
		int[] G = { bgColor.getGreen() };
		int[] B = { bgColor.getBlue() };
		g.setColor(new Color(R[0], G[0], B[0]));
		g.fillRect(0, 0, width, height);// �ñ���ɫ���ͼƬ

		// ��ȡ��ǰ������Ϣ
		Font font = g.getFont();
		String name = font.getName();
		int style = font.getStyle();
		int size = font.getSize();

		// ÿ���ַ�������
		Font[] fs = new Font[word.length()];
		// ���Ƶ��ַ�������ܺ�,�ַ��������߶�
		int fw = 0, fh = 0;

		for (int i = 0; i < word.length(); i++) {
			// �������ÿ���ַ��Ĵ�С
			int s = size + getFontSize()
					+ (isAutoFontSize() ? ck.getIndex(0, 10) : 0);
			fs[i] = new Font(name, style, s);
			g.setFont(fs[i]);
			// �ۼ�ÿ�����������ַ����
			fw += g.getFontMetrics().charWidth(word.charAt(i));
			if (g.getFontMetrics().getHeight() > fh)
				fh = g.getFontMetrics().getHeight();
		}

		for (int i = 0; i < word.length(); i++) {
			g.setFont(fs[i]);
			// �������ÿ���ַ�����ɫ,�ų�����ɫ
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
	 * ����ָ����ͼƬ��ʽ��ͼƬimage����������out��
	 * 
	 * @param image
	 *            ��Ҫ�����ͼƬ
	 * @param imagetype
	 *            ָ���������
	 * @param out
	 *            �����
	 * @throws IOException
	 *             ��������쳣
	 */
	public void store(Image image, String imagetype, OutputStream out)
			throws IOException {
		ImageIO.write((RenderedImage) image, imagetype, out);
	}

	/**
	 * ��ͼƬimage����JPEG��ʽ����������out��
	 * 
	 * @param image
	 *            ��Ҫ�����ͼƬ
	 * @param out
	 *            �����
	 * @throws IOException
	 *             ��������쳣
	 */
	public void store(Image image, OutputStream out) throws IOException {
		store(image, "JPEG", out);
	}

	/**
	 * ������֤�ӿ�����ͼƬ������JPEG��ʽ����������out��
	 * 
	 * @param val
	 *            ��֤�ӿ�
	 * @param out
	 *            �����
	 * @throws IOException
	 *             ��������쳣
	 */
	public void store(Validata val, OutputStream out) throws IOException {
		store(createImage(val), "JPEG", out);
	}

	/**
	 * ��ȡͼƬ���
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * ����ͼƬ���
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * ��ȡͼƬ�߶�
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * ����ͼƬ�߶�
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * ����ͼƬ����ɫRGBɫֵ
	 * 
	 * @param r
	 *            ��ɫ 0~255
	 * @param g
	 *            ��ɫ 0~255
	 * @param b
	 *            ��ɫ 0~255
	 */
	public void setBgColor(int r, int g, int b) {
		this.bgColor = new Color(r, g, b);
	}

	/**
	 * ��ʮ�����Ƶķ�ʽ���ñ�����ɫ
	 * 
	 * @param hex
	 *            ��:#ffffff
	 */
	public void setBgColor(String hex) {
		if (hex.indexOf("#") != -1)
			this.bgColor = Color.decode(hex);
	}

	/**
	 * ����ͼƬǰ��ɫRGBɫֵ һ���趨ÿ����֤����ɫ��һ��,�����ɫ�趨����Ч
	 * 
	 * @param r
	 *            ��ɫ 0~255
	 * @param g
	 *            ��ɫ 0~255
	 * @param b
	 *            ��ɫ 0~255
	 */
	public void setFgColor(int r, int g, int b) {
		this.fgColor = new Color(r, g, b);
		manuColor = true;
		autoFgColor = false;
	}

	/**
	 * ��ʮ�����Ƶķ�ʽ����ǰ����ɫ һ���趨�ɹ���ÿ����֤����ɫ��һ��,�����ɫ�趨����Ч
	 * 
	 * @param hex
	 *            ��:#ffffff
	 */
	public void setFgColor(String hex) {
		if (hex.indexOf("#") != -1) {
			this.fgColor = Color.decode(hex);
			manuColor = true;
			autoFgColor = false;
		}
	}

	/**
	 * ǰ��ɫ�Ƿ�����ı� Ĭ��Ϊ����ı�
	 * 
	 * @return true/false
	 */
	public boolean isAutoFgColor() {
		return autoFgColor;
	}

	/**
	 * �趨ǰ��ɫ�Ƿ�����ı�
	 * 
	 * @param autoFgColor
	 */
	public void setAutoFgColor(boolean autoFgColor) {
		if (manuColor == false)
			this.autoFgColor = autoFgColor;
	}

	/**
	 * ��ȡ�����С
	 * 
	 * @return int
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * �趨�����С
	 * 
	 * @param fontSize
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * ��֤�������Ƿ�����ı� Ĭ������仯
	 * 
	 * @param autoFontSize
	 */
	public boolean isAutoFontSize() {
		return autoFontSize;
	}

	/**
	 * �趨�����Ƿ�����ı�
	 * 
	 * @param autoFontSize
	 */
	public void setAutoFontSize(boolean autoFontSize) {
		this.autoFontSize = autoFontSize;
	}

}
