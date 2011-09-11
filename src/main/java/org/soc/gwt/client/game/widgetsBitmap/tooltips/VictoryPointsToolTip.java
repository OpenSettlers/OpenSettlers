package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.VictoryPointItem;
import org.soc.common.game.VictoryPointsChangedEvent;
import org.soc.common.game.VictoryPointsChangedEvent.VictoryPointsChangedHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;

import com.google.gwt.user.client.ui.Image;

public class VictoryPointsToolTip extends AbstractPlayerInfoToolTip implements
        VictoryPointsChangedHandler
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
    player.victoryPoints().addVictoryPointsChangedListener(this);
  }
  @Override public void onVictoryPointsChanged(VictoryPointsChangedEvent event) {
    if (event.getAddedPoint() != null) {
      Image vpImage = new Image(event.getAddedPoint().icon().iconDefault());
      vpImages.put(event.getAddedPoint(), vpImage);
      rootPanel.add(vpImage);
    }
    if (event.getRemovedPoint() != null) {
      Image vpImage = vpImages.get(event.getRemovedPoint());
      rootPanel.remove(vpImage);
    }
  }
}