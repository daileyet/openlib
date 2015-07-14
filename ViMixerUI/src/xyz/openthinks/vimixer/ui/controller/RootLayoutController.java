package xyz.openthinks.vimixer.ui.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import xyz.openthinks.vimixer.ui.Main;
import xyz.openthinks.vimixer.ui.model.ViFile;

public class RootLayoutController implements Initializable {

	private Main mainApp;

	private static final ObservableList<String> SUPPORT_LIST_VIDEO;
	private static final ObservableList<String> SUPPORT_LIST_IMAGE;
	static {
		SUPPORT_LIST_VIDEO = FXCollections.observableArrayList("*.mkv",
				"*.flv", "*.ogg", "*.ogv", "*.avi", "*.mov", "*.qt", "*.wmv", "*.rm", ".rmvb",
				"*.mp4", "*.mpg", "*.mpeg", "*.3gp", "*.3g2");
		SUPPORT_LIST_IMAGE = FXCollections.observableArrayList("*.tiff","*.png","*.gif","*.jpg","*.bmp","*.jpeg");
	}

	public void setApp(Application main) {
		this.mainApp = (Main) main;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void handAddAction() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter(
				"video files", SUPPORT_LIST_VIDEO);
		FileChooser.ExtensionFilter extensionFilter2 = new FileChooser.ExtensionFilter(
				"image files", SUPPORT_LIST_IMAGE);
		fileChooser.getExtensionFilters().addAll(extensionFilter1,
				extensionFilter2);
		List<File> files = fileChooser.showOpenMultipleDialog(mainApp
				.getPrimaryStage());
		if (files != null) {
			for (File file : files) {
				mainApp.getViFiles().add(new ViFile(file));
			}
		}
	}
	
	@FXML
	private void handQuitAction(){
		System.exit(0);;
	}

}
