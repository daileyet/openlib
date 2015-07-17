package xyz.openthinks.vimixer.ui.controller.biz;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import xyz.openthinks.crypto.mix.MixBlock;
import xyz.openthinks.crypto.mix.MixTarget;
import xyz.openthinks.crypto.mix.Mixer;
import xyz.openthinks.crypto.mix.impl.DefaultMixProcesser;
import xyz.openthinks.crypto.mix.impl.DefaultMixStrategy;
import xyz.openthinks.crypto.mix.impl.FileMixer;
import xyz.openthinks.crypto.mix.impl.MixFile;
import xyz.openthinks.vimixer.ui.controller.BaseController;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.ViFile.STATUS;
import xyz.openthinks.vimixer.ui.model.configure.Segmentor;

/**
 * a new thread to process mixing file
 * 
 * @author minjdai
 *
 */
public class ProcessMixBizThread extends Thread {
	private final ObservableList<ViFile> viFiles;
	private final BaseController controller;

	public ProcessMixBizThread(final BaseController controller,
			final ObservableList<ViFile> filteredList) {
		this.controller = controller;
		this.viFiles = filteredList;
	}

	@Override
	public void run() {
		processBusiness();
	}

	private void processBusiness() {
		String secretKey = controller.configure().getSecretKey();
		if (secretKey == null || "".equals(secretKey.trim())) {
			Alert alert = new Alert(AlertType.ERROR);
			// alert.setHeaderText("");
			alert.setContentText("The secret key is empty, please configure it firstly!");
			alert.initOwner(controller.stage());
			alert.show();
			return;
		}
		Segmentor segmentor = controller.configure().getSegmentor();
		if (segmentor == null) {
			Alert alert = new Alert(AlertType.ERROR);
			// alert.setHeaderText("");
			alert.setContentText("The segmentor is lost, please configure it firstly!");
			alert.initOwner(controller.stage());
			alert.show();
			return;
		}
		for (ViFile viFile : this.viFiles) {
			MixTarget mixTarget = new MixFile(viFile.getFile(),
					segmentor.mixSegmentor());
			Mixer mixer = new FileMixer(mixTarget,
					DefaultMixStrategy.get(secretKey), new ViMixProcesser(
							viFile));
			try {
				mixer.mix();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mixTarget.free();
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
