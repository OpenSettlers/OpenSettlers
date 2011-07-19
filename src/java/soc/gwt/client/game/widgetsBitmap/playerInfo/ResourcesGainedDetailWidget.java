package soc.gwt.client.game.widgetsBitmap.playerInfo;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.board.resources.ResourceList;
import soc.common.views.widgetsInterface.generic.ResourceListWidget;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;

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