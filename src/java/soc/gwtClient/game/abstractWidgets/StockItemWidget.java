package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

import soc.common.board.pieces.PlayerPiece;

public interface StockItemWidget extends IsWidget
{
    public ComplexPanel createRootPanel();
    public PlayerPiece getStockPiece();
}
