package xyz.openthinks.vimixer.ui.controller.biz;

import javafx.collections.ObservableList;
import xyz.openthinks.crypto.mix.MixBlock;
import xyz.openthinks.crypto.mix.MixTarget;
import xyz.openthinks.crypto.mix.Mixer;
import xyz.openthinks.crypto.mix.impl.DefaultMixProcesser;
import xyz.openthinks.crypto.mix.impl.DefaultMixStrategy;
import xyz.openthinks.crypto.mix.impl.FileMixer;
import xyz.openthinks.crypto.mix.impl.MixFile;
import xyz.openthinks.crypto.mix.impl.SmartMixSegment;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.ViFile.STATUS;
/**
 * a new thread to process mixing file
 * @author minjdai
 *
 */
public class ProcessMixBizThread extends Thread {
	private ObservableList<ViFile> viFiles;

	public ProcessMixBizThread(ObservableList<ViFile> filteredList) {
		this.viFiles = filteredList;
	}

	
	@Override
	public void run() {
		processBusiness();
	}
	
	private void processBusiness() {
		for (ViFile viFile : this.viFiles) {
			MixTarget mixTarget = new MixFile(viFile.getFile(),
					SmartMixSegment.get());
			Mixer mixer = new FileMixer(mixTarget,
					DefaultMixStrategy.get("123456"),
					new ViMixProcesser(viFile));
			try {
				mixer.mix();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author minjdai
	 *
	 */
	class ViMixProcesser extends DefaultMixProcesser {

		private ViFile viFile;

		public ViMixProcesser(ViFile viFile) {
			super();
			this.viFile = viFile;
		}

		@Override
		public void start() {
			super.start();
			this.viFile.statusProperty().set(STATUS.IN_PROCESSING);
		}

		@Override
		public void processed(MixBlock mixBlock) {
			// TODO
			super.processed(mixBlock);
		}

		@Override
		public void completed() {
			// completeTime = new Date().getTime();
			super.completed();
			this.viFile.statusProperty().set(STATUS.COMPLETED);
		}
	}

}
