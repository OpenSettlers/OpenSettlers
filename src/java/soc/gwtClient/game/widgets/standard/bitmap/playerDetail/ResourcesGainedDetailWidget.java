package soc.gwtClient.game.widgets.standard.bitmap.playerDetail;

import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.PlayerDetailWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;
import soc.gwtClient.game.widgets.bitmap.ResourceListBitmapWidget;

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

    public ResourcesGainedDetailWidget(GamePlayer player)
    {
        super(player);
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
