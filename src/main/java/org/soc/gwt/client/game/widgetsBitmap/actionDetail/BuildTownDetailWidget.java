package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.BuildTown;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class BuildTownDetailWidget extends AbstractActionDetailWidget
{
    private BuildTown buildTown;

    public BuildTownDetailWidget(GameWidget gameWidget, BuildTown buildTown)
    {
        super(gameWidget, buildTown.player());

        this.buildTown = buildTown;

        rootPanel.add(new Image(R.icons().build32()));
        rootPanel.add(new Image(R.mediumIcon(buildTown)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildTown;
    }
}