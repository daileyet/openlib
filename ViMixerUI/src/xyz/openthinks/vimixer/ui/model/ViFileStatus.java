package xyz.openthinks.vimixer.ui.model;

import xyz.openthinks.vimixer.ui.controller.biz.blockfigure.DynamicPaintType;

public enum ViFileStatus {
	NOT_START(DynamicPaintType.INITIALIZED_ALL), IN_PROCESSING(DynamicPaintType.INITIALIZED_ALL), COMPLETED(
			DynamicPaintType.PROCESSED_ALL);

	private DynamicPaintType painType;

	private ViFileStatus(DynamicPaintType painType) {
		this.painType = painType;
	}

	public String toString() {
		return name();
	};

	public DynamicPaintType paintType() {
		return this.painType;
	}
}