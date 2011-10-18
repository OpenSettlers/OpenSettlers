package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;

import com.google.gwt.user.client.ui.*;

public interface LooseCardsWidget extends IsWidget
{
  public void setVisible(boolean visible);
  public void update();
  public GamePlayer getPlayer();
  public ResourceList getLostCards();
}
