package ui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class CutterImage {
	private ImageIcon cutterImage;
	private Integer partIndex;

	public CutterImage() {
	}

	public CutterImage(Image img, Integer index) {
		cutterImage = new ImageIcon(img);
		partIndex = index;
	}

	public int getWidth() {
		return cutterImage.getIconWidth();
	}

	public int getHeight() {
		return cutterImage.getIconHeight();
	}

	public Image getCutterImage() {
		return cutterImage.getImage();
	}

	public void setCutterImage(Image cutterImage) {
		this.cutterImage = new ImageIcon(cutterImage);
	}

	public Integer getPartIndex() {
		return partIndex;
	}

	public void setPartIndex(Integer partIndex) {
		this.partIndex = partIndex;
	}
}