package org.soc.gwt.client.game;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.presenters.PlayerStockPresenter.StockPieceView;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AbstractStockPieceView<P extends StockPiece> extends VerticalPanel implements
        StockPieceView<P>, MouseOverHandler, MouseOutHandler {
  protected ComplexPanel rootPanel;
  protected ToolTip toolTip;

  public AbstractStockPieceView() {
    super();
  }
  public AbstractStockPieceView(GameWidget gameWidget, GamePlayer player) {
    toolTip = createToolTip();
    rootPanel.addDomHandler(this, MouseOverEvent.getType());
    rootPanel.addDomHandler(this, MouseOutEvent.getType());
  }
  protected ToolTip createToolTip() {
    return null;
  }
  @Override public void onMouseOver(MouseOverEvent event) {
    //    gameWidget.toolTipManager().show(toolTip);
  }
  @Override public void onMouseOut(MouseOutEvent event) {
    //    gameWidget.toolTipManager().hide(toolTip);
  }
}
