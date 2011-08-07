package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuildCity;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.Resources;

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
