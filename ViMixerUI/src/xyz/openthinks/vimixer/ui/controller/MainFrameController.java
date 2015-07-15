package xyz.openthinks.vimixer.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import xyz.openthinks.vimixer.ui.controller.biz.ProcessMixBizThread;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.ViFile.STATUS;

public class MainFrameController extends BaseController {

	@FXML
	private TableView<ViFile> vifileTable;
	@FXML
	private TableColumn<ViFile, Number> idColumn;
	@FXML
	private TableColumn<ViFile, String> filePathColumn;
	@FXML
	private TableColumn<ViFile, STATUS> statusColumn;
	@FXML
	private TextField configPathField;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idColumn.setCellValueFactory(cellData->cellData.getValue().idProperty());
		filePathColumn.setCellValueFactory(cellData -> cellData.getValue().filePathProperty());
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		
	}

	@Override
	protected void afterSetTransfer() {
		super.afterSetTransfer();
		vifileTable.setItems(this.list());
		configPathField.textProperty().bind(this.configure().storedFileProperty());
	}
	
	@FXML
	private void handStartAction(){
		ObservableList<ViFile> viFiles = this.list();
		FilteredList<ViFile> filteredList = viFiles.filtered(testFile -> ViFile.STATUS.NOT_START == testFile.getStatus());
		new ProcessMixBizThread(filteredList).start();
	}


}
