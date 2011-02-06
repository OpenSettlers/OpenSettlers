package soc.gwtClient.game.widgets;

import soc.common.board.Board;
import soc.gwtClient.visuals.svg.BoardSvg;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BoardViewerWidget implements IsWidget
{
    private Board board;
    private BoardSvg boardSvg;

    public BoardViewerWidget(Board board, int width, int height)
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

}
