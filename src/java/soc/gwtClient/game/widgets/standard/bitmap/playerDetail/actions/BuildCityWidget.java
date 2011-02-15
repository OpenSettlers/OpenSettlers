package soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions;

import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuildCityWidget extends AbstractPlayerDetailWidget
{
    private BuildCity buildCity;

    public BuildCityWidget(GamePanel gamePanel, BuildCity buildCity)
    {
        super(gamePanel, buildCity.getPlayer());
        this.buildCity = buildCity;

        rootPanel.add(new Image(Resources.icons().buildLarge()));
        rootPanel.add(new Image(Resources.icons().city()));
    }
}
