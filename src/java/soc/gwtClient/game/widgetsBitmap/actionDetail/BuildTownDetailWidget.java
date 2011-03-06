package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwtClient.images.Resources;

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