package xyz.openthinks.vimixer.ui;

import java.io.IOException;

import xyz.openthinks.vimixer.resources.ResourceLoader;
import xyz.openthinks.vimixer.ui.controller.MainFrameController;
import xyz.openthinks.vimixer.ui.controller.RootLayoutController;
import xyz.openthinks.vimixer.ui.model.ViFile;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private final ObservableList<ViFile> viFiles = FXCollections
			.observableArrayList();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.getIcons().add(ResourceLoader.APP_ICON);
		initRootLayout();
		showMainFrame();
	}

	public final ObservableList<ViFile> getViFiles() {
		return viFiles;
	}
	
	public final Window getPrimaryStage() {
		return primaryStage;
	}

	private void initRootLayout() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceLoader.FXML_ROOTLAYOUT);
		try {
			this.rootLayout = loader.load();
			Scene scene = new Scene(this.rootLayout);
			scene.getStylesheets().add(ResourceLoader.CSS_APP.toExternalForm());

			RootLayoutController controller = loader.getController();
			controller.setApp(this);

			this.primaryStage.setScene(scene);
			this.primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showMainFrame() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceLoader.FXML_MAINFRAME);
		try {
			AnchorPane anchorPane = loader.load();
			MainFrameController controller = loader.getController();
			controller.setApp(this);
			this.rootLayout.setCenter(anchorPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
