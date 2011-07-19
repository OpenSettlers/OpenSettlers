package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.views.behaviour.gameWidget.received.GameOverGameBehaviour;

import com.google.gwt.user.client.ui.IsWidget;

public interface GameOverDialog extends IsWidget
{
    public void update(GameOverGameBehaviour claimVictoryGameBehaviour);
}
