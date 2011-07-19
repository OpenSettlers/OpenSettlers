package org.soc.gwt.client.game.widgetsSvg;

import org.soc.common.board.Board;
import org.soc.common.views.widgetsInterface.generic.BoardViewerWidget;
import org.soc.gwt.client.game.widgetsSvg.visuals.BoardSvg;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/*
 * Displays a board for preview purposes.
 */
public class BoardViewerSvgWidget implements IsWidget, BoardViewerWidget
{
    private Board board;
    private BoardSvg boardSvg;

    public BoardViewerSvgWidget(Board board, int width, int height)
    {
        super();
        this.board = board;

        boardSvg = new BoardSvg(width, height, board);
    }

    @Override
    public Widget asWidget()
    {
        return boardSvg.asWidget();
    }

    @Override
    public void layoutBoard()
    {
        // TODO Implement

    }

    @Override
    public void unLayoutBoard()
    {
        // TODO Implement

    }

}
