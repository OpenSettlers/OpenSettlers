package org.soc.gwt.client.game;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.Town;
import org.soc.common.presenters.PlayerStockPresenter.PlayerStockView;
import org.soc.common.presenters.PlayerStockPresenter.StockPieceView;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlayerStockGwt extends HorizontalPanel implements PlayerStockView, IsWidget {
  @Override public void addStockPieceView(StockPieceView view) {
    add((Widget) view);
  }

  public abstract class AbstractStockPieceView<P extends StockPiece> extends VerticalPanel
          implements StockPieceView<P>, MouseOverHandler, MouseOutHandler {
    protected ComplexPanel rootPanel;
    protected ToolTip toolTip;
    protected int amountPieces;
    private Label labelAmountPieces = new Label();

    public AbstractStockPieceView() {
      super();
    }
    public AbstractStockPieceView(GameWidget gameWidget, GamePlayer player) {
      rootPanel.add(createImage());
      rootPanel.add(labelAmountPieces);
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
    protected void updateUI() {
      labelAmountPieces.setText(Integer.toString(amountPieces));
    }
    @Override public void addPiece(P piece) {
      amountPieces++;
      updateUI();
    }
    @Override public void removePiece(P piece) {
      amountPieces--;
      updateUI();
    }
    protected abstract Image createImage();
  }

  public class StockCityGwt extends AbstractStockPieceView<City> {
    @Override protected Image createImage() {
      return new Image(R.icons().city16());
    }
  }

  public class StockTownGwt extends AbstractStockPieceView<Town> {
    @Override protected Image createImage() {
      return new Image(R.icons().town16());
    }
  }

  public class StockRoadGwt extends AbstractStockPieceView<Road> {
    @Override protected Image createImage() {
      return new Image(R.icons().road16());
    }
  }
}
