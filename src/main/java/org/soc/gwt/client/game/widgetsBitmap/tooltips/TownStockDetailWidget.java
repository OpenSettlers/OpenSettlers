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

public class TownStockDetailWidget extends AbstractPlayerInfoToolTip {
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
    player.stock().towns().addAddedHandler(new Added<Town>() {
      @Override public void added(Town item) {
        Image townImage = new Image(R.icons().town16());
        townImages.put(item, townImage);
        rootPanel.add(townImage);
      }
    });
    player.stock().towns().addRemovedHandler(new Removed<Town>() {
      @Override public void removed(Town item) {
        Image townImage = townImages.get(item);
        rootPanel.remove(townImage);
      }
    });
  }
}