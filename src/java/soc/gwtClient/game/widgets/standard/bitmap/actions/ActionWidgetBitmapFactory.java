package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.common.actions.gameAction.turnActions.standard.TradePlayer;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.ActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.factories.ActionWidgetFactory;
import soc.gwtClient.game.abstractWidgets.factories.DiceWidgetFactory;
import soc.gwtClient.game.widgets.standard.bitmap.DiceWidgetBitmapFactory;

/*
 * Constructs game UI using bitmap UI elements like png icons
 */
public class ActionWidgetBitmapFactory implements ActionWidgetFactory
{
    private DiceWidgetFactory diceWidgetFactory = new DiceWidgetBitmapFactory();

    @Override
    public ActionWidget createActionWidget(TurnAction action, GamePlayer player,
            GamePanel gamePanel)
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
        if (action instanceof BuyDevelopmentCard)
        {
            return new BuyDevelopmentCardBitmapWidget(gamePanel, player);
        }
        if (action instanceof RollDice)
        {
            return diceWidgetFactory.createDiceWidget(gamePanel.getGame()
                    .getGameRules().getDiceType(), gamePanel);
        }

        return null;
    }

}
