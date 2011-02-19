package soc.gwtClient.game.widgetsBitmap.tooltips;

import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourceListWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerDetailWidget;

import com.google.gwt.user.client.ui.Widget;

/*
 * Displays a list of resources the player gained by a player action,
 * such as a dice roll, monopoly, year of plenty or robbing a player.
 */
public class ResourcesGainedDetailWidget extends AbstractPlayerDetailWidget
        implements PlayerDetailWidget
{
    private ResourceListWidget resourceWidget;
    private ResourceList resources = new ResourceList();

    public ResourcesGainedDetailWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
        resourceWidget = new ResourceListBitmapWidget(resources, null, null);
        rootPanel.add(resourceWidget);
        resourceWidget.setHeight("3em");
    }

    public void update(ResourceList resources)
    {
        resourceWidget.setResources(resources);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
