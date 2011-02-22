package soc.gwtClient.game.widgetsSvg;

import soc.common.board.Board;
import soc.gwtClient.game.widgetsInterface.generic.BoardViewerWidget;
import soc.gwtClient.game.widgetsSvg.visuals.BoardSvg;

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
