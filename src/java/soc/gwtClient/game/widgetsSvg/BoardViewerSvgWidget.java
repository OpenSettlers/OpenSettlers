package soc.gwtClient.game.widgetsSvg;

import soc.common.board.Board;
import soc.gwtClient.game.widgetsSvg.visuals.BoardSvg;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BoardViewerSvgWidget implements IsWidget
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

}
