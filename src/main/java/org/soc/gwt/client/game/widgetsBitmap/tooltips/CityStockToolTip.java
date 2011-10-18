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

public class CityStockToolTip extends AbstractPlayerInfoToolTip {
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
    player.stock().cities().addAddedHandler(new Added<City>() {
      @Override public void added(City item) {
        Image cityImage = new Image(R.icons().city16());
        cityImages.put(item, cityImage);
        rootPanel.add(cityImage);
      }
    });
    player.stock().cities().addRemovedHandler(new Removed<City>() {
      @Override public void removed(City item) {
        Image cityImage = cityImages.get(item);
        rootPanel.remove(cityImage);
      }
    });
  }
}
