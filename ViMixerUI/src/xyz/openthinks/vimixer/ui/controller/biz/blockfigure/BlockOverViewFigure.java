package xyz.openthinks.vimixer.ui.controller.biz.blockfigure;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import xyz.openthinks.vimixer.ui.controller.BaseController;
import xyz.openthinks.vimixer.ui.model.ViFile;

/**
 * Show blocks overview and dynamic figure blocks mixed process
 * 
 * @author minjdai
 *
 */
public class BlockOverViewFigure {

	public static BlockOverViewFigure valueOf(ViFile observableItem) {
		return new BlockOverViewFigure(observableItem);
	}

	private BaseController controller;
	private ViFile observable;
	private final BlocksView blocksView;
	private Pane blockPane;

	public BlockOverViewFigure(ViFile observableItem) {
		this.observable = observableItem;
		this.blocksView = BlockFiguresPool.get(observableItem);
	}

	public BlockOverViewFigure with(BaseController mainFrameController) {
		this.controller = mainFrameController;
		return this;
	}

	public BlockOverViewFigure targetOn(Pane blockPane) {
		this.blockPane = blockPane;
		return this;
	}

	/**
	 * render later
	 */
	public void render() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!BlockOverViewFigure.this.blocksView.isInitialized()) {
					BlockOverViewFigure.this.blocksView.initial(BlockOverViewFigure.this.observable,
							BlockOverViewFigure.this.controller);
				}
				BlockOverViewFigure.this.blockPane.getChildren().clear();
				BlockOverViewFigure.this.blockPane.getChildren().add(BlockOverViewFigure.this.blocksView);
			}
		});
	}

	/**
	 * Dynamic change block unit which is completed or not start
	 * @param paintType
	 * @param positions
	 */
	public void dynamic(DynamicPaintType paintType, Long... positions) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				BlocksView blocksView = BlockOverViewFigure.this.blocksView;
				if (blocksView == null || !blocksView.isInitialized())
					return;
				switch (paintType) {
				case INITIALIZED_ALL:
				case PROCESSED_ALL:
					for(Node node: blocksView.getChildren()){
						((Shape)node).setFill(paintType.color());
					}
					break;
				case PROCESSED_PARTIAL:
					if (positions != null) {
						for (Long position : positions) {
							blocksView.find(position).setFill(paintType.color());
						}
					}
					break;
				default:
					break;
				}
			}
		});
	}

	
	public void destory(){
		BlocksView blocksView = BlockFiguresPool.get(observable);
		if(blocksView.getParent()!=null){
			((Pane)blocksView.getParent()).getChildren().remove(blocksView);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((observable == null) ? 0 : observable.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockOverViewFigure other = (BlockOverViewFigure) obj;
		if (observable == null) {
			if (other.observable != null)
				return false;
		} else if (!observable.equals(other.observable))
			return false;
		return true;
	}

	public void push() {
		if (!this.equals(BlockFiguresPool.currentFigure()))
			BlockFiguresPool.push(this);
	}

}
