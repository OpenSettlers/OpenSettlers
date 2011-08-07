package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuildTown;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildTownDetailWidget extends AbstractActionDetailWidget
{
    private BuildTown buildTown;

    public BuildTownDetailWidget(GameWidget gameWidget, BuildTown buildTown)
    {
        super(gameWidget, buildTown.getPlayer());

        this.buildTown = buildTown;

        rootPanel.add(new Image(Resources.icons().build32()));
        rootPanel.add(new Image(Resources.mediumIcon(buildTown)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildTown;
    }
}