package soc.gwtClient.game.widgets.bitmap;

import soc.gwtClient.visuals.abstractVisuals.BoardVisual;

import com.google.gwt.user.client.ui.LayoutPanel;

public class BoardLayoutPanel extends LayoutPanel
{
    BoardVisual boardVisual;

    public BoardLayoutPanel(BoardVisual boardVisual)
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
