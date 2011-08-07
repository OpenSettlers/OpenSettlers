package org.soc.gwt.client.game.widgetsSvg;

import org.soc.common.views.widgetsInterface.main.BoardVisualWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.gwt.client.game.widgetsSvg.visuals.GameBoardSvg;

import com.google.gwt.user.client.ui.LayoutPanel;

public class BoardVisualSvgWidget extends LayoutPanel implements
        BoardVisualWidget
{
    GameBoardVisual boardVisual;
    GameWidget gameWidget;

    public BoardVisualSvgWidget(GameWidget gameWidget)
    {
        super();
        this.boardVisual = new GameBoardSvg(gameWidget.getGame(), 800, 800);
        this.add(boardVisual);
    }

    /**
     * 
     * @see com.google.gwt.user.client.ui.LayoutPanel#onResize()
     */
    @Override
    public void onResize()
    {
        super.onResize();
        // boardVisual.resize(this.getOffsetWidth(), this.getOffsetHeight());
    }

    @Override
    public GameBoardVisual getBoardVisual()
    {
        return boardVisual;
    }

}
