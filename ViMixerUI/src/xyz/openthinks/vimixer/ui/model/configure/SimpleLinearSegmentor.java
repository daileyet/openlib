package xyz.openthinks.vimixer.ui.model.configure;

import javax.xml.bind.annotation.XmlRootElement;

import xyz.openthinks.crypto.mix.impl.DefaultMixSegment;

/**
 * @see DefaultMixSegment
 * @author minjdai
 *
 */
@XmlRootElement(name = "segmentor")
public class SimpleLinearSegmentor extends Segmentor {
	public static final String TYPE = "simple";

	private int space;
	private long length;

	public SimpleLinearSegmentor() {
		this(0, 0); // call default setting in DefaultMixSegment
	}

	public SimpleLinearSegmentor(int space, long length) {
		super(TYPE);
		DefaultMixSegment instance = DefaultMixSegment.get(space, length);
		this.space = instance.getSpace();
		this.length = instance.getLength();
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "SimpleLinearSegmentor [space=" + space + ", length=" + length
				+ ", toString()=" + super.toString() + "]";
	}

}
