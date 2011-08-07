package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.phases.GamePhase;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.IsWidget;

public interface GamePhaseStatusWidget extends IsWidget
{
    public ImageResource getIcon();

    public GamePhase getGamePhase();
}
