package org.soc.common.views.behaviour.gameWidget.beforeSend;

import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.game.developmentCards.AbstractDevelopmentCard;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class BuyDevelopmentCardGameBehaviour implements GameBehaviour,
        TradeFirst
{
    private BuyDevelopmentCard buyDev;
    private GameWidget gameWidget;

    public BuyDevelopmentCardGameBehaviour(GameWidget gameWidget,
            BuyDevelopmentCard buyDev)
    {
        super();
        this.gameWidget = gameWidget;
        this.buyDev = buyDev;
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        GamePlayer player = gameWidget.getPlayingPlayer();
        if (player.getResources().hasAtLeast(AbstractDevelopmentCard.getCost()))
        {
            gameWidget.sendAction(buyDev);
        }
        else
        {
            gameWidget.getBankTradeDialog().setDevcardTrade(this);
        }
    }

    @Override
    public void onCancelTrade()
    {
    }

    @Override
    public void onTraded()
    {
        gameWidget.sendAction(buyDev);
    }
}
