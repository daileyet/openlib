package xyz.openthinks.vimixer.ui.controller.biz.blockfigure;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import xyz.openthinks.vimixer.ui.controller.biz.figure.Dynamically;
import xyz.openthinks.vimixer.ui.controller.biz.figure.FigureOverview;
import xyz.openthinks.vimixer.ui.model.ViFile;

/**
 * Show blocks overview and dynamic figure blocks mixed process
 * 
 * @author minjdai
 *
 */
public class BlockOverViewFigure extends FigureOverview<BlocksView> implements
		Dynamically {

	public BlockOverViewFigure(ViFile observableItem) {
		super(observableItem);
	}

	/**
	 * Dynamic change block unit which is completed or not start
	 * 
	 * @param paintType
	 * @param positions
	 */
	public void dynamic(DynamicPaintType paintType, Number... positions) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (figureView == null || !figureView.isInitialized())
					return;
				switch (paintType) {
				case INITIALIZED_ALL:
				case PROCESSED_ALL:
					for (Node node : figureView.getChildren()) {
						((Shape) node).setFill(paintType.color());
					}
					break;
				case PROCESSED_PARTIAL:
					if (positions != null) {
						for (Number position : positions) {
							figureView.find(position.longValue()).setFill(
									paintType.color());
						}
					}
					break;
				default:
					break;
				}
			}
		});
	}

}
