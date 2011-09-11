package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractStockItemWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.CityStockToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockCityBitmapWidget extends AbstractStockItemWidget implements
        PlayerPieceListChangedEventHandler<City>
{
  Image cityImage = new Image(R.icons().city16());
  Label amountCities = new Label();
  City city = new City();

  public StockCityBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    cityImage.setSize("16px", "16px");
    updateUI();
    rootPanel.add(cityImage);
    rootPanel.add(amountCities);
    player.stock().cities().addCitiesChangedEventHandler(this);
  }
  @Override public PlayerPiece getStockPiece()
  {
    return city;
  }
  private void updateUI()
  {
    amountCities.setText(Integer.toString(player.stock().cities()
            .size()));
  }
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<City> event)
  {
    updateUI();
  }
  @Override protected ToolTip createToolTip()
  {
    return new CityStockToolTip(gameWidget, player);
  }
}
