package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.*;

import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.game.DevelopmentCard.VictoryPoint;
import org.soc.common.game.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.*;

import com.google.gwt.user.client.ui.*;

/** Shows amount of victory points by displaying each victory point as an image */
public class VictoryPointDetailWidget extends AbstractPlayerInfoToolTip
{
  private HashMap<VictoryPoint, Image> pointsIcons = new HashMap<VictoryPoint, Image>();

  public VictoryPointDetailWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    update();
    player.victoryPoints().addAddedHandler(new Added<VictoryPointItem>() {
      @Override public void added(VictoryPointItem item) {
        rootPanel.add(new Image(item.icon()
                .iconDefault()));
      }
    });
    player.victoryPoints().addRemovedHandler(new Removed<VictoryPointItem>() {
      @Override public void removed(VictoryPointItem item) {
        // TODO Auto-generated method stub
      }
    });
  }
  private void update()
  {
    for (VictoryPointItem vp : player.victoryPoints())
    {
      rootPanel.add(new Image(vp.icon().iconDefault()));
    }
  }
}
