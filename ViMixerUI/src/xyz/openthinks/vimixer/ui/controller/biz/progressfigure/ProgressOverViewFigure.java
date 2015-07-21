package xyz.openthinks.vimixer.ui.controller.biz.progressfigure;

import javafx.application.Platform;
import xyz.openthinks.vimixer.ui.controller.biz.blockfigure.DynamicPaintType;
import xyz.openthinks.vimixer.ui.controller.biz.figure.Dynamically;
import xyz.openthinks.vimixer.ui.controller.biz.figure.FigureOverview;
import xyz.openthinks.vimixer.ui.model.ViFile;

/**
 * Show progress overview and dynamic figure progress mixed process
 * 
 * @author minjdai
 *
 */
public class ProgressOverViewFigure extends FigureOverview<ProgressView> implements Dynamically {

	public ProgressOverViewFigure(ViFile observableItem) {
		super(observableItem);
	}

	public void dynamic(DynamicPaintType paintType,final Number... percents) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (figureView == null || !figureView.isInitialized())
					return;
				switch (paintType) {
				case INITIALIZED_ALL:
				case PROCESSED_ALL:
					figureView.updateProgress(paintType.progress());
					break;
				case PROCESSED_PARTIAL:
					if (percents != null && percents.length>0) {
						figureView.updateProgress(percents[0].doubleValue());
					}
					break;
				default:
					break;
				}
			}

		});
	}

}
