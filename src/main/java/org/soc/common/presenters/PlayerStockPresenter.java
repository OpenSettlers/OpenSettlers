package org.soc.common.presenters;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.game.*;
import org.soc.common.game.pieces.*;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.views.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;

import com.google.inject.*;

public class PlayerStockPresenter {
  public interface StockPieceView<M extends StockPiece> {
    public void addPiece(M piece);
    public void removePiece(M piece);
  }

  public interface PlayerStockView extends HasToolTip {
    public void addStockPieceView(StockPieceView view);
  }

  private PlayerStockView view;
  private GameWidget gameWidget;
  private GamePlayer player;

  public PlayerStockPresenter(PlayerStockView view, GameWidget gameWidget, GamePlayer player) {
    this.view = view;
    this.gameWidget = gameWidget;
    this.player = player;
    StockItemWidgetFactory factory = gameWidget.clientFactory()
            .getStockItemWidgetFactory(player);
    for (StockPiece stockPiece : gameWidget.game().rules().stockPieces()) {}
    //view.addStockPieceView(stockPiece.createStockItemWidget(factory));
  }

  public class StockCityPresenter {
    @Inject public StockCityPresenter(final StockPieceView<City> view, GameWidget gameWidget,
            GamePlayer player) {
      player.stock().cities().addAddedHandler(new Added<City>() {
        @Override public void added(City item) {
          if (item != null)
            view.addPiece(item);
        }
      });
      player.stock().cities().addRemovedHandler(new Removed<City>() {
        @Override public void removed(City item) {
          if (item != null)
            view.removePiece(item);
        }
      });
    }
  }

  public class StockTownPresenter {
    @Inject public StockTownPresenter(final StockPieceView<Town> view, GameWidget gameWidget,
            GamePlayer player) {
      player.stock().towns().addAddedHandler(new Added<Town>() {
        @Override public void added(Town item) {
          if (item != null)
            view.addPiece(item);
        }
      });
      player.stock().towns().addRemovedHandler(new Removed<Town>() {
        @Override public void removed(Town item) {
          if (item != null)
            view.removePiece(item);
        }
      });
    }
  }

  public class StockRoadPresenter {
    @Inject public StockRoadPresenter(final StockPieceView<Road> view, GameWidget gameWidget,
            GamePlayer player) {
      player.stock().roads().addAddedHandler(new Added<Road>() {
        @Override public void added(Road item) {
          if (item != null)
            view.addPiece(item);
        }
      });
      player.stock().roads().addRemovedHandler(new Removed<Road>() {
        @Override public void removed(Road item) {
          if (item != null)
            view.removePiece(item);
        }
      });
    }
  }
}
