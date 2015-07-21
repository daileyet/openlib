package xyz.openthinks.vimixer.ui.controller.biz.figure;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import xyz.openthinks.vimixer.ui.controller.BaseController;
import xyz.openthinks.vimixer.ui.controller.biz.blockfigure.BlockOverViewFigure;
import xyz.openthinks.vimixer.ui.model.ViFile;

public class FigureOverview<T extends Figureable> {

	public static BlockOverViewFigure valueOf(ViFile observableItem) {
		return new BlockOverViewFigure(observableItem);
	}

	protected BaseController controller;
	protected ViFile observable;
	protected final T figureView;
	protected Pane blockPane;

	@SuppressWarnings("unchecked")
	public FigureOverview(ViFile observableItem) {
		this.observable = observableItem;
		this.figureView = FigureOverviewPool.get(observableItem,
				(Class<? extends FigureOverview<T>>) getClass());
	}

	/**
	 * associate with the {@link BaseController}
	 * 
	 * @param mainFrameController
	 * @return
	 */
	public FigureOverview<T> with(BaseController mainFrameController) {
		this.controller = mainFrameController;
		return this;
	}

	/**
	 * append the included view {@link Figureable} to target pane
	 * 
	 * @param blockPane
	 *            {@link Pane}
	 * @return
	 */
	public FigureOverview<T> targetOn(Pane blockPane) {
		this.blockPane = blockPane;
		return this;
	}

	/**
	 * render the included view {@link Figureable} in JavaFx thread
	 */
	public void render() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!figureView.isInitialized()) {
					figureView.initial(observable, controller);
				}
				blockPane.getChildren().clear();
				blockPane.getChildren().add(figureView.getView());
			}
		});
	}

	/**
	 * remove the included view {@link Figureable}
	 */
	@SuppressWarnings("unchecked")
	public void destory() {
		T figureView = (T) FigureOverviewPool.get(observable,
				(Class<? extends FigureOverview<T>>) getClass());
		if (figureView != null)
			figureView.destory();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((observable == null) ? 0 : observable.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FigureOverview<T> other = (FigureOverview<T>) obj;
		if (observable == null) {
			if (other.observable != null)
				return false;
		} else if (!observable.equals(other.observable))
			return false;
		return true;
	}

	/**
	 * push to {@link FigureOverviewPool} to execute, it is not immediate, it
	 * will push into a queue.
	 */
	@SuppressWarnings("unchecked")
	public void push() {
		if (!this
				.equals(FigureOverviewPool
						.currentFigure((Class<? extends FigureOverview<? extends Figureable>>) getClass())))
			;
		else
			FigureOverviewPool.push(this);
	}

}