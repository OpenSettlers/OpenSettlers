package org.soc.common.views.widgetsInterface.playerInfo;

import org.soc.common.game.pieces.Piece.PlayerPiece;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface StockItemWidget extends IsWidget
{
  public ComplexPanel createRootPanel();
  public PlayerPiece getStockPiece();

  public interface StockItemWidgetFactory
  {
    public StockItemWidget createRoadStockWidget();
    public StockItemWidget createCityStockWidget();
    public StockItemWidget createTownStockWidget();
  }
}
