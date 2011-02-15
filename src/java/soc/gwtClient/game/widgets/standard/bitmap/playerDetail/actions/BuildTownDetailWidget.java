package soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildTownDetailWidget extends AbstractPlayerDetailWidget
{
    private BuildTown buildTown;

    public BuildTownDetailWidget(GamePanel gamePanel, BuildTown buildTown)
    {
        super(gamePanel, buildTown.getPlayer());

        this.buildTown = buildTown;

        rootPanel.add(new Image(Resources.icons().buildLarge()));
        rootPanel.add(new Image(Resources.icons().town()));
    }
}