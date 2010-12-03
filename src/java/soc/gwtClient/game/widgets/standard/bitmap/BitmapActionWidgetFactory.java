package soc.gwtClient.client.game.standard.bitmap;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.game.Player;
import soc.gwtClient.client.game.IActionWidget;
import soc.gwtClient.client.game.IActionWidgetFactory;
import soc.gwtClient.client.game.IGamePanel;

/*
 * Constructs game UI using bitmap UI elements like png icons
 */
public class BitmapActionWidgetFactory implements IActionWidgetFactory
{
    @Override
    public IActionWidget createActionPanel(TurnAction action, Player player,
            IGamePanel gamePanel)
    {
        if (action instanceof BuildRoad)
        {
            return new BitmapBuildRoadWidget(gamePanel, player);
        }
        
        if (action instanceof BuildCity)
        {
            return new BitmapBuildCityWidget(gamePanel, player);
        }
        
        return null;
    }
}
