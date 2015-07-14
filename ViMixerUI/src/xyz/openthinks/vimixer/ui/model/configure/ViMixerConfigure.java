package xyz.openthinks.vimixer.ui.model.configure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * configure XML for Vimixer
 * 
 * @author minjdai
 *
 */
@XmlRootElement(name = "vimix")
public class ViMixerConfigure {

	private String configureName;

	private String secretKey;

	private Segmentor segmentor;

	@XmlAttribute(name = "name")
	public String getConfigureName() {
		return configureName;
	}

	public void setConfigureName(String configureName) {
		this.configureName = configureName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	@XmlElementRef
	public Segmentor getSegmentor() {
		return segmentor;
	}

	public void setSegmentor(Segmentor segmentor) {
		this.segmentor = segmentor;
	}

	@Override
	public String toString() {
		return "ViMixerConfigure [configureName=" + configureName
				+ ", secretKey=" + secretKey + ", segmentor=" + segmentor + "]";
	}

	/**
	 * @throws JAXBException
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

}
