package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class CityStockToolTip extends AbstractPlayerInfoToolTip implements
        PlayerPieceListChangedEventHandler<City>
{
  private Map<City, Image> cityImages = new HashMap<City, Image>();

  public CityStockToolTip(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    for (City city : player.stock().cities())
    {
      Image cityImage = new Image(R.icons().city16());
      cityImages.put(city, cityImage);
      rootPanel.add(cityImage);
    }
    player.stock().cities().addCitiesChangedEventHandler(this);
  }
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<City> event)
  {
    if (event.getRemovedPiece() != null)
    {
      Image cityImage = cityImages.get(event.getRemovedPiece());
      rootPanel.remove(cityImage);
    }
    if (event.getAddedPiece() != null)
    {
      Image cityImage = new Image(R.icons().city16());
      cityImages.put(event.getAddedPiece(), cityImage);
      rootPanel.add(cityImage);
    }
  }
}
