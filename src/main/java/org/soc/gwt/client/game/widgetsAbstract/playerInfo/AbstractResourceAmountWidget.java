package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.*;
import org.soc.common.views.widgetsInterface.playerInfo.*;

import com.google.gwt.user.client.ui.*;

public class AbstractResourceAmountWidget implements ResourceAmountWidget
{
  protected GamePlayer player;
  protected ComplexPanel rootPanel;

  public AbstractResourceAmountWidget(GamePlayer player)
  {
    this.player = player;
    rootPanel = createRootPanel();
    //player.resources().addResourcesChangedHandler(this);
  }
  @Override public ComplexPanel createRootPanel()
  {
    return new VerticalPanel();
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
