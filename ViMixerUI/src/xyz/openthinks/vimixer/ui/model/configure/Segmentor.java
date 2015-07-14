package xyz.openthinks.vimixer.ui.model.configure;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @see SmartLinearSegmentor
 * @see SimpleLinearSegmentor
 * @author minjdai
 *
 */
@XmlSeeAlso({ SmartLinearSegmentor.class, SimpleLinearSegmentor.class })
public abstract class Segmentor {
	@Override
	public String toString() {
		return "Segmentor [type=" + type + "]";
	}

	private String type;

	public Segmentor() {
	}

	public Segmentor(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
