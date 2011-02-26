package soc.common.board.pieces.abstractPieces;

import soc.common.views.widgetsInterface.payerInfo.StockItemWidget;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;

public interface StockPiece
{
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory);
}
