package xyz.openthinks.vimixer.ui.model.configure;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import xyz.openthinks.crypto.mix.MixSegmentor;
/**
 * The wrapper of {@link MixSegmentor}, used in {@link ViMixerConfigure}
 * @author minjdai
 *
 */
@XmlType
@XmlSeeAlso({ SimpleLinearSegmentor.class,SmartLinearSegmentor.class })
public abstract class Segmentor {
	private ObjectProperty<SegmentorType> type;

	public Segmentor() {
		this.type = new SimpleObjectProperty<SegmentorType>();
	}

	public Segmentor(SegmentorType type) {
		super();
		this.type = new SimpleObjectProperty<SegmentorType>(type);
	}

	public final ObjectProperty<SegmentorType> typeProperty() {
		return this.type;
	}

	public final SegmentorType getType() {
		return this.typeProperty().get();
	}

	public final void setType(final SegmentorType type) {
		this.typeProperty().set(type);
	}

	@Override
	public String toString() {
		return type.get().toString() ;
	}
	
	public abstract  void refresh(Segmentor otherSeg);
	
	/**
	 * get {@link MixSegmentor} instance
	 * @return
	 */
	public abstract MixSegmentor mixSegmentor();

}
