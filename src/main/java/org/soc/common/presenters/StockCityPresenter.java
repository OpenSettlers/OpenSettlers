package org.soc.common.presenters;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.presenters.PlayerStockPresenter.StockPieceView;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.inject.Inject;

public class StockCityPresenter {
  public interface StockCityView extends StockPieceView<City> {}

  @Inject public StockCityPresenter(final StockCityView view, GameWidget gameWidget,
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
