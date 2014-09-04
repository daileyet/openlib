package image;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;

/**
 * ͼ�������
 * 
 * @author dmj
 * @since 2010/10/21
 */
public class ImageCutter {
	private Image image;// �ü���ͼƬ

	public ImageCutter() {
	}

	public ImageCutter(Image img) {
		this.image = img;
	}

	public Dimension getSize() {
		ImageIcon temp = new ImageIcon(getImage());
		return new Dimension(temp.getIconWidth(), temp.getIconHeight());
	}

	/**
	 * �ü�ͼƬ(x,y)��ʼ�ľ���
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public Image cut(int x, int y, int width, int height) {
		CropImageFilter cif = new CropImageFilter(x, y, width, height);
		return Toolkit.getDefaultToolkit().createImage(
				new FilteredImageSource(image.getSource(), cif));
	}

	public Image cut(int x, int y, int width, int height, Component com) {
		CropImageFilter cif = new CropImageFilter(x, y, width, height);
		return com.createImage(new FilteredImageSource(image.getSource(), cif));
	}

	/**
	 * ����xNum*yNum��
	 * 
	 * @param xNum
	 *            :��
	 * @param yNum
	 *            :��
	 * @return
	 */
	public Image[] cut(int xNum, int yNum) {
		ImageIcon temp = new ImageIcon(getImage());
		int width = temp.getIconWidth();
		int height = temp.getIconHeight();
		int cW = width / xNum;// ÿ����
		int cH = height / yNum;// ÿ��߶�
		Image[] cuts = new Image[xNum * yNum];
		int index = 0;
		for (int i = 0; i < xNum; i++) {
			for (int j = 0; j < yNum; j++) {
				cuts[index++] = cut(j * cW, i * cH, cW, cH);
			}
		}
		return cuts;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image img) {
		this.image = img;
	}

}
