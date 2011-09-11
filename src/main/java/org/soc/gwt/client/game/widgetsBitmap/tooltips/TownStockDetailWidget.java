package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Town;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class TownStockDetailWidget extends AbstractPlayerInfoToolTip implements
        PlayerPieceListChangedEventHandler<Town>
{
  private Map<Town, Image> townImages = new HashMap<Town, Image>();

  public TownStockDetailWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    for (Town town : player.stock().towns())
    {
      Image townImage = new Image(R.icons().town16());
      townImages.put(town, townImage);
      rootPanel.add(townImage);
    }
    player.stock().towns().addTownsChangedEventHandler(this);
  }
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Town> event)
  {
    if (event.getRemovedPiece() != null)
    {
      Image townImage = townImages.get(event.getRemovedPiece());
      rootPanel.remove(townImage);
    }
    if (event.getAddedPiece() != null)
    {
      Image townImage = new Image(R.icons().town16());
      townImages.put(event.getAddedPiece(), townImage);
      rootPanel.add(townImage);
    }
  }
}
