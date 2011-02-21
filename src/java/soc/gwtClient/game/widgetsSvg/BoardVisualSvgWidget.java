package soc.gwtClient.game.widgetsSvg;

import soc.gwtClient.game.widgetsInterface.main.BoardVisualWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.visuals.GameBoardVisual;
import soc.gwtClient.game.widgetsSvg.visuals.GameBoardSvg;

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
