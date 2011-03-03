package soc.gwtClient.game.widgetsBitmap.status;

import soc.common.game.phases.EndedGamePhase;
import soc.common.game.phases.GamePhase;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EndedBitmapWidget implements GamePhaseStatusWidget
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private EndedGamePhase gamePhase;
    private ImageResource icon;

    public EndedBitmapWidget(EndedGamePhase gamePhase)
    {
        super();
        this.gamePhase = gamePhase;

        icon = Resources.gamePhase(gamePhase);
        rootPanel.add(new Label("This game has ended"));
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
