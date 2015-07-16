package xyz.openthinks.vimixer.ui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import xyz.openthinks.vimixer.ui.controller.biz.ProcessMixBizThread;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.ViFile.STATUS;
import xyz.openthinks.vimixer.ui.model.ViFileSupportType;

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
	@FXML
	private Button runBtn;
	@FXML
	private ContextMenu tableCtxmenu;
	@FXML
	private MenuItem resetMenuItem;
	@FXML
	private MenuItem resetAllMenuItem;
	@FXML
	private MenuItem clearMenuItem;
	@FXML
	private MenuItem clearAllMenuItem;
	@FXML
	private FlowPane blockPane;
	@FXML
	private ScrollPane bottomScrollPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idColumn.setCellValueFactory(cellData -> cellData.getValue()
				.idProperty());
		filePathColumn.setCellValueFactory(cellData -> cellData.getValue()
				.filePathProperty());
		statusColumn.setCellValueFactory(cellData -> cellData.getValue()
				.statusProperty());
		tableCtxmenu.setOnShowing((event)->{
				boolean isShow = !vifileTable.getSelectionModel().getSelectedItems().isEmpty();
				resetMenuItem.setVisible(isShow);
				clearMenuItem.setVisible(isShow);
				isShow = !vifileTable.getItems().isEmpty();
				resetAllMenuItem.setVisible(isShow);
				clearAllMenuItem.setVisible(isShow);
		});
//		resetAllMenuItem.visibleProperty().bind(Bindings.isNotEmpty(vifileTable.getItems()));
//		clearAllMenuItem.visibleProperty().bind(Bindings.isNotEmpty(vifileTable.getItems()));
//		resetMenuItem.visibleProperty().bind(Bindings.isNotEmpty(vifileTable.getSelectionModel().getSelectedItems()));
//		clearMenuItem.visibleProperty().bind(Bindings.isNotEmpty(vifileTable.getSelectionModel().getSelectedItems()));
		blockPane.prefWidthProperty().bind(bottomScrollPane.widthProperty().add(-15));
	}

	@Override
	protected void afterSetTransfer() {
		super.afterSetTransfer();
		// bind table view item property to data model
		vifileTable.itemsProperty().bindBidirectional(this.listProperty());
		
		configPathField.textProperty().bind(
				this.configure().storedFileProperty());
		// bind run button disable or not
		this.configure().secretKeyProperty()
				.addListener((observable, oldvalue, newvalue) -> {
					if (newvalue == null || newvalue.trim().isEmpty()) {
						runBtn.setDisable(true);
					}else{
						runBtn.setDisable(false);
					}
		});
		// initial run button disable or not
		if(this.configure().getSecretKey()==null || this.configure().getSecretKey().trim().isEmpty()){
			runBtn.setDisable(true);
		}
		initialBlocksOverview();
	}

	private void initialBlocksOverview() {
		int block_width = 10, block_height = 10;
		int block_arc_width = 3, block_arc_height = 3;
		int block_count = 1000;
		Paint block_init_color = Color.ORANGE;
		for (int i = 0; i < block_count; i++) {
			Rectangle block = new Rectangle(block_width, block_height, block_init_color);
			block.setArcWidth(block_arc_width);
			block.setArcHeight(block_arc_height);
			blockPane.getChildren().add(block);
		}
		
	}

	@FXML
	private void handStartAction() {
		ObservableList<ViFile> viFiles = this.listProperty().get();
		FilteredList<ViFile> filteredList = viFiles
				.filtered(testFile -> ViFile.STATUS.NOT_START == testFile
						.getStatus());
		new ProcessMixBizThread(this, filteredList).start();
	}
	
	@FXML
	private void handOnDragOver(DragEvent event){
		Dragboard dragboard= event.getDragboard();
		if(dragboard.hasFiles()){
			event.acceptTransferModes(TransferMode.LINK);
		}else{
			event.consume();
		}
	}
	
	@FXML
	private void handDragDropped(DragEvent event){
		Dragboard dragboard= event.getDragboard();
		boolean success = false;
		if(dragboard.hasFiles()){
			for(File file: dragboard.getFiles()){
				if(file.isFile() && ViFileSupportType.accept(file)){
					this.listProperty().get().add(new ViFile(file));
					success=true;
				}
			}
		}
		event.setDropCompleted(success);
		event.consume();
	}
	
	@FXML
	private void handResetAction(){
		ObservableList<ViFile> viFiles = vifileTable.getSelectionModel().getSelectedItems();
		for(ViFile viFile:viFiles ){
			viFile.setStatus(STATUS.NOT_START);
		}
	}
	
	@FXML
	private void handResetAllAction(){
		ObservableList<ViFile> viFiles = this.listProperty().get();
		for(ViFile viFile:viFiles ){
			viFile.setStatus(STATUS.NOT_START);
		}
	}
	
	@FXML
	private void handClearAction(){
		ObservableList<ViFile> viFiles = vifileTable.getSelectionModel().getSelectedItems();
		vifileTable.getItems().removeAll(viFiles);
	}
	
	@FXML
	private void handClearAllAction(){
		this.listProperty().get().clear();
	}

}
