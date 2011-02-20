package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.standard.BuildTown;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildTownDetailWidget extends AbstractPlayerDetailWidget
{
    private BuildTown buildTown;

    public BuildTownDetailWidget(GameWidget gamePanel, BuildTown buildTown)
    {
        super(gamePanel, buildTown.getPlayer());

        this.buildTown = buildTown;

        rootPanel.add(new Image(Resources.icons().buildLarge()));
        rootPanel.add(new Image(Resources.icons().town()));
    }
}