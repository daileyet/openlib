package xyz.openthinks.vimixer.ui.model.configure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 * configure XML file entity
 * @author minjdai
 *
 */
@XmlRootElement(name = "vimix")
public class ViMixerConfigure {
	private StringProperty configureName;
	private StringProperty secretKey;
	private ObjectProperty<Segmentor> segmentor;
	private transient StringProperty storedFile;

	public ViMixerConfigure() {
		this.configureName = new SimpleStringProperty();
		this.secretKey = new SimpleStringProperty();
		this.segmentor = new SimpleObjectProperty<Segmentor>();
		this.storedFile = new SimpleStringProperty();
	}

	public ViMixerConfigure(String configureName, String secretKey,
			Segmentor segmentor) {
		super();
		this.configureName = new SimpleStringProperty(configureName);
		this.secretKey = new SimpleStringProperty(secretKey);
		this.segmentor = new SimpleObjectProperty<Segmentor>(segmentor);
		this.storedFile = new SimpleStringProperty();
	}
	
	public final StringProperty configureNameProperty() {
		return this.configureName;
	}

	@XmlAttribute(name = "name")
	public final java.lang.String getConfigureName() {
		return this.configureNameProperty().get();
	}

	public final void setConfigureName(final java.lang.String configureName) {
		this.configureNameProperty().set(configureName);
	}

	public final StringProperty secretKeyProperty() {
		return this.secretKey;
	}

	public final java.lang.String getSecretKey() {
		return this.secretKeyProperty().get();
	}

	public final void setSecretKey(final java.lang.String secretKey) {
		this.secretKeyProperty().set(secretKey);
	}

	public final ObjectProperty<Segmentor> segmentorProperty() {
		return this.segmentor;
	}

	@XmlElementRef
	public final Segmentor getSegmentor() {
		return this.segmentorProperty().get();
	}

	public final void setSegmentor(
			final Segmentor segmentor) {
		this.segmentorProperty().set(segmentor);
	}
	
	

	@Override
	public String toString() {
		return "ViMixerConfigureP [configureName=" + configureName
				+ ", secretKey=" + secretKey + ", segmentor=" + segmentor + "]";
	}

	/**
	 * @throws PropertyException
	 * @throws FileNotFoundException
	 */
	public static void marshal(ViMixerConfigure configure, File file)
			throws JAXBException, PropertyException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(ViMixerConfigure.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(configure, new FileOutputStream(file));
	}

	public static ViMixerConfigure unmarshal(File file) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ViMixerConfigure.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (ViMixerConfigure) unmarshaller.unmarshal(file);
	}

	public final StringProperty storedFileProperty() {
		return this.storedFile;
	}

	@XmlTransient
	public final java.lang.String getStoredFile() {
		return this.storedFileProperty().get();
	}

	public final void setStoredFile(final java.lang.String storedFile) {
		this.storedFileProperty().set(storedFile);
	}


}
