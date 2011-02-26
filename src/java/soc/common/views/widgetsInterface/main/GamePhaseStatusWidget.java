package soc.common.views.widgetsInterface.main;

import soc.common.game.gamePhase.GamePhase;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.IsWidget;

public interface GamePhaseStatusWidget extends IsWidget
{
    public ImageResource getIcon();

    public GamePhase getGamePhase();
}
