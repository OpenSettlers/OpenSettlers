package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.playerInfo.StockWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class AbstractStockWidget implements StockWidget
{
  protected GameWidget gameWidget;
  protected GamePlayer player;
  protected ComplexPanel rootPanel;

  public AbstractStockWidget(GameWidget gameWidget, GamePlayer player)
  {
    this.gameWidget = gameWidget;
    this.player = player;
    rootPanel = createRootPanel();
    createStockItemWidgetsList();
  }
  private void createStockItemWidgetsList()
  {
    StockItemWidgetFactory factory = gameWidget.clientFactory()
            .getStockItemWidgetFactory(player);
    for (StockPiece stockPiece : gameWidget.game().rules()
            .stockPieces())
      rootPanel.add(stockPiece.createStockItemWidget(factory));
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.client.game.IStockWidget#createRootPanel() */
  @Override public ComplexPanel createRootPanel()
  {
    return new HorizontalPanel();
  }
}
