package xyz.openthinks.vimixer.ui.model.configure;

import javax.xml.bind.annotation.XmlRootElement;
import xyz.openthinks.crypto.mix.impl.SmartMixSegment;

/**
 * @see SmartMixSegment
 * @author minjdai
 *
 */
@XmlRootElement(name = "segmentor")
public class SmartLinearSegmentor extends Segmentor {
	public static final String TYPE = "smart";

	public SmartLinearSegmentor() {
		super(TYPE);
	}

}
