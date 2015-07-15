package xyz.openthinks.vimixer.ui.model.configure;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "segmentor")
public class SmartLinearSegmentor extends Segmentor {
	public static final String TYPE = "Smart";

	public SmartLinearSegmentor() {
		super(TYPE);
	}

	@Override
	public String toString() {
		return "SmartLinearSegmentorP [getType()=" + getType() + "]";
	}

	@Override
	public void refresh(Segmentor otherSeg) {
		
	}

}
