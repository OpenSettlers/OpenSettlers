package soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions;

import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildRoadDetailWidget extends AbstractPlayerDetailWidget
{
    private BuildRoad buildRoad;

    public BuildRoadDetailWidget(GamePanel gamePanel, BuildRoad buildRoad)
    {
        super(gamePanel, buildRoad.getPlayer());
        this.buildRoad = buildRoad;

        rootPanel.add(new Image(Resources.icons().buildLarge()));
        rootPanel.add(new Image(Resources.icons().road()));
    }

}
