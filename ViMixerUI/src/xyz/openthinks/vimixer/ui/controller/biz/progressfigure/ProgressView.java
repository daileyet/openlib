package xyz.openthinks.vimixer.ui.controller.biz.progressfigure;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import xyz.openthinks.vimixer.ui.controller.BaseController;
import xyz.openthinks.vimixer.ui.controller.MainFrameController;
import xyz.openthinks.vimixer.ui.model.ViFile;

public class ProgressView extends HBox{
	private ProgressBar progressBar;
	private Label progressLabel;
	private AtomicBoolean initailized = new AtomicBoolean(false);
	private Lock lock = new ReentrantLock();
	/**
	 * check all the elements at the node are initialized or not
	 * @return
	 */
	public boolean isInitialized() {
		return initailized.get();
	}
	
	/**
	 * initial progress bar in this UI view
	 * @param observable
	 * @param controller
	 */
	public void initial(ViFile observable, BaseController controller) {
		lock.lock();
		try{
			progressBar=new ProgressBar(observable.infoProperty().get().computeProgress());
			progressLabel=new Label(" "+observable.infoProperty().get().computeProgressPercent());
			progressLabel.setAlignment(Pos.CENTER_RIGHT);
//			progressLabel.textProperty().bind(Bindings.convert(progressBar.progressProperty()));
			this.getChildren().add(progressBar);
			this.getChildren().add(progressLabel);
			
			progressBar.progressProperty().addListener((obser,oldvalue,newvalue)->{
				Double percent = newvalue.doubleValue()*100;
				progressLabel.setText(" "+percent.intValue()+"%");
			});
			
			initailized.set(true);
			this.setCache(true);
			//bind this overview pane width to the right width property in MainFrame
			this.progressBar.prefWidthProperty().bind(((MainFrameController)controller).getBlockPaneWidthProperty().add(-35));
		}finally{
			lock.unlock();
		}
	}
	
	public void updateProgress(double computeProgress){
		this.progressBar.setProgress(computeProgress);
	}
	
}
