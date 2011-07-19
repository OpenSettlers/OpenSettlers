package soc.gwt.client.game.widgetsBitmap.status;

import soc.common.game.phases.GamePhase;
import soc.common.game.phases.LobbyGamePhase;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.gwt.client.images.Resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LobbyBitmapWidget implements GamePhaseStatusWidget
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private LobbyGamePhase gamePhase;
    private ImageResource icon;

    public LobbyBitmapWidget(LobbyGamePhase gamePhase)
    {
        super();
        this.gamePhase = gamePhase;

        icon = Resources.mediumIcon(gamePhase);
        rootPanel.add(new Label("Lobby phase"));
        rootPanel.add(new Image(icon));
        rootPanel.setStyleName("phasePanel");

    }

    @Override
    public GamePhase getGamePhase()
    {
        return gamePhase;
    }

    @Override
    public ImageResource getIcon()
    {
        return icon;
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}