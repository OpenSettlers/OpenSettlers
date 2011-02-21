package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildCity;
import soc.gwtClient.game.widgetsAbstract.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildCityDetailBitmapWidget extends AbstractActionDetailWidget
{
    private BuildCity buildCity;

    public BuildCityDetailBitmapWidget(GameWidget gamePanel, BuildCity buildCity)
    {
        super(gamePanel, buildCity.getPlayer());
        this.buildCity = buildCity;

        rootPanel.add(new Image(Resources.icons().buildLarge()));
        rootPanel.add(new Image(Resources.icons().city()));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildCity;
    }
}
