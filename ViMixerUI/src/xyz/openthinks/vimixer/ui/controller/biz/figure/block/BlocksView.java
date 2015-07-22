package xyz.openthinks.vimixer.ui.controller.biz.figure.block;

import static xyz.openthinks.vimixer.ui.controller.biz.figure.DynamicPaintType.INITIALIZED_ALL;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import xyz.openthinks.crypto.mix.MixBlock;
import xyz.openthinks.crypto.mix.MixBlocks;
import xyz.openthinks.crypto.mix.MixTarget;
import xyz.openthinks.crypto.mix.impl.MixFile;
import xyz.openthinks.vimixer.ui.controller.BaseController;
import xyz.openthinks.vimixer.ui.controller.MainFrameController;
import xyz.openthinks.vimixer.ui.controller.biz.figure.DynamicPaintType;
import xyz.openthinks.vimixer.ui.controller.biz.figure.Figureable;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.configure.Segmentor;

/**
 * graphic for one {@link ViFile} blocks view, render and process
 * 
 * @author minjdai
 *
 */
public class BlocksView extends FlowPane implements Figureable {
	private int block_width = 10, block_height = 10;
	private int block_arc_width = 3, block_arc_height = 3;
	private AtomicBoolean initailized = new AtomicBoolean(false);
	private Lock lock = new ReentrantLock();
	// store the  mapping between block unit in file and block unit in UI
	private ObservableMap<MixBlock, Shape> map = FXCollections.observableHashMap();

	public boolean isInitialized() {
		return initailized.get();
	}

	public void initial(ViFile observable, BaseController controller) {
		lock.lock();
		try {
			DynamicPaintType paintType = INITIALIZED_ALL;
			paintType = observable.getStatus().paintType();
			Segmentor segmentor = controller.configure().getSegmentor();
			MixTarget mixTarget = new MixFile(observable.getFile(), segmentor.mixSegmentor());
			MixBlocks mixBlocks = mixTarget.blocks();
			for (int i = 0; i < mixBlocks.size(); i++) {
				Rectangle ret = new Rectangle(block_width, block_height, paintType.color());
				ret.setArcWidth(block_arc_width);
				ret.setArcHeight(block_arc_height);
				this.getChildren().add(ret);
				map.put(mixBlocks.get(i), ret);
			}
			initailized.set(true);
			this.setCache(true);
			//bind this overview pane width to the right width property in MainFrame
			this.prefWidthProperty().bind(((MainFrameController) controller).getBlockPaneWidthProperty());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * find the block unit UI by block unit position in file
	 * @param position
	 * @return
	 */
	public Shape find(MixBlock block) {
		return map.get(block);
	}

	/**
	 * @return the block_width
	 */
	public int getBlock_width() {
		return block_width;
	}

	/**
	 * @param block_width the block_width to set
	 */
	public void setBlock_width(int block_width) {
		this.block_width = block_width;
	}

	/**
	 * @return the block_height
	 */
	public int getBlock_height() {
		return block_height;
	}

	/**
	 * @param block_height the block_height to set
	 */
	public void setBlock_height(int block_height) {
		this.block_height = block_height;
	}

	/**
	 * @return the block_arc_width
	 */
	public int getBlock_arc_width() {
		return block_arc_width;
	}

	/**
	 * @param block_arc_width the block_arc_width to set
	 */
	public void setBlock_arc_width(int block_arc_width) {
		this.block_arc_width = block_arc_width;
	}

	/**
	 * @return the block_arc_height
	 */
	public int getBlock_arc_height() {
		return block_arc_height;
	}

	/**
	 * @param block_arc_height the block_arc_height to set
	 */
	public void setBlock_arc_height(int block_arc_height) {
		this.block_arc_height = block_arc_height;
	}

	@Override
	public Node getView() {
		return this;
	}

	@Override
	public void destory() {
		if (this.getParent() != null) {
			((Pane) this.getParent()).getChildren().remove(this);
		}

	}

}