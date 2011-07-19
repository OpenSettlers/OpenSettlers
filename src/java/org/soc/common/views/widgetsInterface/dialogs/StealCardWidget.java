package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.behaviour.gameWidget.beforeSend.RobPlayerGameBehaviour;

import com.google.gwt.user.client.ui.IsWidget;

public interface StealCardWidget extends IsWidget
{
    public void update(RobPlayerGameBehaviour robPlayerGameBehaviour);

    public void cardPicked(GamePlayer opponent);
}
