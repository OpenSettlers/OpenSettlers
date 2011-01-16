package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;

public class BuyDevelopmentCardGameBehaviour implements GameBehaviour,
        TradeFirst
{
    private BuyDevelopmentCard buyDev;
    private GamePanel gamePanel;

    public BuyDevelopmentCardGameBehaviour(BuyDevelopmentCard buyDev,
            GamePanel gamePanel)
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
    public void start(GamePanel gamePanel)
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
