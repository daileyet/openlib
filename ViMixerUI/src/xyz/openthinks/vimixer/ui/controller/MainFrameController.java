package xyz.openthinks.vimixer.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import xyz.openthinks.vimixer.ui.Main;
import xyz.openthinks.vimixer.ui.controller.biz.ProcessMixBizThread;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.ViFile.STATUS;

public class MainFrameController implements Initializable {

	@FXML
	private TableView<ViFile> vifileTable;
	@FXML
	private TableColumn<ViFile, Number> idColumn;
	@FXML
	private TableColumn<ViFile, String> filePathColumn;
	@FXML
	private TableColumn<ViFile, STATUS> statusColumn;
	
	private Main mainApp;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty());
		filePathColumn.setCellValueFactory(cellData -> cellData.getValue().filePathProperty());
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
	}

	public void setApp(Application main) {
		this.mainApp = (Main) main;
		vifileTable.setItems(mainApp.getViFiles());
	}
	
	@FXML
	private void handStartAction(){
		ObservableList<ViFile> viFiles = mainApp.getViFiles();
		FilteredList<ViFile> filteredList = viFiles.filtered(testFile -> ViFile.STATUS.NOT_START == testFile.getStatus());
		new ProcessMixBizThread(filteredList).start();
	}


}
