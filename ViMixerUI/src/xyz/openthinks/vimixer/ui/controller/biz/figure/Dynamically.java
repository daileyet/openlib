package xyz.openthinks.vimixer.ui.controller.biz.figure;

import xyz.openthinks.vimixer.ui.controller.biz.blockfigure.DynamicPaintType;
/**
 * Dynamic change interface for {@link FigureOverview}
 * @author minjdai
 *
 */
public interface Dynamically {
	public void dynamic(DynamicPaintType paintType, Number... args);
}
