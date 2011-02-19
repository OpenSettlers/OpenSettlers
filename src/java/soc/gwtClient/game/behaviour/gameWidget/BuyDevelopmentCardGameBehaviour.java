package soc.gwtClient.game.behaviour.gameWidget;

import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class BuyDevelopmentCardGameBehaviour implements GameBehaviour,
        TradeFirst
{
    private BuyDevelopmentCard buyDev;
    private GameWidget gamePanel;

    public BuyDevelopmentCardGameBehaviour(BuyDevelopmentCard buyDev,
            GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
        this.buyDev = buyDev;
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        GamePlayer player = gamePanel.getPlayingPlayer();
        if (player.getResources().hasAtLeast(DevelopmentCard.getCost()))
        {
            gamePanel.sendAction(buyDev);
        }
        else
        {
            gamePanel.getBankTradeUI().setDevcardTrade(this);
        }
    }

    @Override
    public void onCancelTrade()
    {
    }

    @Override
    public void onTraded()
    {
        gamePanel.sendAction(buyDev);
    }
}
