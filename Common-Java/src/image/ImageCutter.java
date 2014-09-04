package image;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;

/**
 * 图像剪裁类
 * 
 * @author dmj
 * @since 2010/10/21
 */
public class ImageCutter {
	private Image image;// 裁剪的图片

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
	 * 裁剪图片(x,y)开始的矩形
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
	 * 剪裁xNum*yNum块
	 * 
	 * @param xNum
	 *            :列
	 * @param yNum
	 *            :行
	 * @return
	 */
	public Image[] cut(int xNum, int yNum) {
		ImageIcon temp = new ImageIcon(getImage());
		int width = temp.getIconWidth();
		int height = temp.getIconHeight();
		int cW = width / xNum;// 每块宽度
		int cH = height / yNum;// 每块高度
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
