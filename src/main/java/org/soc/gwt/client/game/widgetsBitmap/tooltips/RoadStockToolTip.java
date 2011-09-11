package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class RoadStockToolTip extends AbstractPlayerInfoToolTip implements
        PlayerPieceListChangedEventHandler<Road>
{
  private Map<Road, Image> roadImages = new HashMap<Road, Image>();

  public RoadStockToolTip(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    for (Road road : player.stock().roads())
    {
      Image roadImage = new Image(R.icons().road16());
      roadImages.put(road, roadImage);
      rootPanel.add(roadImage);
    }
    player.stock().roads().addRoadsChangedEventHandler(this);
  }
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
  {
    if (event.getRemovedPiece() != null)
    {
      Image roadImage = roadImages.get(event.getRemovedPiece());
      rootPanel.remove(roadImage);
    }
    if (event.getAddedPiece() != null)
    {
      Image roadImage = new Image(R.icons().road16());
      roadImages.put(event.getAddedPiece(), roadImage);
      rootPanel.add(roadImage);
    }
  }
}