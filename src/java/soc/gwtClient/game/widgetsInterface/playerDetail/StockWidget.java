package soc.gwtClient.game.widgetsInterface.playerDetail;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

import soc.common.board.pieces.abstractPieces.PlayerPiece;

public interface StockWidget extends IsWidget
{
    public StockItemWidget createStockItemWidget(PlayerPiece playerPiece);
    public ComplexPanel createRootPanel();
}
