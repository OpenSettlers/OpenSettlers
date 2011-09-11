package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.RobPlayer.RobPlayerGameBehaviour;

import com.google.gwt.user.client.ui.IsWidget;

public interface StealCardWidget extends IsWidget {
  public void update(RobPlayerGameBehaviour robPlayerGameBehaviour);
  public void cardPicked(GamePlayer opponent);
}
