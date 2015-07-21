/**
 * 
 */
package xyz.openthinks.vimixer.ui.controller.biz.figure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import xyz.openthinks.vimixer.ui.controller.biz.blockfigure.BlockOverViewFigure;
import xyz.openthinks.vimixer.ui.controller.biz.blockfigure.BlocksView;
import xyz.openthinks.vimixer.ui.controller.biz.progressfigure.ProgressOverViewFigure;
import xyz.openthinks.vimixer.ui.controller.biz.progressfigure.ProgressView;
import xyz.openthinks.vimixer.ui.model.ViFile;

/**
 * @author minjdai
 *
 */
public class FigureOverviewPool {

	private static class FigureableMap {
		private ObservableMap<Class<? extends FigureOverview<? extends Figureable>>, Figureable> figureMap = FXCollections
				.observableHashMap();

		@SuppressWarnings({ "unchecked" })
		<K extends Figureable> K get(
				Class<? extends FigureOverview<? extends Figureable>> figureOverviewClz) {
			if (figureMap.containsKey(figureOverviewClz))
				;
			else {
				Figureable figureable = null;
				if (figureOverviewClz == BlockOverViewFigure.class) {
					figureable = new BlocksView();
				} else if (figureOverviewClz == ProgressOverViewFigure.class) {
					figureable = new ProgressView();
				} else {
					throw new UnsupportFigureOverview(
							"Not suuport this class type:" + figureOverviewClz);
				}
				figureMap.put(figureOverviewClz, figureable);
			}
			return (K) figureMap.get(figureOverviewClz);
		}
	}

	private static ObservableMap<ViFile, FigureableMap> caches = FXCollections
			.observableHashMap();

	private static ObservableMap<Class<? extends FigureOverview<? extends Figureable>>, FigureOverview<? extends Figureable>> currentFigureMap = FXCollections
			.observableHashMap();

	public static <K extends Figureable> K get(ViFile observableItem,
			Class<? extends FigureOverview<K>> figureOverviewClz) {

		if (caches.containsKey(observableItem)) {
			FigureableMap figureableMap = caches.get(observableItem);
			return figureableMap.get(figureOverviewClz);
		} else {
			caches.put(observableItem, new FigureableMap());
		}
		return get(observableItem, figureOverviewClz);
	}

	public static FigureOverview<? extends Figureable> currentFigure(
			Class<? extends FigureOverview<? extends Figureable>> figureOverviewClz) {
		return currentFigureMap.get(figureOverviewClz);
	}

	public static <K extends Figureable, T extends FigureOverview<K>> void push(
			T figureOverview) {

	}

}
