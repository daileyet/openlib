/**
 * 
 */
package xyz.openthinks.vimixer.ui.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import xyz.openthinks.vimixer.ui.ViMixerApp;
import xyz.openthinks.vimixer.ui.ViMixerApp.TransferData;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.configure.ViMixerConfigure;

/**
 * Base controller
 * 
 * @author minjdai
 *
 */
public abstract class BaseController implements Initializable {
	private static final String PREF_FILE = "filePath";
	private TransferData transfer;

	public final void setTransfer(final TransferData transfer) {
		this.beforeSetTransfer();
		this.transfer = transfer;
		this.afterSetTransfer();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	protected void afterSetTransfer() {
	}

	protected void beforeSetTransfer() {
	}

	public final Application app() {
		return transfer.app();
	}

	public final ObservableList<ViFile> list() {
		return transfer.list();
	}

	public final ViMixerConfigure configure() {
		return transfer.configure();
	}

	public final Stage stage() {
		return transfer.stage();
	}

	public final File lastConfigureFile() {
		Preferences preferences = Preferences
				.userNodeForPackage(ViMixerApp.class);
		String filePath = preferences.get(PREF_FILE, null);
		if (filePath != null) {
			return new File(filePath);
		}
		return null;
	}

	private final void storeConfigurePath(File file) {
		Preferences preferences = Preferences
				.userNodeForPackage(ViMixerApp.class);
		if (file != null) {
			preferences.put(PREF_FILE, file.getPath());
			configure().setStoredFile(file.getAbsolutePath());
		} else {
			preferences.remove(PREF_FILE);
			configure().setStoredFile(null);
		}
	}
	
	/**
	 * @param file
	 */
	public final void loadConfigure(File file) {
		if(file!=null){
			try {
				ViMixerConfigure loadCconfigure = ViMixerConfigure.unmarshal(file);
				configure().setConfigureName(loadCconfigure.getConfigureName());
				configure().setSecretKey(loadCconfigure.getSecretKey());
				configure().setSegmentor(loadCconfigure.getSegmentor());
				this.storeConfigurePath(file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param file
	 */
	public final void saveConfigure(File file) {
		if(file!=null){
			try {
				ViMixerConfigure.marshal(this.configure(), file);
				this.storeConfigurePath(file);
			} catch (FileNotFoundException | JAXBException e) {
				e.printStackTrace();
			}
		}
	}
}