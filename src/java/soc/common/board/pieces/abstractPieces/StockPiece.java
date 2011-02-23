package soc.common.board.pieces.abstractPieces;

import soc.gwtClient.game.widgetsInterface.playerInfo.StockItemWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.StockItemWidgetFactory;

public interface StockPiece
{
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory);
}
