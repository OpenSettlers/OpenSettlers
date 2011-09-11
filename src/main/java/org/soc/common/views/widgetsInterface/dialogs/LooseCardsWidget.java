package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourceList;

import com.google.gwt.user.client.ui.IsWidget;

public interface LooseCardsWidget extends IsWidget
{
  public void setVisible(boolean visible);
  public void update();
  public GamePlayer getPlayer();
  public ResourceList getLostCards();
}
