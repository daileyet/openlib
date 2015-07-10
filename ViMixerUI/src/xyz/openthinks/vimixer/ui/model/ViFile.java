package xyz.openthinks.vimixer.ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViFile {
	private IntegerProperty id;
	private final StringProperty filePath; 
	private  StringProperty status;
	
	public ViFile(String filePath) {
		this.filePath =new SimpleStringProperty(filePath);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public final void setId(final int id) {
		this.idProperty().set(id);
	}

	public final StringProperty filePathProperty() {
		return this.filePath;
	}

	public final java.lang.String getFilePath() {
		return this.filePathProperty().get();
	}

	public final void setFilePath(final java.lang.String filePath) {
		this.filePathProperty().set(filePath);
	}

	public final StringProperty statusProperty() {
		return this.status;
	}

	public final java.lang.String getStatus() {
		return this.statusProperty().get();
	}

	public final void setStatus(final java.lang.String status) {
		this.statusProperty().set(status);
	}

	
	
	
	
}
