package soc.common.views.behaviour.gameWidget.beforeSend;

import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.common.game.developmentCards.AbstractDevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.widgetsInterface.main.GameWidget;

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
