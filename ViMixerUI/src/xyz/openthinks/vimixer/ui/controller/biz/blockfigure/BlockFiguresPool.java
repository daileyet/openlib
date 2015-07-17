package xyz.openthinks.vimixer.ui.controller.biz.blockfigure;

import java.util.concurrent.LinkedBlockingQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import xyz.openthinks.vimixer.ui.model.ViFile;

/**
 * 
 * @author minjdai
 *
 */
public final class BlockFiguresPool {
	static ObservableMap<ViFile, BlocksView> caches = FXCollections
			.observableHashMap();

	static BlocksView get(ViFile vifile) {
		if (caches.containsKey(vifile)) {
			return caches.get(vifile);
		} else {
			caches.put(vifile, new BlocksView());
			return caches.get(vifile);
		}
	}

	static BlockOverViewFigure currentFigure;

	static LinkedBlockingQueue<BlockOverViewFigure> concurrentLinkedQueue = new LinkedBlockingQueue<BlockOverViewFigure>();

	public static void push(BlockOverViewFigure overViewFigure) {
		currentFigure = overViewFigure;
		concurrentLinkedQueue.add(overViewFigure);
	}

	public static void active() {
		new BlockFiguresPoolThread().start();
	}

	static class BlockFiguresPoolThread extends Thread {
		@Override
		public void run() {
			BlockOverViewFigure picked = null;
			do {
				try {
					picked = BlockFiguresPool.concurrentLinkedQueue.take();
					if (BlockFiguresPool.currentFigure == picked)
						picked.render();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (true);
		}
	}
}