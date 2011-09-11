package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.BuildCity;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class BuildCityDetailBitmapWidget extends AbstractActionDetailWidget
{
    private BuildCity buildCity;

    public BuildCityDetailBitmapWidget(GameWidget gameWidget,
                    BuildCity buildCity)
    {
        super(gameWidget, buildCity.player());
        this.buildCity = buildCity;

        rootPanel.add(new Image(R.icons().build32()));
        rootPanel.add(new Image(R.mediumIcon(buildCity)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buildCity;
    }
}
