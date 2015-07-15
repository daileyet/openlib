package xyz.openthinks.vimixer.ui.model.configure;

import javax.xml.bind.annotation.XmlRootElement;

import xyz.openthinks.crypto.mix.impl.DefaultMixSegment;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

@XmlRootElement(name = "segmentor")
public class SimpleLinearSegmentor extends Segmentor {
	public static final String TYPE = "Simple";

	private IntegerProperty space;
	private LongProperty length;

	public SimpleLinearSegmentor() {
		this(0, 0);
	}

	public SimpleLinearSegmentor(int space, long length) {
		super(TYPE);
		DefaultMixSegment instance = DefaultMixSegment.get(space, length);
		this.space = new SimpleIntegerProperty(instance.getSpace());
		this.length = new SimpleLongProperty(instance.getLength());
	}

	public final IntegerProperty spaceProperty() {
		return this.space;
	}

	public final int getSpace() {
		return this.spaceProperty().get();
	}

	public final void setSpace(final int space) {
		this.spaceProperty().set(space);
	}

	public final LongProperty lengthProperty() {
		return this.length;
	}

	public final long getLength() {
		return this.lengthProperty().get();
	}

	public final void setLength(final long length) {
		this.lengthProperty().set(length);
	}

	@Override
	public String toString() {
		return "SimpleLinearSegmentorP [space=" + space + ", length=" + length
				+ ", getType()=" + getType() + "]";
	}

	@Override
	public void refresh(Segmentor otherSeg) {
		if(otherSeg instanceof SimpleLinearSegmentor){
			SimpleLinearSegmentor other = (SimpleLinearSegmentor)otherSeg;
			setLength(other.getLength());
			setSpace(other.getSpace());
		}
	}
}
