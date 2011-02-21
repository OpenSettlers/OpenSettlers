package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.board.resources.ResourceList;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourceListWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

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