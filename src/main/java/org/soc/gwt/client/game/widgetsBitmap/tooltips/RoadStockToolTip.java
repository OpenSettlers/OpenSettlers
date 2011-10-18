package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.*;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.game.*;
import org.soc.common.game.pieces.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class RoadStockToolTip extends AbstractPlayerInfoToolTip
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
    player.stock().roads().addAddedHandler(new Added<Road>() {
      @Override public void added(Road item) {
        Image roadImage = roadImages.get(item);
        rootPanel.remove(roadImage);
      }
    });
    player.stock().roads().addRemovedHandler(new Removed<Road>() {
      @Override public void removed(Road item) {
        Image roadImage = new Image(R.icons().road16());
        roadImages.put(item, roadImage);
        rootPanel.add(roadImage);
      }
    });
  }
}