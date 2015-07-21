package xyz.openthinks.vimixer.ui.controller.biz.progressfigure;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import xyz.openthinks.vimixer.ui.controller.BaseController;
import xyz.openthinks.vimixer.ui.controller.biz.blockfigure.DynamicPaintType;
import xyz.openthinks.vimixer.ui.model.ViFile;

/**
 * Show progress overview and dynamic figure progress mixed process
 * 
 * @author minjdai
 *
 */
public class ProgressOverViewFigure {
	private BaseController controller;
	private ViFile observable;
	private final ProgressView progressView;
	private Pane progressPane;

	public static ProgressOverViewFigure valueOf(ViFile observableItem){
		return new ProgressOverViewFigure(observableItem);
	}
	
	public ProgressOverViewFigure(ViFile observableItem) {
		this.observable = observableItem;
		this.progressView = ProgressFiguresPool.get(observableItem);
	}

	public ProgressOverViewFigure with(BaseController mainFrameController) {
		this.controller = mainFrameController;
		return this;
	}

	public ProgressOverViewFigure targetOn(Pane progressPane) {
		this.progressPane = progressPane;
		return this;
	}

	public void render() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				if (!progressView.isInitialized()) {
					progressView.initial(observable, controller);
				}
				progressPane.getChildren().clear();
				progressPane.getChildren().add(progressView);
			}
		});
	}

	public void dynamic(DynamicPaintType paintType,final Double... percents) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (progressView == null || !progressView.isInitialized())
					return;
				switch (paintType) {
				case INITIALIZED_ALL:
				case PROCESSED_ALL:
					progressView.updateProgress(paintType.progress());
					break;
				case PROCESSED_PARTIAL:
					if (percents != null && percents.length>0) {
						progressView.updateProgress(percents[0]);
					}
					break;
				default:
					break;
				}
			}

		});
	}

	public void destory() {
		ProgressView progressView = ProgressFiguresPool.get(observable);
		if(progressView.getParent()!=null){
			((Pane)progressView.getParent()).getChildren().remove(progressView);
		}
	}
	
	public void push() {
		if (!this.equals(ProgressFiguresPool.currentFigure()))
			ProgressFiguresPool.push(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((observable == null) ? 0 : observable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgressOverViewFigure other = (ProgressOverViewFigure) obj;
		if (observable == null) {
			if (other.observable != null)
				return false;
		} else if (!observable.equals(other.observable))
			return false;
		return true;
	}
	
}
