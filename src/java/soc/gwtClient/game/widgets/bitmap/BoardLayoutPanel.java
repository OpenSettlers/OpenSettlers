package soc.gwtClient.game.widgets.bitmap;

import soc.common.client.visuals.board.IBoardVisual;

import com.google.gwt.user.client.ui.LayoutPanel;

public class BoardLayoutPanel extends LayoutPanel
{
    IBoardVisual boardVisual;

    public BoardLayoutPanel(IBoardVisual boardVisual)
    {
        super();
        this.boardVisual = boardVisual;
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

}
