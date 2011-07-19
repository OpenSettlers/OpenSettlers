package org.soc.common.board.pieces.abstractPieces;

import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidget;
import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;

public interface StockPiece
{
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory);

}
