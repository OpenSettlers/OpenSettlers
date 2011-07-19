package soc.gwt.client.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildCityDetailBitmapWidget extends AbstractActionDetailWidget
{
    private BuildCity buildCity;

    public BuildCityDetailBitmapWidget(GameWidget gameWidget,
                    BuildCity buildCity)
    {
        super(gameWidget, buildCity.getPlayer());
        this.buildCity = buildCity;

        rootPanel.add(new Image(Resources.icons().build32()));
        rootPanel.add(new Image(Resources.mediumIcon(buildCity)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildCity;
    }
}
