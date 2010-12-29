package soc.gwtClient.game.abstractWidgets;

import soc.common.board.pieces.PlayerPiece;

public interface IBankTradeUI
{
    public void show();

    public void hide();

    public void setPiece(PlayerPiece piece);

    public void setPopupPosition(int x, int y);
}
