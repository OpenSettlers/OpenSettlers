package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuildRoad;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildRoadDetailWidget extends AbstractActionDetailWidget
{
    private BuildRoad buildRoad;

    public BuildRoadDetailWidget(GameWidget gameWidget, BuildRoad buildRoad)
    {
        super(gameWidget, buildRoad.getPlayer());
        this.buildRoad = buildRoad;

        rootPanel.add(new Image(Resources.icons().build32()));
        rootPanel.add(new Image(Resources.mediumIcon(buildRoad)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildRoad;
    }

}
