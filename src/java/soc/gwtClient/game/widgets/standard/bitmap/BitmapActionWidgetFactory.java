package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.actions.gameAction.EndTurn;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.common.actions.gameAction.turnActions.standard.TradePlayer;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.factories.IActionWidgetFactory;

/*
 * Constructs game UI using bitmap UI elements like png icons
 */
public class BitmapActionWidgetFactory implements IActionWidgetFactory
{
    @Override
    public IActionWidget createActionWidget(TurnAction action, Player player,
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
        
        if (action instanceof BuildTown)
        {
            return new BitmapBuildTownWidget(gamePanel, player);
        }
        
        if (action instanceof EndTurn)
        {
            return new BitmapEndTurnWidget(gamePanel, player);
        }
        
        if (action instanceof ClaimVictory)
        {
            return new BitmapClaimVictoryWidget(gamePanel, player);
        }
        
        if (action instanceof TradePlayer)
        {
            return new BitmapTradePlayerWidget(gamePanel, player);
        }
        
        if (action instanceof TradeBank)
        {
            return new BitmapTradeBankWidget(gamePanel, player);
        }        
        if (action instanceof PlayDevelopmentCard)
        {
            return new BitmapPlayDevelopmentCardWidget(player, gamePanel);
        }
        
        return null;
    }
}
