package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.views.widgetsInterface.playerInfo.ResourceAmountWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractResourceAmountWidget implements ResourceAmountWidget,
        ResourcesChangedHandler
{
  protected GamePlayer player;
  protected ComplexPanel rootPanel;

  public AbstractResourceAmountWidget(GamePlayer player)
  {
    this.player = player;
    rootPanel = createRootPanel();
    player.resources().addResourcesChangedHandler(this);
  }
  @Override public ComplexPanel createRootPanel()
  {
    return new VerticalPanel();
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {}
}
