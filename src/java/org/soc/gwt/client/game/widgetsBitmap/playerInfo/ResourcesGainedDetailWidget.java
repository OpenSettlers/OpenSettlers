package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.views.widgetsInterface.generic.ResourceListWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;

/*
 * Displays a list of resources the player gained by a player action,
 * such as a dice roll, monopoly, year of plenty or robbing a player.
 */
public class ResourcesGainedDetailWidget extends AbstractActionDetailWidget
{
    private ResourceListWidget resourceWidget;
    private ResourceList resources = new ResourceList();
    private RollDice rolledDice;

    public ResourcesGainedDetailWidget(GameWidget gameWidget, RollDice rolledDice)
    {
        super(gameWidget, rolledDice.getPlayer());
        this.rolledDice = rolledDice;
        resourceWidget = new ResourceListBitmapWidget(resources, null, null);
        rootPanel.add(resourceWidget);
        resourceWidget.setHeight("3em");
    }

    public void update(ResourceList resources)
    {
        resourceWidget.setResources(resources);
    }

    @Override
    public GameAction getGameAction()
    {
        return rolledDice;
    }
}