package soc.gwtClient.game.widgetsInterface.playerInfo;

import soc.common.board.pieces.abstractPieces.PlayerPiece;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface StockItemWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public PlayerPiece getStockPiece();
}
