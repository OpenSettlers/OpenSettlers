package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.gwtClient.game.widgetsAbstract.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildTownDetailWidget extends AbstractActionDetailWidget
{
    private BuildTown buildTown;

    public BuildTownDetailWidget(GameWidget gameWidget, BuildTown buildTown)
    {
        super(gameWidget, buildTown.getPlayer());

        this.buildTown = buildTown;

        rootPanel.add(new Image(Resources.icons().buildLarge()));
        rootPanel.add(new Image(Resources.icons().town()));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildTown;
    }
}