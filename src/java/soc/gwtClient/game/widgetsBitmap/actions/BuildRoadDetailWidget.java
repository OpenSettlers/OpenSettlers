package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.standard.BuildRoad;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildRoadDetailWidget extends AbstractPlayerDetailWidget
{
    private BuildRoad buildRoad;

    public BuildRoadDetailWidget(GameWidget gamePanel, BuildRoad buildRoad)
    {
        super(gamePanel, buildRoad.getPlayer());
        this.buildRoad = buildRoad;

        rootPanel.add(new Image(Resources.icons().buildLarge()));
        rootPanel.add(new Image(Resources.icons().road()));
    }

}
