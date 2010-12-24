package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
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
public class ActionWidgetBitmapFactory implements IActionWidgetFactory
{
    @Override
    public IActionWidget createActionWidget(TurnAction action, Player player,
            IGamePanel gamePanel)
    {
        if (action instanceof BuildRoad)
        {
            return new BuildRoadBitmapWidget(gamePanel, player);
        }
        
        if (action instanceof BuildCity)
        {
            return new BuildCityBitmapWidget(gamePanel, player);
        }
        
        if (action instanceof BuildTown)
        {
            return new BuildTownBitmapWidget(gamePanel, player);
        }
        
        if (action instanceof EndTurn)
        {
            return new EndTurnBitmapWidget(gamePanel, player);
        }
        
        if (action instanceof ClaimVictory)
        {
            return new ClaimVictoryBitmapWidget(gamePanel, player);
        }
        
        if (action instanceof TradePlayer)
        {
            return new TradePlayerBitmapWidget(gamePanel, player);
        }
        
        if (action instanceof TradeBank)
        {
            return new TradeBankBitmapWidget(gamePanel, player);
        }        
        if (action instanceof PlayDevelopmentCard)
        {
            return new PlayDevelopmentCardBitmapWidget(player, gamePanel);
        }
        
        return null;
    }

}
