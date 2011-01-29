package soc.gwtClient.game.widgets.standard.bitmap.status;

import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.gwtClient.images.Resources;

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

        icon = Resources.gamePhase(gamePhase);

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
