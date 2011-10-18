package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.*;

import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.game.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.*;

import com.google.gwt.user.client.ui.*;

public class VictoryPointsToolTip extends AbstractPlayerInfoToolTip
{
  private Map<VictoryPointItem, Image> vpImages = new HashMap<VictoryPointItem, Image>();

  public VictoryPointsToolTip(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    for (VictoryPointItem vp : player.victoryPoints())
    {
      Image vpImage = new Image(vp.icon().iconDefault());
      vpImages.put(vp, vpImage);
      rootPanel.add(vpImage);
    }
    player.victoryPoints().addAddedHandler(new Added<VictoryPointItem>() {
      @Override public void added(VictoryPointItem item) {
        Image vpImage = new Image(item.icon().iconDefault());
        vpImages.put(item, vpImage);
        rootPanel.add(vpImage);
      }
    });
    player.victoryPoints().addRemovedHandler(new Removed<VictoryPointItem>() {
      @Override public void removed(VictoryPointItem item) {
        Image vpImage = vpImages.get(item);
        rootPanel.remove(vpImage);
      }
    });
  }
}