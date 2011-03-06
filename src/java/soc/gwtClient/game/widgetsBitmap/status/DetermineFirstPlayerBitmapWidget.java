package soc.gwtClient.game.widgetsBitmap.status;

import soc.common.game.phases.DetermineFirstPlayerGamePhase;
import soc.common.game.phases.GamePhase;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DetermineFirstPlayerBitmapWidget implements GamePhaseStatusWidget
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase;
    private ImageResource icon;

    public DetermineFirstPlayerBitmapWidget(
                    DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase)
    {
        this.determineFirstPlayerGamePhase = determineFirstPlayerGamePhase;

        icon = Resources.mediumIcon(determineFirstPlayerGamePhase);

        rootPanel.add(new Label("Highroller starts the game"));
        rootPanel.add(new Image(icon));
        rootPanel.setStyleName("phasePanel");
    }

    @Override
    public GamePhase getGamePhase()
    {
        return determineFirstPlayerGamePhase;
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
