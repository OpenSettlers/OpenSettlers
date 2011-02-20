package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

/*
 * Constructs game UI using bitmap UI elements like png icons
 */
public class ActionWidgetBitmapFactory implements ActionWidgetFactory
{
    private GamePlayer player;
    private DiceWidgetFactory diceWidgetFactory;
    private GameWidget gamePanel;

    public ActionWidgetBitmapFactory(GameWidget gamePanel, GamePlayer player)
    {
        super();
        this.player = player;
        this.gamePanel = gamePanel;

        diceWidgetFactory = new DiceWidgetBitmapFactory(player);
    }

    @Override
    public ActionWidget createBuildCityWidget()
    {
        return new BuildCityBitmapWidget(gamePanel, player);
    }

    @Override
    public ActionWidget createBuildRoadWidget()
    {
        return new BuildRoadBitmapWidget(gamePanel, player);
    }

    @Override
    public ActionWidget createBuildTownWidget()
    {
        return new BuildTownBitmapWidget(gamePanel, player);
    }

    @Override
    public ActionWidget createBuyDevelopmentCardWidget()
    {
        return new BuyDevelopmentCardBitmapWidget(gamePanel, player);
    }

    @Override
    public ActionWidget createClaimVictoryWidget()
    {
        return new ClaimVictoryBitmapWidget(gamePanel, player);
    }

    @Override
    public ActionWidget createEndTurnWidget()
    {
        return new EndTurnBitmapWidget(gamePanel, player);
    }

    @Override
    public ActionWidget createPlayDevelopmentCardWidget()
    {
        return new PlayDevelopmentCardBitmapWidget(player, gamePanel);
    }

    @Override
    public ActionWidget createRollDiceWidget()
    {
        return diceWidgetFactory.createDiceWidget(gamePanel.getGame()
                .getRules().getDiceType(), gamePanel);
    }

    @Override
    public ActionWidget createTradeBankWidget()
    {
        return new TradeBankBitmapWidget(gamePanel, player);
    }

    @Override
    public ActionWidget createTradePlayerWidget()
    {
        return new TradePlayerBitmapWidget(gamePanel, player);
    }

}
