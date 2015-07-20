package xyz.openthinks.vimixer.ui.model.configure;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import xyz.openthinks.crypto.mix.MixSegmentor;
import xyz.openthinks.crypto.mix.impl.SmartMixSegment;

@XmlType
@XmlRootElement(name = "smart-segmentor")
public class SmartLinearSegmentor extends Segmentor {
	public static final SegmentorType TYPE = SegmentorType.SMART;

	public SmartLinearSegmentor() {
		super(TYPE);
	}

	@Override
	public String toString() {
		return "SmartLinearSegmentor [getType()=" + getType() + "]";
	}

	@Override
	public void refresh(Segmentor otherSeg) {

	}

	@Override
	public MixSegmentor mixSegmentor() {
		return SmartMixSegment.get();
	}

}
