package org.soc.common.views.widgetsInterface.playerInfo;

import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerTurnStatusWidget extends IsWidget,
        TurnChangedHandler
{}
