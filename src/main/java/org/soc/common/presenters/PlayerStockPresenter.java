package org.soc.common.presenters;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.Town;
import org.soc.common.views.HasToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;

import com.google.inject.Inject;

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
      player.stock().cities()
              .addCitiesChangedEventHandler(new PlayerPieceListChangedEventHandler<City>() {
                @Override public void
                        onPlayerPieceListChanged(PlayerPieceListChangedEvent<City> event) {
                  if (event.getAddedPiece() != null) {
                    view.addPiece(event.getAddedPiece());
                  }
                  if (event.getRemovedPiece() != null) {
                    view.removePiece(event.getRemovedPiece());
                  }
                }
              });
    }
  }

  public class StockTownPresenter {
    @Inject public StockTownPresenter(final StockPieceView<Town> view, GameWidget gameWidget,
            GamePlayer player) {
      player.stock().towns()
              .addTownsChangedEventHandler(new PlayerPieceListChangedEventHandler<Town>() {
                @Override public void onPlayerPieceListChanged(
                        PlayerPieceListChangedEvent<Town> event) {
                  if (event.getAddedPiece() != null) {
                    view.addPiece(event.getAddedPiece());
                  }
                  if (event.getRemovedPiece() != null) {
                    view.removePiece(event.getRemovedPiece());
                  }
                }
              });
    }
  }

  public class StockRoadPresenter {
    @Inject public StockRoadPresenter(final StockPieceView<Road> view, GameWidget gameWidget,
            GamePlayer player) {
      player.stock().roads()
              .addRoadsChangedEventHandler(new PlayerPieceListChangedEventHandler<Road>() {
                @Override public void onPlayerPieceListChanged(
                        PlayerPieceListChangedEvent<Road> event) {
                  if (event.getAddedPiece() != null) {
                    view.addPiece(event.getAddedPiece());
                  }
                  if (event.getRemovedPiece() != null) {
                    view.removePiece(event.getRemovedPiece());
                  }
                }
              });
    }
  }
}
