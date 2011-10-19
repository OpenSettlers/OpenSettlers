package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.*;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.DevelopmentCard.Soldier;
import org.soc.common.game.*;
import org.soc.common.game.pieces.Army.ArmyChangedEvent;
import org.soc.common.game.pieces.Army.ArmyChangedEventHandler;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.*;

import com.google.gwt.user.client.ui.*;

public class SoldiersToolTip extends AbstractPlayerInfoToolTip implements
        ArmyChangedEventHandler
{
  private Map<Soldier, Image> soldierImages = new HashMap<Soldier, Image>();

  public SoldiersToolTip(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    for (Soldier soldier : player.army().getSoldiers())
    {
      Image soldierImage = new Image(Models.mediumIcon(soldier));
      soldierImages.put(soldier, soldierImage);
      rootPanel.add(soldierImage);
    }
    player.army().addSoldiersChangedEventHandler(this);
  }
  @Override public void onArmyChanged(ArmyChangedEvent event)
  {
    if (event.getAddedSoldier() != null)
    {
      Image soldierImage = new Image(Models.mediumIcon(event
              .getAddedSoldier()));
      soldierImages.put(event.getAddedSoldier(), soldierImage);
      rootPanel.add(soldierImage);
    }
  }
}
