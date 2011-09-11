package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class BuildRoadDetailWidget extends AbstractActionDetailWidget
{
    private BuildRoad buildRoad;

    public BuildRoadDetailWidget(GameWidget gameWidget, BuildRoad buildRoad)
    {
        super(gameWidget, buildRoad.player());
        this.buildRoad = buildRoad;

        rootPanel.add(new Image(R.icons().build32()));
        rootPanel.add(new Image(R.mediumIcon(buildRoad)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildRoad;
    }

}
