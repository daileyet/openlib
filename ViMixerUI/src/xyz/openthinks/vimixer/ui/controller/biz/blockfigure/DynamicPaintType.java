package xyz.openthinks.vimixer.ui.controller.biz.blockfigure;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum DynamicPaintType {
	INITIALIZED_ALL(Color.ORANGE), PROCESSED_ALL(Color.GREEN),PROCESSED_PARTIAL(Color.GREEN);

	private final Paint paint;

	private DynamicPaintType(final Paint paint) {
		this.paint = paint;
	}

	public final Paint color() {
		return this.paint;
	}
}
