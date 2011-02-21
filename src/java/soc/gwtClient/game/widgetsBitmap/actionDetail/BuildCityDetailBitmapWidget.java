package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildCity;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildCityDetailBitmapWidget extends AbstractActionDetailWidget
{
    private BuildCity buildCity;

    public BuildCityDetailBitmapWidget(GameWidget gameWidget, BuildCity buildCity)
    {
        super(gameWidget, buildCity.getPlayer());
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
