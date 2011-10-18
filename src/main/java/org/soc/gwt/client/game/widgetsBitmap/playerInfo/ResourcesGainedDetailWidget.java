package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.actions.*;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;
import org.soc.gwt.client.game.widgetsBitmap.generic.*;

/*
 * Displays a list of resources the player gained by a player action,
 * such as a dice roll, monopoly, year of plenty or robbing a player.
 */
public class ResourcesGainedDetailWidget extends AbstractActionDetailWidget
{
  private ResourceListWidget resourceWidget;
  private MutableResourceList resources = new MutableResourceListImpl();
  private RollDice rolledDice;

  public ResourcesGainedDetailWidget(GameWidget gameWidget, RollDice rolledDice)
  {
    super(gameWidget, rolledDice.player());
    this.rolledDice = rolledDice;
    resourceWidget = new ResourceListBitmapWidget(new MutableResourceListImpl(resources), null, null);
    rootPanel.add(resourceWidget);
    resourceWidget.setHeight("3em");
  }
  public void update(ResourceList resources)
  {
    resourceWidget.setResources(resources);
  }
  @Override public GameAction getGameAction()
  {
    return rolledDice;
  }
}