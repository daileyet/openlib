package ui;

import image.ImageCutter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SplitPictureBean extends JPanel {
	private static final long serialVersionUID = 7185827209187072002L;

	private Image srcPic = null;
	private ImageCutter picCutter = null;
	private CutterImage[] cutterPics = null;
	private int rows = 3, cols = 3;
	private int cellspacing = 1;
	private SplitPictureListener spl = null;
	private static final int XPREFSIZE = 800;
	private static final int YPREFSIZE = 600;

	public Dimension getPreferredSize() {
		return new Dimension(XPREFSIZE, YPREFSIZE);

	}

	public int getWidth() {
		return picCutter == null ? XPREFSIZE : (int) (picCutter.getSize()
				.getWidth());
	}

	public int getHeight() {
		return picCutter == null ? YPREFSIZE : (int) (picCutter.getSize()
				.getHeight());
	}

	public void setImageFileName(String imageFileName) {
		try {
			srcPic = ImageIO.read(new File(imageFileName));
			updateSplitPicture();
		} catch (IOException e) {
			srcPic = null;
		}
	}

	public SplitPictureBean() {
		this.addKeyListener(new SplitPictureKeyListener());
		this.addMouseListener(new SplitPictureMouseListener());
		this.setFocusable(true);
		this.addSplitPictureListener(new SplitPictureListener() {
			@Override
			public void completed() {
			}

			@Override
			public void moved() {
			}

			@Override
			public void moving() {
			}
		});
		setBorder(BorderFactory.createEtchedBorder());
	}

	public SplitPictureBean(Image src) {
		this(src, 3, 3);
	}

	public SplitPictureBean(Image src, int cols, int rows) {
		this();
		this.srcPic = src;
		this.setCols(cols);
		this.setRows(rows);
		updateSplitPicture();
	}

	public void updateSplitPicture() {
		picCutter = new ImageCutter(srcPic);
		cutterPics = convertImage(picCutter.cut(getCols(), getRows()));
		setSplitPics(rows * cols);
		showComplete = false;
		repaint();
	}

	public boolean isComplete() {
		boolean complete = true;
		for (int i = 0; i < cols * rows; i++) {
			if (this.getCutterPics()[i] != null
					&& this.getCutterPics()[i].getPartIndex() != i) {
				complete = false;
				break;
			}
		}
		return complete;
	}

	public void cheat() {
		for (int i = 0; i < cols * rows; i++) {
			int index = -1;
			for (int j = 0; j < cols * rows; j++) {
				if (getCutterPics()[j] != null
						&& getCutterPics()[j].getPartIndex() == i)
					index = j;
			}
			if (index != -1 && !isComplete()) {
				move(i, index);
			}
		}
	}

	private CutterImage[] convertImage(Image[] imgs) {
		CutterImage[] cutterImgs = new CutterImage[imgs.length];
		for (int i = 0; i < imgs.length; i++) {
			cutterImgs[i] = new CutterImage(imgs[i], i);
		}
		return cutterImgs;
	}

	private CutterImage[] setSplitPics(int picNum) {
		List<Integer> picLoaded = new ArrayList<Integer>();
		Random rd = new Random();
		CutterImage[] splitPics = new CutterImage[picNum];
		while (picLoaded.size() != picNum) {
			int temp = rd.nextInt(picNum);
			if (!picLoaded.contains(Integer.valueOf(temp))) {
				splitPics[picLoaded.size()] = this.getCutterPics()[temp];
				picLoaded.add(temp);
			}
		}
		splitPics[splitPics.length - 1] = null;
		return this.cutterPics = splitPics;
	}

	protected void paintSplitPicture(Graphics g, CutterImage[] showCutterPics) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < showCutterPics.length; i++) {
			CutterImage pic = showCutterPics[i];
			if (pic == null)
				continue;// ¿ÕµÄÇÐÆ¬Ìø¹ý
			int w = pic.getWidth();
			int h = pic.getHeight();
			g.drawImage(pic.getCutterImage(), i % rows * (w + cellspacing), i
					/ cols * (h + cellspacing), this);
		}
		g.setColor(c);
	}

	protected void paintCompletePicture(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(getSrcPic(), 0, 0, this);
		g.setColor(c);
	}

	public void showComplete() {
		showComplete = true;
		repaint();
	}

	private boolean showComplete = false;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (srcPic != null) {
			Image off = new BufferedImage(this.getWidth(), this.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			Graphics dg = off.getGraphics();// Ë«»º³å»æÖÆ
			if (!showComplete)
				paintSplitPicture(dg, getCutterPics());
			else
				paintCompletePicture(dg);
			g.drawImage(off, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	protected void judgeMove(int currentIndex) {
		if (currentIndex < 0 || currentIndex > cols * rows)
			return;
		int l = currentIndex % cols == 0 ? currentIndex : currentIndex - 1;
		if (l != currentIndex && getCutterPics()[l] == null) {
			left(currentIndex);
			return;
		}
		int r = (currentIndex + 1) % cols == 0 ? currentIndex
				: currentIndex + 1;
		if (r != currentIndex && getCutterPics()[r] == null) {
			right(currentIndex);
			return;
		}
		int t = currentIndex / rows == 0 ? currentIndex : currentIndex - cols;
		if (t != currentIndex && getCutterPics()[t] == null) {
			up(currentIndex);
			return;
		}
		int b = currentIndex / rows == (rows - 1) ? currentIndex : currentIndex
				+ cols;
		if (b != currentIndex && getCutterPics()[b] == null) {
			down(currentIndex);
			return;
		}
	}

	@Override
	public void move(int currentIndex, int nextIndex) {
		CutterImage temp = getCutterPics()[currentIndex];
		getCutterPics()[currentIndex] = getCutterPics()[nextIndex];
		getCutterPics()[nextIndex] = temp;
		spl.moving();
		repaint();
		spl.moved();
		if (isComplete())
			spl.completed();
	}

	public void up(int currentIndex) {
		int nextIndex = currentIndex / rows == 0 ? currentIndex : currentIndex
				- cols;
		move(currentIndex, nextIndex);
	}

	public void down(int currentIndex) {
		int nextIndex = currentIndex / rows == (rows - 1) ? currentIndex
				: currentIndex + cols;
		move(currentIndex, nextIndex);
	}

	public void left(int currentIndex) {
		int nextIndex = currentIndex % cols == 0 ? currentIndex
				: currentIndex - 1;
		move(currentIndex, nextIndex);
	}

	public void right(int currentIndex) {
		int nextIndex = (currentIndex + 1) % cols == 0 ? currentIndex
				: currentIndex + 1;
		move(currentIndex, nextIndex);
	}

	class SplitPictureMouseListener extends MouseAdapter {
		private int getCurrentIndex(Point p) {
			int index = -1;
			Rectangle rect = new Rectangle();
			for (int i = 0; i < getCutterPics().length; i++) {
				if (getCutterPics()[i] == null)
					continue;
				int w = getCutterPics()[i].getWidth();
				int h = getCutterPics()[i].getHeight();
				rect.setBounds(i % rows * (w + cellspacing), i / cols
						* (h + cellspacing), w, h);
				if (rect.contains(p)) {
					index = i;
					break;
				}
			}
			return index;
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				judgeMove(getCurrentIndex(event.getPoint()));
			} else if (event.isAltDown() && event.isControlDown()) {
				cheat();
				repaint();
			}
		}
	}

	class SplitPictureKeyListener implements KeyListener {
		private int getBlankCutterIndex() {
			int index = 0;
			for (int i = 0; i < getCutterPics().length; i++) {
				if (getCutterPics()[i] == null) {
					index = i;
					break;
				}
			}
			return index;
		}

		@Override
		public void keyPressed(KeyEvent event) {
			int code = event.getKeyCode();
			if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN) {
				up(getBlankCutterIndex());
			} else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {
				down(getBlankCutterIndex());
			} else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				right(getBlankCutterIndex());
			} else if (code == KeyEvent.VK_RIGHT
					|| code == KeyEvent.VK_KP_RIGHT) {
				left(getBlankCutterIndex());
			}
		}

		@Override
		public void keyReleased(KeyEvent event) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	public void addSplitPictureListener(SplitPictureListener listener) {
		this.spl = listener;
	}

	public void removeSplitPictureListener() {
		this.spl = new SplitPictureListener() {
			@Override
			public void completed() {
			}

			@Override
			public void moved() {
			}

			@Override
			public void moving() {
			}
		};
	}

	public SplitPictureListener[] getSplitPictureListeners() {
		return new SplitPictureListener[] { spl };
	}

	public Image getSrcPic() {
		return srcPic;
	}

	public void setSrcPic(Image srcPic) {
		this.srcPic = srcPic;
		updateSplitPicture();
	}

	public ImageCutter getPicCutter() {
		return picCutter;
	}

	CutterImage[] getCutterPics() {
		return cutterPics;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getCellspacing() {
		return cellspacing;
	}

	public void setCellspacing(int cellspacing) {
		this.cellspacing = cellspacing;
		updateSplitPicture();
	}

}
