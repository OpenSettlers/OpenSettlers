package org.soc.gwt.client.game.widgetsBitmap.status;

import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.phases.InitialPlacementGamePhase;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class InitialPlacementBitmapWidget implements GamePhaseStatusWidget
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private InitialPlacementGamePhase gamePhase;
    private ImageResource icon;

    public InitialPlacementBitmapWidget(InitialPlacementGamePhase gamePhase)
    {
        super();
        this.gamePhase = gamePhase;

        icon = Resources.mediumIcon(gamePhase);

        rootPanel.add(new Label("Place initial towns & roads"));
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
