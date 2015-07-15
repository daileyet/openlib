package xyz.openthinks.vimixer.ui.model.configure;

import javax.xml.bind.annotation.XmlSeeAlso;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlSeeAlso({ SmartLinearSegmentor.class, SimpleLinearSegmentor.class })
public abstract class Segmentor {
	private StringProperty type;

	public Segmentor() {
		this.type = new SimpleStringProperty();
	}

	public Segmentor(String type) {
		super();
		this.type = new SimpleStringProperty(type);
	}

	public final StringProperty typeProperty() {
		return this.type;
	}

	public final java.lang.String getType() {
		return this.typeProperty().get();
	}

	public final void setType(final java.lang.String type) {
		this.typeProperty().set(type);
	}

	@Override
	public String toString() {
		return type.get() ;
	}
	
	public abstract  void refresh(Segmentor otherSeg);

}
