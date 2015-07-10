package xyz.openthinks.vimixer.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import xyz.openthinks.vimixer.ui.model.ViFile;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class MainFrameController implements Initializable {

	@FXML
	private TableView<ViFile> vifileTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setApp(Application main) {
		
	}

}
